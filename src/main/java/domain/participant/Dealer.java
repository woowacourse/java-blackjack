package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;

public class Dealer extends GameParticipant {
    private final Deck deck;

    public Dealer(Deck deck, CardHand cardHand) {
        super("딜러", cardHand);
        this.deck = deck;
    }

    public Card pickCard() {
        return deck.drawCard();
    }

    public CardHand pickInitialDeal() {
        return deck.getInitialDeal();
    }

    public boolean doesNeedCard() {
        return cardHand.doesDealerNeedCard();
    }
}
