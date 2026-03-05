package controller;

import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.stream.IntStream;
import message.IOMessage;
import util.NameParser;
import util.checkWinner;
import view.InputView;
import view.ResultView;

public class BlackJackGame {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList(inputView.getParticipant());
        startAndPrint(dealer, players);
        drawPlayersTurn(players);
        drawDealerTurn(dealer);
        printResult(players, dealer);
    }

    private void startGame(Dealer dealer, Players players) {
        IntStream.range(0, 2).forEach(i -> dealer.drawCard());

        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(player -> IntStream.range(0, 2)
                        .forEach(i -> player.drawCard()));
    }

    private void startAndPrint(Dealer dealer, Players players) {
        startGame(dealer, players);
        System.out.printf("%n");
        resultView.printGameStart(players, dealer);
        System.out.printf("%n");
    }

    private void drawPlayersTurn(Players players) {
        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(this::drawPlayerTurn);
    }

    private void drawPlayerTurn(Player player) {
        boolean shouldStop = false;
        while (!shouldStop) {
            shouldStop = stopDraw(player) || drawAfterChoice(player);
        }
    }

    private boolean stopDraw(Player player) {
        return inputView.getMoreCards(player).equals("n");
    }

    private void drawOneCardAndPrint(Player player) {
        player.drawCard();
        System.out.println(player.getName() + "카드: " + resultView.joinCardNames(player.getCardList()));
    }

    private boolean drawAfterChoice(Player player) {
        drawOneCardAndPrint(player);
        return printBustAndCheck(player);
    }

    private boolean printBustAndCheck(Player player) {
        if (!player.checkBust()) {
            return false;
        }
        System.out.println(player.getName() + "는 버스트!");
        return true;
    }

    private void drawDealerTurn(Dealer dealer) {
        System.out.printf("%n");
        while (dealer.getScore().isDealerDraw()) {
            dealer.drawCard();
            System.out.println(IOMessage.DEALER_ONE_CARD.message());
        }
        System.out.printf("%n");
    }

    private void printResult(Players players, Dealer dealer) {
        resultView.printResult(players, dealer);
        if (dealer.checkBust()) {
            System.out.println("딜러는 버스트!");
        }
        checkWinner.decideWinner(dealer, players);
        resultView.printWinner(players, dealer);
    }
}
