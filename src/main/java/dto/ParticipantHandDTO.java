package dto;

import domain.Participant;
import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public final class ParticipantHandDTO {
    private final String name;
    private final String handCards;
    private final String score;

    public ParticipantHandDTO(Participant participant, List<Card> handCards) {
        this.name = participant.getName();
        this.handCards = convertHandCards(handCards);
        score = getStringScore(participant);
    }

    private String convertHandCards(List<Card> handCards) {
        return handCards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    private String getStringScore(Participant participant) {
        if (participant.isBust()) {
            return "버스트";
        }
        return String.valueOf(participant.calculateScore());
    }

    public String getName() {
        return name;
    }

    public String getHandCards() {
        return handCards;
    }

    public String getScore() {
        return score;
    }
}
