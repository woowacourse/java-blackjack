package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
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
        CardNumber cardNumber = card.getNumber();
        CardSuit cardSuit = card.getSuit();
        return cardNumber.getValue() + cardSuit.getValue();
    }

    public String getName() {
        return name;
    }

    public String getFirstCardName() {
        return cardNames.get(0);
    }

    public List<String> getCardNames() {
        return cardNames;
    }
}
