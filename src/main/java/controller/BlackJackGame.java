package controller;

import javax.print.DocFlavor.READER;
import model.*;
import model.card.Deck;
import model.user.Dealer;
import model.user.Player;
import model.user.Players;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    private static final int ADDITIONAL_DRAW_COUNT = 1;
    public static final int INITIAL_DRAW_COUNT = 2;
    public static final int HIT_BOUNDARY = 16;

    public static void play(Players players, Dealer dealer, Deck deck) {
        OutputView.printInitialCards(players, dealer);
        OutputView.printUserCard(players, dealer);
        drawCardToPlayers(players, deck);
        hitOrStayForDealer(dealer, deck);
        OutputView.printFinalScoreResult(players, dealer);
        compareScores(players, dealer);
        OutputView.printResult(players, dealer);
    }

    private static void drawCardToPlayers(Players players, Deck deck) {
        for (Player player : players) {
            drawCardEachPlayer(deck, player);
        }
    }

    private static void drawCardEachPlayer(Deck deck, Player player) {
        while (!player.isBust() && YesOrNo.findAnswer(InputView.inputYesOrNo(player)).isYes()) {
            player.drawCard(deck.draw(ADDITIONAL_DRAW_COUNT));
            OutputView.printPlayerCard(player);
        }
    }

    private static void hitOrStayForDealer(Dealer dealer, Deck deck) {
        if (dealer.isHitBound()) {
            OutputView.printDealerDraw(dealer);
            dealer.drawCard(deck.draw(ADDITIONAL_DRAW_COUNT));
        }
    }

    private static void compareScores(Players players, Dealer dealer) {
        for (Player player : players) {
            Result result = Result.calculateResult(dealer, player);
            player.setResult(result);
            dealer.setResult(result);
        }
    }
}
