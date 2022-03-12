package blackjack.domain.dto;

import blackjack.domain.player.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class Status {

    private final String name;
    private final List<CardDto> cardDtos;
    private final int score;

    public Status(String name, List<CardDto> cardDtos, int score) {
        this.name = name;
        this.cardDtos = cardDtos;
        this.score = score;
    }

    public static Status of(Participant participant) {
        return new Status(
                participant.getName(),
                participant.getCards().stream()
                        .map(CardDto::of)
                        .collect(Collectors.toList()),
                participant.getScore());
    }

    public static Status initialOf(Participant participant) {
        return new Status(
                participant.getName(),
                participant.openCards().stream()
                        .map(CardDto::of)
                        .collect(Collectors.toList()),
                participant.getScore());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }

    public int getScore() {
        return score;
    }
}
