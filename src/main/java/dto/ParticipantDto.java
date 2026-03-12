package dto;

import domain.Card;
import domain.Participant;
import java.util.ArrayList;
import java.util.List;

public record ParticipantDto(String name, List<CardDto> cards) {
    public static ParticipantDto consistWithInitialInfo(Participant participant) {
        String name = participant.getName();
        List<Card> initialCards = participant.showInitialCard();

        return new ParticipantDto(
                name,
                consistCardsInfo(initialCards)
        );
    }

    public static ParticipantDto from(Participant participant) {
        String name = participant.getName();
        List<Card> ownCards = participant.showOwnCards();

        return new ParticipantDto(
                name,
                consistCardsInfo(ownCards)
        );
    }

    private static List<CardDto> consistCardsInfo(List<Card> initialCards) {
        return initialCards
                .stream()
                .map(CardDto::from)
                .toList();
    }

    public static List<ParticipantDto> listOf(List<? extends Participant> participants) {
        List<ParticipantDto> participantDtos = new ArrayList<>();

        for (Participant participant : participants) {
            String name = participant.getName();

            List<CardDto> cards = new ArrayList<>();
            for (Card card : participant.showOwnCards()) {
                cards.add(CardDto.from(card));
            }

            ParticipantDto participantDto = new ParticipantDto(name, cards);
            participantDtos.add(participantDto);
        }

        return participantDtos;
    }
}
