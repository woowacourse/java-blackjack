package controller;

import model.*;
import view.InputView;
import view.OutputView;

public class BlackJackGame {

    public static final int SCORE_BOUNDARY = 16;
    public static final int ADDITIONAL_DRAW_COUNT = 1;

    public static void play(Players players, Dealer dealer, Deck deck) {
        OutputView.printInitialCards(players, dealer);
        OutputView.printUsersCard(players, dealer);
        for (Player player : players.getPlayers()) {
            drawCardEachPlayer(deck, player);
        }
        hitOrStayForDealer(dealer, deck);
        OutputView.printFinalCardHandResult(players, dealer);
        compareScores(players, dealer);
        OutputView.printResult(players, dealer);
    }

    private static void compareScores(final Players players, final Dealer dealer) {
        for(Player player : players.getPlayers()){
            Result result = dealer.compareScore(player);
            player.setResult(result);
            dealer.setResult(result);
        }
    }


    private static void drawCardEachPlayer(Deck deck, Player player) {
        YesOrNo yesOrNo = YesOrNo.of("y");
        while (player.getScore() < 21 && yesOrNo.getYesOrNo()) {
            yesOrNo = YesOrNo.of(InputView.inputYesOrNo(player));
            drawWhenYes(deck, player, yesOrNo);
            OutputView.printPlayerCard(player);
        }
    }

    private static void drawWhenYes(Deck deck, Player player, YesOrNo yesOrNo) {
        if (yesOrNo.getYesOrNo()) {
            player.drawCard(deck.draw(ADDITIONAL_DRAW_COUNT));
        }
    }

    private static void hitOrStayForDealer(Dealer dealer, Deck deck) {
        if (dealer.getScore() <= SCORE_BOUNDARY) {
            OutputView.printDealerDraw(dealer);
            dealer.drawCard(deck.draw(ADDITIONAL_DRAW_COUNT));
        }
    }
}
