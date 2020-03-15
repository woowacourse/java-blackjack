package controller;

import model.*;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    public static final int ADDITIONAL_DRAW_COUNT = 1;
    public static final int INITIAL_DRAW_COUNT = 2;
    public static final int HIT_BOUNDARY = 16;
    public static final int BLACK_JACK_COUNT = 21;

    public static void play(Players players, Dealer dealer, Deck deck) {
        OutputView.printInitialCards(players, dealer);
        OutputView.printUsersCard(players, dealer);
        drawCardToPlayers(players, deck);
        hitOrStayForDealer(dealer, deck);
        OutputView.printFinalCardHandResult(players, dealer);
        GameResult gameResult = new GameResult(players, dealer);
        gameResult.calculateResults();
        OutputView.printResult(gameResult);
    }

    private static void drawCardToPlayers(final Players players, final Deck deck) {
        for (Player player : players) {
            drawCardEachPlayer(deck, player);
        }
    }

//    private static void compareScores(final Players players, final Dealer dealer) {
//        GameResult gameResult = new GameResult(players, dealer);
//
//        for (Player player : players) {
//            Result result = Result.calculateResult(dealer, player);
//            gameResult.calculateResults(result, player);
//
//            player.setResult(result);
//            dealer.setResult(result);
//        }
//    }

    private static void drawCardEachPlayer(Deck deck, Player player) {
        while (!player.isBust()) {
            YesOrNo yesOrNo = YesOrNo.getYesOrNoByValue(InputView.inputYesOrNo(player));
            if(!yesOrNo.getTrueOrFalse()){
                break;
            }
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
}
