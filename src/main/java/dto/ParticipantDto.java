package dto;

import domain.Card;
import domain.Participant;
import java.util.ArrayList;
import java.util.List;

public record ParticipantDto(String name, List<CardDto> cards, int score) {
    public static ParticipantDto initialFrom(Participant participant) {
        String name = participant.getName();

        List<CardDto> cards = new ArrayList<>();
        for (Card card : participant.getInitialVisibleCards()) {
            cards.add(CardDto.from(card));
        }

        int score = 0;
        return new ParticipantDto(name, cards, score);
    }

    public static ParticipantDto from(Participant participant) {
        String name = participant.getName();

        List<CardDto> cards = new ArrayList<>();
        for (Card card : participant.getDeck().getCards()) {
            cards.add(CardDto.from(card));
        }

        int score = participant.calculateDeckSum();
        return new ParticipantDto(name, cards, score);
    }

    public static List<ParticipantDto> listOf(List<? extends Participant> participants) {
        List<ParticipantDto> participantDtos = new ArrayList<>();

        for (Participant participant : participants) {
            String name = participant.getName();

            List<CardDto> cards = new ArrayList<>();
            for (Card card : participant.getDeck().getCards()) {
                cards.add(CardDto.from(card));
            }

            int score = participant.calculateDeckSum();

            ParticipantDto participantDto = new ParticipantDto(name, cards, score);
            participantDtos.add(participantDto);
        }

        return participantDtos;
    }
}
