package controller;

import model.*;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    public static final String COMMA = ",";
    public static final int ADDITIONAL_DRAW_COUNT = 1;
    public static final int INITIAL_DRAW_COUNT = 2;
    public static final int HIT_BOUNDARY = 16;
    public static final int BLACK_JACK_COUNT = 21;

    public static void play() {
        Deck deck = new Deck(CardFactory.createCardList());
        PlayerNames playerNames = new PlayerNames(InputView.inputPlayerNames());
        Players players = new Players(playerNames, deck, INITIAL_DRAW_COUNT);
        Dealer dealer = new Dealer(deck, INITIAL_DRAW_COUNT);

        OutputView.printInitialCards(players, dealer);
        OutputView.printUsersCard(players, dealer);
        drawCardToPlayers(players, deck);
        hitOrStayForDealer(dealer, deck);
        OutputView.printFinalCardHandResult(players, dealer);

        GameResult gameResult = new GameResult(players, dealer);
        OutputView.printResult(gameResult);
    }

    private static void drawCardToPlayers(final Players players, final Deck deck) {
        for (Player player : players) {
            drawCardEachPlayer(deck, player);
        }
    }

    private static void drawCardEachPlayer(Deck deck, Player player) {
        while (!player.isMoreThanBlackJack()) {
            Answer answer = Answer.getYesOrNoByValue(InputView.inputYesOrNo(player));
            if (!answer.isYes()) {
                break;
            }
            player.drawCard(deck, ADDITIONAL_DRAW_COUNT);
            OutputView.printPlayerCard(player);
        }
    }

    private static void hitOrStayForDealer(Dealer dealer, Deck deck) {
        if (dealer.isHitBound()) {
            OutputView.printDealerDraw(dealer);
            dealer.drawCard(deck, ADDITIONAL_DRAW_COUNT);
        }
    }
}
