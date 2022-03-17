package blackjack.domain.card;

import blackjack.domain.game.PlayingCards;

import java.util.List;

public enum Denomination {

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

    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int NUMBER_TEN = 10;
    private static final int ACE_EXTRA_NUMBER = 10;

    private final String name;
    private final int number;

    Denomination(final String name, final int number) {
        this.name = name;
        this.number = number;
    }

    public static boolean isBlackjack(final List<Denomination> denominations) {
        if (denominations.size() != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return hasAce(denominations) && hasTen(denominations);
    }

    public static int getTotal(final List<Denomination> denominations) {
        int total = denominations.stream()
                .map(denomination -> denomination.number)
                .mapToInt(Integer::intValue)
                .sum();
        total = addAceExtraNumber(denominations, total);
        return total;
    }

    private static boolean hasAce(final List<Denomination> denominations) {
        return denominations.contains(Denomination.ACE);
    }

    private static boolean hasTen(final List<Denomination> denominations) {
        return denominations.stream()
                .map(denomination -> denomination.number)
                .anyMatch(number -> number == NUMBER_TEN);
    }

    private static int addAceExtraNumber(final List<Denomination> denominations, int total) {
        int aceCount = getAceCount(denominations);
        while (aceCount-- > 0 && total + ACE_EXTRA_NUMBER <= PlayingCards.BLACKJACK) {
            total += ACE_EXTRA_NUMBER;
        }
        return total;
    }

    private static int getAceCount(final List<Denomination> denominations) {
        return (int) denominations.stream()
                .filter(denomination -> denomination.name.equals(ACE.name))
                .count();
    }

    public String getName() {
        return name;
    }
}
