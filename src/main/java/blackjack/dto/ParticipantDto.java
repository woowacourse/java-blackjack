package blackjack.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

public class ParticipantDto {

    private final String name;
    private final List<CardDto> cardDtos;
    private final int score;

    public ParticipantDto(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cardDtos = cards.stream()
                .map(CardDto::toDto)
                .collect(Collectors.toUnmodifiableList());
        this.score = score;
    }

    public static ParticipantDto toDto(final Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCards(), participant.getScore());
    }

    public String getName() {
        return name;
    }

    public List<String> getCardNames() {
        return cardDtos.stream()
                .map(CardDto::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getScore() {
        return score;
    }

}