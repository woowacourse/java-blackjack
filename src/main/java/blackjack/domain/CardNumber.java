package blackjack.domain;

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

    public static final int ACE_MAXIMUM = 11;
    private final String name;
    private final int number;

    CardNumber(final String name, final int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public static int getTotal(final List<CardNumber> cardNumbers) {
        int total = cardNumbers.stream()
                .map(cardNumber -> cardNumber.number)
                .mapToInt(Integer::intValue)
                .sum();

        total = addAceExtraNumber(cardNumbers, total);
        return total;
    }

    private static int addAceExtraNumber(final List<CardNumber> cardNumbers, int total) {
        int aceCount = getAceCount(cardNumbers);
        while (aceCount-- > 0 && total <= ACE_MAXIMUM) {
            total += ACE_MAXIMUM - ACE.number;
        }
        return total;
    }

    private static int getAceCount(final List<CardNumber> cardNumbers) {
        return (int) cardNumbers.stream()
                .filter(CardNumber::isEqualAce)
                .count();
    }

    private static boolean isEqualAce(CardNumber cardNumber) {
        return cardNumber.equals(ACE);
    }
}
