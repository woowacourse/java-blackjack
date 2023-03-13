package controller;

import domain.card.Score;
import domain.command.DrawCommand;
import domain.card.Card;
import domain.card.GameDeck;
import domain.card.ShuffleDeckGenerator;
import domain.game.BlackjackGame;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.Names;
import domain.user.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import view.InputView;
import view.OutputView;

public class BlackjackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = setUp();
        playGame(blackjackGame);
    }

    private BlackjackGame setUp() {
        GameDeck gameDeck = new GameDeck(new ShuffleDeckGenerator());

        Dealer dealer = new Dealer();
        dealer.receiveCards(gameDeck.drawForFirstTurn());

        List<Player> players = setUpPlayers();
        players.forEach(player -> player.receiveCards(gameDeck.drawForFirstTurn()));

        return new BlackjackGame(players, dealer, gameDeck);
    }

    private List<Player> setUpPlayers() {
        return readUserNames().stream()
                .map(name -> new Player(name, readPlayerBatting(name)))
                .collect(Collectors.toList());
    }

    private void playGame(BlackjackGame blackjackGame) {
        showSetUpResult(blackjackGame.makeSetUpResult());
        progressPlayersTurn(blackjackGame);
        progressDealerTurn(blackjackGame);
        showUserCardResults(blackjackGame);
        showFinalProfit(blackjackGame);
    }

    private void progressPlayersTurn(BlackjackGame blackjackGame) {
        while (blackjackGame.hasReadyPlayer()) {
            progressPlayerTurn(blackjackGame);
        }
    }

    private void progressPlayerTurn(BlackjackGame blackjackGame) {
        Player nowPlayer = blackjackGame.getReadyPlayer();
        while (!nowPlayer.hasResult() && readDrawCommand(nowPlayer).equals(DrawCommand.DRAW)) {
            blackjackGame.drawOneMoreCardForPlayer(nowPlayer);
            showDrawResult(nowPlayer);
        }
        if (!nowPlayer.hasResult()) {
            blackjackGame.doStay(nowPlayer);
            showDrawResult(nowPlayer);
        }
    }

    private void progressDealerTurn(BlackjackGame blackJackGame) {
        blackJackGame.drawCardUntilDealerFinished();

        if (blackJackGame.dealerDrawCount() > 0) {
            outputView.printDealerDrawResult(blackJackGame.dealerDrawCount());
        }
    }

    private List<Name> readUserNames() {
        outputView.printInputPlayerNameMessage();
        Names names = new Names(inputView.readPlayersName());
        return names.getNames();
    }

    private int readPlayerBatting(Name name) {
        outputView.printInputPlayerBattingMessage(unwrapName(name));
        return inputView.readPlayerBatting();
    }

    private DrawCommand readDrawCommand(Player player) {
        outputView.printAskOneMoreCardMessage(unwrapName(player.getName()));
        return inputView.readDrawCommand();
    }

    private void showDrawResult(Player player) {
        outputView.printPlayerDrawResult(unwrapName(player.getName()), player.getCards());
    }

    private void showSetUpResult(HashMap<Name, List<Card>> setUpResult) {
        outputView.printSetUpResult(setUpResult);
    }

    private void showUserCardResults(BlackjackGame blackjackGame) {
        List<Name> allUserNames = blackjackGame.getAllUserNames();

        allUserNames.forEach(name -> outputView.printUserCardWithScore(unwrapName(name),
                unwrapCards(blackjackGame.getCards(name)),
                unwrapScore(blackjackGame.getScore(name))));
    }

    private void showFinalProfit(BlackjackGame blackjackGame) {
        Map<Name, Integer> playerPrizes = blackjackGame.calculatePlayerResult();

        outputView.printFinalResultHeaderMessage();

        outputView.printDealerPrizeResult(playerPrizes.values().stream()
                .mapToInt(num -> num)
                .sum() * -1);
        outputView.printPlayerPrizeResult(playerPrizes);
    }

    private String unwrapName(Name name) {
        return name.getName();
    }

    private String unwrapCards(List<Card> cards) {
        return cards.stream()
                .map(Card::getSymbol)
                .collect(Collectors.joining(", "));
    }

    private int unwrapScore(Score score) {
        return score.getScore();
    }
}
