package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantCardsDto {

    private final String name;
    private final List<String> cardNames;

    private ParticipantCardsDto(String name, List<String> cardNames) {
        this.name = name;
        this.cardNames = cardNames;
    }

    public static ParticipantCardsDto from(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), getCardNames(participant.getCards()));
    }

    private static List<String> getCardNames(List<Card> cards) {
        return cards.stream()
                    .map(ParticipantCardsDto::getCardName)
                    .collect(Collectors.toList());
    }

    private static String getCardName(Card card) {
        return card.getNumber()
                   .getValue() + card.getSuit()
                                     .getValue();
    }

    public String getName() {
        return name;
    }

    public List<String> getCardNames() {
        return cardNames;
    }
}
