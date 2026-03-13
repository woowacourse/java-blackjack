package blackjack.model.card;

import blackjack.dto.CardDto;
import java.util.ArrayList;
import java.util.List;

public class Hands {

    private static final int ACE_THRESHOLD_SCORE = 11;
    private static final int ACE_ADDITIONAL_SCORE = 10;

    private final List<Card> cards;

    private Hands(List<Card> cards) {
        this.cards = cards;
    }

    public static Hands empty() {
        return new Hands(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalScore() {
        int baseScore = this.cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();

        boolean hasAce = cards.stream()
                .anyMatch(Card::isAce);

        if (hasAce && baseScore <= ACE_THRESHOLD_SCORE) {
            baseScore += ACE_ADDITIONAL_SCORE;
        }

        return baseScore;
    }

    public boolean hasScoreHigherThan(final int score) {
        return calculateTotalScore() > score;
    }

    public CardDto getFirstCard() {
        Card firstCard = cards.getFirst();
        return CardDto.from(firstCard);
    }

    public List<CardDto> getAllCards() {
        return this.cards.stream()
                .map(CardDto::from)
                .toList();
    }
}
