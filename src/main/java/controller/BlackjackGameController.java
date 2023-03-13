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

    private void showFinalProfit(BlackjackGame blackjackGame) {
        // 플레이어 순서대로 이름, 금액
        // 딜러는 모든 플레이어 최종 수익의 합의 반대 부호
//        HashMap<Name, Integer> playerFinalPrizes = blackjackGame.makePlayerProfitResult();
        Map<Name, Integer> playerPrizes =  blackjackGame.calculatePlayerResult();
        outputView.printFinalResultHeaderMessage();

        playerPrizes.values().stream()
                .mapToInt(num -> num)
                .sum();
        outputView.printDealerPrizeResult(playerPrizes.values().stream()
                .mapToInt(num -> num)
                .sum() * -1);

        outputView.printPlayerPrizeResult(playerPrizes);


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

    private List<Name> readUserNames() {
        outputView.printInputPlayerNameMessage();
        Names names = new Names(inputView.readPlayersName());
        return names.getNames();
    }

    private int readPlayerBatting(Name name) {
        outputView.printInputPlayerBattingMessage(unwrapName(name));
        return inputView.readPlayerBatting();
    }

    private void playGame(BlackjackGame blackjackGame) {
        showSetUpResult(blackjackGame.makeSetUpResult());
        progressPlayersTurn(blackjackGame);
        progressDealerTurn(blackjackGame);
        showUserCardResults(blackjackGame);
        showFinalProfit(blackjackGame);
//        showFinalResult(gameResult);
    }

    private void progressPlayersTurn(BlackjackGame blackjackGame) {
        while(blackjackGame.hasReadyPlayer()) {
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

        Dealer dealer = blackJackGame.getDealer();

        if (dealer.drawCount() > 0) {
            outputView.printDealerDrawResult(dealer.drawCount());
        }
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


    private List<String> unwrapNames(List<Name> names) {
        return names.stream()
                .map(Name::getName)
                .collect(Collectors.toList());
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
