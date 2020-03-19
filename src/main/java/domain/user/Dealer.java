package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;
import domain.result.Result;

public class Dealer extends User {
    private static final int ADD_THRESHOLD = 17;
    private static final String NAME = "딜러";
    private Deck deck;

    public Dealer(PlayingCards playingCards, Deck deck) {
        super(playingCards, NAME);
        this.deck = deck;
    }

//    @Override
//    public int calculateProfit(Result result) {
//        if (result.equals(Result.PLAYER_WIN_WITH_BLACKJACK) || result.equals(Result.PLAYER_WIN_WITHOUT_BLACKJACk))
//        return result.calculateProfit();
//    }

    void confirmCards(int hitSize) {
        for (int i = 0; i < hitSize; i++) {
            Card card = deck.pop();
            hit(card);
        }
    }


}
