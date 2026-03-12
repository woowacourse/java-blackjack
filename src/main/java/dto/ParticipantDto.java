package dto;

import domain.Card;
import domain.Participant;
import java.util.ArrayList;
import java.util.List;

public record ParticipantDto(String name, List<CardDto> cards, int score) {

    public static ParticipantDto from(Participant participant) {
        String name = participant.getName();

        List<CardDto> cardsDtos = new ArrayList<>();
        List<Card> cards = participant.getCards();
        for (Card card : cards) {
            cardsDtos.add(CardDto.from(card));
        }

        int score = participant.calculateDeckSum();
        return new ParticipantDto(name, cardsDtos, score);
    }

    public static ParticipantDto initialFrom(Participant participant) {
        String name = participant.getName();

        List<Card> cards = participant.getInitialVisibleCards();
        List<CardDto> cardDtos = CardDto.listOf(cards);

        int score = participant.calculateDeckSum();

        return new ParticipantDto(name, cardDtos, score);
    }

    public static List<ParticipantDto> listOf(Iterable<? extends Participant> participants) {
        List<ParticipantDto> participantDtos = new ArrayList<>();

        for (Participant participant : participants) {
            ParticipantDto participantDto = ParticipantDto.from(participant);
            participantDtos.add(participantDto);
        }

        return participantDtos;
    }
}
