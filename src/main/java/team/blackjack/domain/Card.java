package team.blackjack.domain;

import java.util.List;

public enum Card {
    ACE_OF_HEARTS(Suit.HEARTS, "A하트", true, List.of(1, 11)),
    TWO_OF_HEARTS(Suit.HEARTS, "2하트", false, List.of(2)),
    THREE_OF_HEARTS(Suit.HEARTS, "3하트", false, List.of(3)),
    FOUR_OF_HEARTS(Suit.HEARTS, "4하트", false, List.of(4)),
    FIVE_OF_HEARTS(Suit.HEARTS, "5하트", false, List.of(5)),
    SIX_OF_HEARTS(Suit.HEARTS, "6하트", false, List.of(6)),
    SEVEN_OF_HEARTS(Suit.HEARTS, "7하트", false, List.of(7)),
    EIGHT_OF_HEARTS(Suit.HEARTS, "8하트", false, List.of(8)),
    NINE_OF_HEARTS(Suit.HEARTS, "9하트", false, List.of(9)),
    TEN_OF_HEARTS(Suit.HEARTS, "10하트", false, List.of(10)),
    JACK_OF_HEARTS(Suit.HEARTS, "J하트", false, List.of(10)),
    QUEEN_OF_HEARTS(Suit.HEARTS, "Q하트", false, List.of(10)),
    KING_OF_HEARTS(Suit.HEARTS, "K하트", false, List.of(10)),

    ACE_OF_DIAMONDS(Suit.DIAMONDS, "A다이아", true, List.of(1, 11)),
    TWO_OF_DIAMONDS(Suit.DIAMONDS, "2다이아", false, List.of(2)),
    THREE_OF_DIAMONDS(Suit.DIAMONDS, "3다이아", false, List.of(3)),
    FOUR_OF_DIAMONDS(Suit.DIAMONDS, "4다이아", false, List.of(4)),
    FIVE_OF_DIAMONDS(Suit.DIAMONDS, "5다이아", false, List.of(5)),
    SIX_OF_DIAMONDS(Suit.DIAMONDS, "6다이아", false, List.of(6)),
    SEVEN_OF_DIAMONDS(Suit.DIAMONDS, "7다이아", false, List.of(7)),
    EIGHT_OF_DIAMONDS(Suit.DIAMONDS, "8다이아", false, List.of(8)),
    NINE_OF_DIAMONDS(Suit.DIAMONDS, "9다이아", false, List.of(9)),
    TEN_OF_DIAMONDS(Suit.DIAMONDS, "10다이아", false, List.of(10)),
    JACK_OF_DIAMONDS(Suit.DIAMONDS, "J다이아", false, List.of(10)),
    QUEEN_OF_DIAMONDS(Suit.DIAMONDS, "Q다이아", false, List.of(10)),
    KING_OF_DIAMONDS(Suit.DIAMONDS, "K다이아", false, List.of(10)),

    ACE_OF_CLUBS(Suit.CLUBS, "A클로버", true, List.of(1, 11)),
    TWO_OF_CLUBS(Suit.CLUBS, "2클로버", false, List.of(2)),
    THREE_OF_CLUBS(Suit.CLUBS, "3클로버", false, List.of(3)),
    FOUR_OF_CLUBS(Suit.CLUBS, "4클로버", false, List.of(4)),
    FIVE_OF_CLUBS(Suit.CLUBS, "5클로버", false, List.of(5)),
    SIX_OF_CLUBS(Suit.CLUBS, "6클로버", false, List.of(6)),
    SEVEN_OF_CLUBS(Suit.CLUBS, "7클로버", false, List.of(7)),
    EIGHT_OF_CLUBS(Suit.CLUBS, "8클로버", false, List.of(8)),
    NINE_OF_CLUBS(Suit.CLUBS, "9클로버", false, List.of(9)),
    TEN_OF_CLUBS(Suit.CLUBS, "10클로버", false, List.of(10)),
    JACK_OF_CLUBS(Suit.CLUBS, "J클로버", false, List.of(10)),
    QUEEN_OF_CLUBS(Suit.CLUBS, "Q클로버", false, List.of(10)),
    KING_OF_CLUBS(Suit.CLUBS, "K클로버", false, List.of(10)),

    ACE_OF_SPADES(Suit.SPADES, "A스페이드", true, List.of(1, 11)),
    TWO_OF_SPADES(Suit.SPADES, "2스페이드", false, List.of(2)),
    THREE_OF_SPADES(Suit.SPADES, "3스페이드", false, List.of(3)),
    FOUR_OF_SPADES(Suit.SPADES, "4스페이드", false, List.of(4)),
    FIVE_OF_SPADES(Suit.SPADES, "5스페이드", false, List.of(5)),
    SIX_OF_SPADES(Suit.SPADES, "6스페이드", false, List.of(6)),
    SEVEN_OF_SPADES(Suit.SPADES, "7스페이드", false, List.of(7)),
    EIGHT_OF_SPADES(Suit.SPADES, "8스페이드", false, List.of(8)),
    NINE_OF_SPADES(Suit.SPADES, "9스페이드", false, List.of(9)),
    TEN_OF_SPADES(Suit.SPADES, "10스페이드", false, List.of(10)),
    JACK_OF_SPADES(Suit.SPADES, "J스페이드", false, List.of(10)),
    QUEEN_OF_SPADES(Suit.SPADES, "Q스페이드", false, List.of(10)),
    KING_OF_SPADES(Suit.SPADES, "K스페이드", false, List.of(10));

    private final Suit suit;
    private final String name;
    private final boolean isAce;
    private final List<Integer> score;

    Card(Suit suit, String name, boolean isAce, List<Integer> values) {
        this.suit = suit;
        this.name = name;
        this.isAce = isAce;
        this.score = values;
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

    public boolean isAce() {
        return this.isAce;
    }

    public List<Integer> getScore() {
        return score;
    }

}
