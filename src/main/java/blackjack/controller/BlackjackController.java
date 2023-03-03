package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.ResultReferee;
import blackjack.domain.game.ScoreBoard;
import blackjack.domain.game.ScoreReferee;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.name.PlayerName;
import blackjack.domain.user.name.UserName;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final int BUST_SCORE = -1;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = getPlayers();
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame();

        startGame(players, dealer, blackjackGame);
        processGame(players, dealer, blackjackGame);
        endGame(players, dealer);
    }

    private void startGame(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        blackjackGame.initGame(dealer);
        players.getPlayers().forEach(blackjackGame::initGame);
        outputView.printUsersCard(dealer, players);
    }

    private void processGame(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        players.getPlayers().forEach((player -> processPlayerDraw(blackjackGame, player)));

        processDealerDraw(dealer, blackjackGame);
    }

    private void processPlayerDraw(final BlackjackGame blackjackGame, final Player player) {
        while (inputView.readCommand(player.getName()) && !isBust(player.showCards())) {
            blackjackGame.draw(player);
            outputView.printUserCards(player);
        }
    }

    private void processDealerDraw(final Dealer dealer, final BlackjackGame blackjackGame) {
        while (!blackjackGame.isDealerEnd(dealer)) {
            blackjackGame.draw(dealer);
            outputView.printDealerDraw();
        }
    }

    private void endGame(final Players players, final Dealer dealer) {
        ScoreBoard scoreBoard = new ScoreBoard(dealer, players);
        outputView.printCardResult(scoreBoard);

        printResult(players, scoreBoard);
    }

    private void printResult(final Players players, final ScoreBoard scoreBoard) {
        ResultReferee referee = new ResultReferee(scoreBoard);
        final Map<UserName, GameResult> playerScore = getPlayerScore(players, referee);
        final Map<GameResult, Integer> dealerScore = referee.getDealerScore();
        outputView.printGameResult(dealerScore, playerScore);
    }

    private boolean isBust(final List<Card> showCards) {
        return ScoreReferee.calculateScore(showCards) == BUST_SCORE;
    }

    private Players getPlayers() {
        final List<String> strings = inputView.readParticipantName();
        return new Players(strings.stream()
                .map(name -> new Player(new PlayerName(name)))
                .collect(Collectors.toList()));
    }

    public Map<UserName, GameResult> getPlayerScore(final Players players, final ResultReferee referee) {
        Map<UserName, GameResult> result = new HashMap<>();

        players.getPlayers().forEach((player) -> {
            final GameResult score = referee.askResultByUserName(player.getName());
            result.put(player.getName(), score);
        });

        return result;
    }
}
