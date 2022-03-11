package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ParticipantCardsDto {

    private final String name;
    private final Set<Card> cards;

    private ParticipantCardsDto(String name, Set<Card> cards) {
        this.name = name;
        this.cards = new HashSet<>(cards);
    }

    public static ParticipantCardsDto of(String name, Set<Card> cards) {
        return new ParticipantCardsDto(name, cards);
    }

    public boolean isPlayer() {
        return Objects.equals(name, Dealer.UNIQUE_NAME);
    }

    public String getName() {
        return name;
    }

    public Set<Card> getCards() {
        return Collections.unmodifiableSet(cards);
    }
}
