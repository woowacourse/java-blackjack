package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;

public class Dealer extends GameParticipant {
    public static final String NAME = "딜러";

    private final Deck deck;

    public Dealer(Deck deck, CardHand cardHand) {
        super(NAME, cardHand);
        this.deck = deck;
    }

    public CardHand pickInitialDeal() {
        return deck.getInitialDeal();
    }

    public Card pickCard() {
        return deck.drawCard();
    }

    @Override
    public boolean canHit() {
        return cardHand.doesDealerNeedCard();
    }
}
