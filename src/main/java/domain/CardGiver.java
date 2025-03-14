package domain;

import java.util.List;

public class CardGiver {

    private static final int DEFAULT_CARD_GIVE_COUNT = 2;

    private final Deck deck;

    public CardGiver(Deck deck) {
        this.deck = deck;
    }

    public void giveOneTo(Participant participant) {
        if (participant.isPossibleDraw()) {
            participant.addDrawCard(deck.drawCard());
        }
    }

    public void giveDefaultTo(List<Participant> participants) {
        participants.forEach(participant -> participant.addDefaultCards(deck.drawCards(DEFAULT_CARD_GIVE_COUNT)));
    }
}
