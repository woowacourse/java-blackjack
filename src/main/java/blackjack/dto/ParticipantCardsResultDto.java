package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.participant.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantCardsResultDto {

    private final String name;
    private final List<String> cardNames;
    private final int score;

    private ParticipantCardsResultDto(String name, List<String> cardNames, int score) {
        this.name = name;
        this.cardNames = cardNames;
        this.score = score;
    }

    public static ParticipantCardsResultDto of(Participant participant) {
        return new ParticipantCardsResultDto(
                participant.getName(), getCardNames(participant.getCards()), participant.getScore()
        );
    }

    private static List<String> getCardNames(List<Card> cards) {
        return cards.stream()
                    .map(ParticipantCardsResultDto::getCardName)
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

    public List<String> getCardNames() {
        return cardNames;
    }

    public int getScore() {
        return score;
    }
}
