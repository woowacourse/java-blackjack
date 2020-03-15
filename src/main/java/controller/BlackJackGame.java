package controller;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;
import util.YesOrNo;
import view.InputView;
import view.OutputView;

public class BlackJackGame {

    private static final int FIRST_CARD_COUNT = 2;

    private final Deck deck;

    public static BlackJackGame set(Deck deck) {
        return new BlackJackGame(deck);
    }

    private BlackJackGame(Deck deck) {
        this.deck = deck;
    }

    public void firstDealOut(Dealer dealer, Players players) {
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            dealer.draw(deck);
            players.draw(deck);
        }
    }

    public void additionalDealOut(Dealer dealer, Players players) {
        players.additionalDealOut(deck, this::isYes, OutputView::printPlayerDealOutResult);
        dealer.additionalDealOut(deck, OutputView::printDealerDealOut);
    }

    private boolean isYes(String name) {
        String input = InputView.receiveYesOrNoInput(name);
        return YesOrNo.isYes(input);
    }
}
