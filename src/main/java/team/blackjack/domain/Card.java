package team.blackjack.domain;

import java.util.List;

public enum Card {
    ACE_OF_HEARTS(Suit.HEARTS, "A하트", List.of(1, 11)),
    TWO_OF_HEARTS(Suit.HEARTS, "2하트", List.of(2)),
    THREE_OF_HEARTS(Suit.HEARTS, "3하트", List.of(3));

    private final Suit suit;
    private final String name;
    private final List<Integer> values;

    Card(Suit suit, String name, List<Integer> values) {
        this.suit = suit;
        this.name = name;
        this.values = values;
    }

    public enum Suit {

        HEARTS(true), DIAMONDS(true), CLUBS(false), SPADES(false);

        private final boolean isRed;

        Suit(boolean isRed) {
            this.isRed = isRed;
        }

        public boolean isRed() {
            return isRed;
        }
    }

    public String getCardName() {
        return this.name;
    }
}
