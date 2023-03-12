package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantStatusResponse {
    private final String name;
    private final List<String> cards;

    public ParticipantStatusResponse(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantStatusResponse of(Participant participant) {
        return new ParticipantStatusResponse(participant.getName().getName(), getCardsStatus(participant.getCards()));
    }

    public static ParticipantStatusResponse ofStart(Participant participant) {
        return new ParticipantStatusResponse(participant.getName().getName(), getCardsStatus(participant.getStartCards()));
    }

    private static List<String> getCardsStatus(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRank() + card.getSuit())
                .collect(toList());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
