package domain;

import java.util.List;

public class CardGiver {

    private static final int DEFAULT_CARD_GIVE_COUNT = 2;

    private final Deck deck;

    public CardGiver(Deck deck) {
        this.deck = deck;
    }

    public Cards giveDefault() {
        return new Cards(deck.drawCards(DEFAULT_CARD_GIVE_COUNT));
    }

    public Card giveOne() {
        return deck.drawCard();
    }

    public void giveDefaultTo(List<Participant> participants) {
        participants.forEach(participant -> participant.addCards(giveDefault()));
    }
}
