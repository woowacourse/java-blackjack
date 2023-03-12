package blackjack.dto;

import blackjack.domain.Name;
import blackjack.domain.Participant;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantStatusDTO {
    private final String name;
    private final List<String> cards;

    private ParticipantStatusDTO(final Name name, final List<Card> cards) {
        this.name = name.getValue();
        this.cards = cards.stream()
                .map(Card::toString)
                .collect(Collectors.toUnmodifiableList());
    }

    public static ParticipantStatusDTO ofStart(final Participant participant) {
        return new ParticipantStatusDTO(participant.getName(), participant.initialOpen());
    }

    public static ParticipantStatusDTO of(final Participant participant) {
        return new ParticipantStatusDTO(participant.getName(), participant.openAll());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return new ArrayList<>(cards);
    }
}
