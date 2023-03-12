package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.participant.Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantDto {
    private final String name;
    private final List<String> cards;

    public ParticipantDto(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantDto from(Participant participant) {
        String participantName = participant.getName();
        List<String> participantCards = makeCardUnits(participant.getHand());
        return new ParticipantDto(participantName, participantCards);
    }

    public static ParticipantDto of(Participant participant, Card card) {
        String participantName = participant.getName();
        List<String> participantCard = Collections.singletonList((makeCardUnit(card.getNumber(), card.getSuit())));
        return new ParticipantDto(participantName, participantCard);
    }

    public static List<ParticipantDto> of(List<? extends Participant> participants) {
        List<ParticipantDto> participantsDto = new ArrayList<>();
        for (Participant participant : participants) {
            participantsDto.add(from(participant));
        }
        return participantsDto;
    }

    private static List<String> makeCardUnits(List<Card> hand) {
        return hand.stream()
                .map(card -> makeCardUnit(card.getNumber(), card.getSuit()))
                .collect(Collectors.toList());
    }

    private static String makeCardUnit(CardNumber number, CardSuit suit) {
        return number.getSymbol() + suit.getSuit();
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
