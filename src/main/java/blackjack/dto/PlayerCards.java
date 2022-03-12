package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class PlayerCards {

    private final String name;
    private final List<Card> cards;

    private PlayerCards(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerCards toPlayerFirstCards(final Participant participant) {
        return new PlayerCards(
                participant.getName(),
                participant.firstCards()
        );
    }

    public static PlayerCards toPlayerCards(final Participant participant) {
        return new PlayerCards(
                participant.getName(),
                participant.cards()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
