package blackjack.card;

import java.util.Random;
import java.util.stream.Stream;

public class CardFixture {

    private static final Random RANDOM = new Random();

    public static Card createCard(CardSuit suit, CardRank rank) {
        return Card.of(suit, rank);
    }

    public static Card createCard(CardSuit suit) {
        return Card.of(suit, getRandomRank());
    }

    public static Card createCard(CardRank rank) {
        return Card.of(getRandomSuit(), rank);
    }

    public static Card createCard(int value) {
        return Card.of(getRandomSuit(), getRank(value));
    }

    public static Card createCard() {
        return Card.of(getRandomSuit(), getRandomRank());
    }

    public static Cards createCardsForSum(int sum) {
        if (sum < 1) {
            throw new UnsupportedOperationException("불가");
        }

        Cards cards = Cards.empty();

        if (sum < 5) {
            cards.add(createCard(sum));
            return cards;
        }

        if (sum % 2 == 1) {
            cards.add(createCard(3));
            sum -= 3;
        }

        int count = sum / 2;
        for (int i = 0; i < count; i++) {
            cards.add(createCard(CardRank.TWO));
        }
        return cards;
    }

    private static CardSuit getRandomSuit() {
        CardSuit[] suits = CardSuit.values();
        return suits[RANDOM.nextInt(suits.length)];
    }

    private static CardRank getRandomRank() {
        CardRank[] ranks = CardRank.values();
        return ranks[RANDOM.nextInt(ranks.length)];
    }

    private static CardRank getRank(int value) {
        return Stream.of(CardRank.values())
                .filter(rank -> rank.getValue() == value)
                .findAny()
                .orElseThrow();
    }
}
