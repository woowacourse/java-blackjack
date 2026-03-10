package controller;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.Random;
import java.util.stream.IntStream;
import util.NameParser;
import view.InputView;
import view.ResultView;

public class BlackJackGame {
    private static final int INITIAL_DRAW_COUNT = 2;
    private static final String STOP_DRAW_INPUT = "n";

    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        Random random = new Random();
        Players players = NameParser.makeNameList(inputView.getParticipant());
        Dealer dealer = new Dealer();
        Cards cards = new Cards(random);

        startGame(players, dealer, cards);
        resultView.printGameStartSection(players, dealer);
        drawPlayersTurn(players, cards);
        drawDealerTurn(dealer, cards);
        printResult(players, dealer);
    }

    private void startGame(Players players, Dealer dealer, Cards cards) {
        drawInitialDealerCards(dealer, cards);
        drawInitialPlayerCards(players, cards);
    }

    private void drawInitialDealerCards(Dealer dealer, Cards cards) {
        IntStream.range(0, INITIAL_DRAW_COUNT).forEach(i -> dealer.drawCard(cards));
    }

    private void drawInitialPlayerCards(Players players, Cards cards) {
        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(player -> drawInitialCardsToPlayer(player, cards));
    }

    private void drawInitialCardsToPlayer(Player player, Cards cards) {
        IntStream.range(0, INITIAL_DRAW_COUNT)
                .forEach(i -> player.drawCard(cards));
    }

    private void drawPlayersTurn(Players players, Cards cards) {
        IntStream.range(0, players.getSize())
                .mapToObj(players::getPlayer)
                .forEach(player -> drawPlayerTurn(player, cards));
    }

    private void drawPlayerTurn(Player player, Cards cards) {
        while (shouldContinuePlayerTurn(player, cards)) {
        }
    }

    private boolean shouldContinuePlayerTurn(Player player, Cards cards) {
        if (checkPlayerStopDraw(player)) {
            return false;
        }
        return !drawAndCheckBust(player, cards);
    }

    private boolean checkPlayerStopDraw(Player player) {
        return inputView.getMoreCards(player).equals(STOP_DRAW_INPUT);
    }

    private boolean drawAndCheckBust(Player player, Cards cards) {
        drawOneCardAndPrint(player, cards);
        return printBustAndCheck(player);
    }

    private void drawOneCardAndPrint(Player player, Cards cards) {
        player.drawCard(cards);
        resultView.printPlayerCards(player.getName(), resultView.joinCardNames(player.getCardList()));
    }

    private boolean printBustAndCheck(Player player) {
        if (!player.checkBust()) {
            return false;
        }
        resultView.printPlayerBust(player.getName());
        return true;
    }

    private void drawDealerTurn(Dealer dealer, Cards cards) {
        resultView.printLineBreak();
        while (canDealerDraw(dealer)) {
            drawDealerCardAndPrint(dealer, cards);
        }
        resultView.printLineBreak();
    }

    private boolean canDealerDraw(Dealer dealer) {
        return dealer.getScore().isDealerDraw();
    }

    private void drawDealerCardAndPrint(Dealer dealer, Cards cards) {
        dealer.drawCard(cards);
        resultView.printDealerDrawMessage();
    }

    private void printResult(Players players, Dealer dealer) {
        printGameResult(players, dealer);
        printWinnerResult(players, dealer);
    }

    private void printGameResult(Players players, Dealer dealer) {
        resultView.printResult(players, dealer);
        if (dealer.checkBust()) {
            resultView.printDealerBust();
        }
    }

    private void printWinnerResult(Players players, Dealer dealer) {
        players.decideWinner(dealer);
        resultView.printWinner(players, dealer);
    }
}
