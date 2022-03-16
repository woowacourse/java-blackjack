package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.participant.Participant;

public class ParticipantDto {

    private final String name;
    private final List<CardDto> cards;
    private final int score;

    private ParticipantDto(String name, List<CardDto> cards, int score) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public static ParticipantDto from(Participant participant) {
        List<CardDto> cards = participant.getCards().stream()
            .map(CardDto::from)
            .collect(Collectors.toList());
        return new ParticipantDto(participant.getName(), cards, participant.getScore());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        return score;
    }
}
