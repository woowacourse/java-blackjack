package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;
import domain.result.Result;

public class Dealer extends User {
    private static final String NAME = "딜러";
    private Deck deck;

    public Dealer(PlayingCards playingCards, Deck deck, Money money) {
        super(playingCards, NAME, money);
        this.deck = deck;
    }

    @Override
    public Money calculateProfit(Result result) {
        if (dealerLose(result)) {
            return result.calculateProfit(money).multiply(LOSE_PENALTY_RATE);
        }
        return result.calculateProfit(money);
    }

    private boolean dealerLose(Result result) {
        return result.equals(Result.PLAYER_WIN_WITH_BLACKJACK) || result.equals(Result.PLAYER_WIN_WITHOUT_BLACKJACk);
    }

    void confirmCards(int hitSize) {
        for (int i = 0; i < hitSize; i++) {
            Card card = deck.pop();
            hit(card);
        }
    }
}
