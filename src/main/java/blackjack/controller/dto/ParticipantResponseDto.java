package blackjack.controller.dto;

import blackjack.domain.carddeck.Card;
import blackjack.domain.participant.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantResponseDto {
    private final String name;
    private final List<CardDto> hand;
    private final int totalScore;

    public ParticipantResponseDto(String name, List<CardDto> hand, int totalScore) {
        this.name = name;
        this.hand = hand;
        this.totalScore = totalScore;
    }

    public static ParticipantResponseDto from(Participant participant) {
        return new ParticipantResponseDto(participant.getName(), parseCardDtos(participant.toHandList()), participant.getTotalScore().getValue());
    }

    private static List<CardDto> parseCardDtos(List<Card> hand) {
        return hand.stream()
                .map(card -> new CardDto(card.getPatternName(), card.getNumberName()))
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getHand() {
        return hand;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
