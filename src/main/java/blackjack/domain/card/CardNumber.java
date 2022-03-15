package blackjack.domain.card;

import blackjack.domain.game.PlayingCards;

import java.util.List;

public enum CardNumber {

    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    ;

    private static final int NUMBER_TEN = 10;
    private static final int ACE_EXTRA_NUMBER = 10;

    private final String name;
    private final int number;

    CardNumber(final String name, final int number) {
        this.name = name;
        this.number = number;
    }

    public static boolean isBlackjack(final List<CardNumber> cardNumbers) {
        if (cardNumbers.size() != 2) {
            return false;
        }
        return hasAce(cardNumbers) && hasTen(cardNumbers);
    }

    public static int getTotal(final List<CardNumber> cardNumbers) {
        int total = cardNumbers.stream()
                .map(cardNumber -> cardNumber.number)
                .mapToInt(Integer::intValue)
                .sum();
        total = addAceExtraNumber(cardNumbers, total);
        return total;
    }

    private static boolean hasAce(final List<CardNumber> cardNumbers) {
        return cardNumbers.contains(CardNumber.ACE);
    }

    private static boolean hasTen(final List<CardNumber> cardNumbers) {
        return cardNumbers.stream()
                .map(cardNumber -> cardNumber.number)
                .anyMatch(number -> number == NUMBER_TEN);
    }

    private static int addAceExtraNumber(final List<CardNumber> cardNumbers, int total) {
        int aceCount = getAceCount(cardNumbers);
        while (aceCount-- > 0 && total + ACE_EXTRA_NUMBER <= PlayingCards.BLACKJACK) {
            total += ACE_EXTRA_NUMBER;
        }
        return total;
    }

    private static int getAceCount(final List<CardNumber> cardNumbers) {
        return (int) cardNumbers.stream()
                .filter(cardNumber -> cardNumber.name.equals(ACE.name))
                .count();
    }

    public String getName() {
        return name;
    }
}
