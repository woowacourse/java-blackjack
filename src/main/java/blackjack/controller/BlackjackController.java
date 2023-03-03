package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ResultReferee;
import blackjack.domain.game.Score;
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
        final List<String> strings = inputView.readParticipantName();

        Players players = new Players(strings.stream()
                .map(name -> new Player(new PlayerName(name))).collect(Collectors.toList()));
        Dealer dealer = new Dealer();

        BlackjackGame blackjackGame = new BlackjackGame();
        initUserCard(players, dealer, blackjackGame);
        outputView.printUsersCard(dealer, players);

        players.getPlayers().forEach((player -> {
            while (inputView.readCommand(player.getName()) && !isBust(player.showCards())) {
                blackjackGame.draw(player);
                outputView.printUserCards(player);
            }
        }));

        while (!blackjackGame.isDealerEnd(dealer)) {
            blackjackGame.draw(dealer);
            outputView.printDealerDraw();
        }

        ScoreBoard scoreBoard = new ScoreBoard(dealer, players);
        outputView.printCardResult(scoreBoard);

        printResult(players, scoreBoard);
    }

    private boolean isBust(final List<Card> showCards) {
        return ScoreReferee.calculateScore(showCards) == BUST_SCORE;
    }

    private void printResult(final Players players, final ScoreBoard scoreBoard) {
        ResultReferee referee = new ResultReferee(scoreBoard);
        final Map<UserName, Score> playerScore = getPlayerScore(players, referee);
        final Map<Score, Integer> dealerScore = referee.getDealerScore();
        outputView.printGameResult(dealerScore, playerScore);
    }

    public Map<UserName, Score> getPlayerScore(final Players players, final ResultReferee referee) {
        Map<UserName, Score> result = new HashMap<>();

        players.getPlayers().forEach((player) -> {
            final Score score = referee.askResultByUserName(player.getName());
            result.put(player.getName(), score);
        });

        return result;
    }

    private static void initUserCard(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        blackjackGame.initGame(dealer);
        players.getPlayers().forEach(blackjackGame::initGame);
    }

}
