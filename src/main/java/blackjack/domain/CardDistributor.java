package blackjack.domain;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Deck;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import java.util.stream.IntStream;

public class CardDistributor {

    public static final int STARTING_CARD_COUNT = 2;
    private final Deck deck;

    public CardDistributor(final Deck deck) {
        this.deck = deck;
    }

    public void distributeStartingCardsTo(Participants participants) {
        participants.unwrap().forEach(this::distributeStartingCardsTo);
    }

    private void distributeStartingCardsTo(Participant participant) {
        IntStream.range(0, STARTING_CARD_COUNT)
            .forEach(i -> distributeCardTo(participant));
    }

    public void distributeCardTo(Participant participant) {
        Card card = deck.draw();
        participant.draw(card);
    }
}
