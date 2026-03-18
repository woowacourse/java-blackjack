package team.blackjack.domain;

import java.util.List;
import java.util.Set;

public enum Card {
    ACE_OF_HEARTS(Suit.HEARTS, Number.ACE),
    TWO_OF_HEARTS(Suit.HEARTS, Number.TWO),
    THREE_OF_HEARTS(Suit.HEARTS, Number.THREE),
    FOUR_OF_HEARTS(Suit.HEARTS, Number.FOUR),
    FIVE_OF_HEARTS(Suit.HEARTS, Number.FIVE),
    SIX_OF_HEARTS(Suit.HEARTS, Number.SIX),
    SEVEN_OF_HEARTS(Suit.HEARTS, Number.SEVEN),
    EIGHT_OF_HEARTS(Suit.HEARTS, Number.EIGHT),
    NINE_OF_HEARTS(Suit.HEARTS, Number.NINE),
    TEN_OF_HEARTS(Suit.HEARTS, Number.TEN),
    JACK_OF_HEARTS(Suit.HEARTS, Number.JACK),
    QUEEN_OF_HEARTS(Suit.HEARTS, Number.QUEEN),
    KING_OF_HEARTS(Suit.HEARTS, Number.KING),

    ACE_OF_DIAMONDS(Suit.DIAMONDS, Number.ACE),
    TWO_OF_DIAMONDS(Suit.DIAMONDS, Number.TWO),
    THREE_OF_DIAMONDS(Suit.DIAMONDS, Number.THREE),
    FOUR_OF_DIAMONDS(Suit.DIAMONDS, Number.FOUR),
    FIVE_OF_DIAMONDS(Suit.DIAMONDS, Number.FIVE),
    SIX_OF_DIAMONDS(Suit.DIAMONDS, Number.SIX),
    SEVEN_OF_DIAMONDS(Suit.DIAMONDS, Number.SEVEN),
    EIGHT_OF_DIAMONDS(Suit.DIAMONDS, Number.EIGHT),
    NINE_OF_DIAMONDS(Suit.DIAMONDS, Number.NINE),
    TEN_OF_DIAMONDS(Suit.DIAMONDS, Number.TEN),
    JACK_OF_DIAMONDS(Suit.DIAMONDS, Number.JACK),
    QUEEN_OF_DIAMONDS(Suit.DIAMONDS, Number.QUEEN),
    KING_OF_DIAMONDS(Suit.DIAMONDS, Number.KING),

    ACE_OF_CLUBS(Suit.CLUBS, Number.ACE),
    TWO_OF_CLUBS(Suit.CLUBS, Number.TWO),
    THREE_OF_CLUBS(Suit.CLUBS, Number.THREE),
    FOUR_OF_CLUBS(Suit.CLUBS, Number.FOUR),
    FIVE_OF_CLUBS(Suit.CLUBS, Number.FIVE),
    SIX_OF_CLUBS(Suit.CLUBS, Number.SIX),
    SEVEN_OF_CLUBS(Suit.CLUBS, Number.SEVEN),
    EIGHT_OF_CLUBS(Suit.CLUBS, Number.EIGHT),
    NINE_OF_CLUBS(Suit.CLUBS, Number.NINE),
    TEN_OF_CLUBS(Suit.CLUBS, Number.TEN),
    JACK_OF_CLUBS(Suit.CLUBS, Number.JACK),
    QUEEN_OF_CLUBS(Suit.CLUBS, Number.QUEEN),
    KING_OF_CLUBS(Suit.CLUBS, Number.KING),

    ACE_OF_SPADES(Suit.SPADES, Number.ACE),
    TWO_OF_SPADES(Suit.SPADES, Number.TWO),
    THREE_OF_SPADES(Suit.SPADES, Number.THREE),
    FOUR_OF_SPADES(Suit.SPADES, Number.FOUR),
    FIVE_OF_SPADES(Suit.SPADES, Number.FIVE),
    SIX_OF_SPADES(Suit.SPADES, Number.SIX),
    SEVEN_OF_SPADES(Suit.SPADES, Number.SEVEN),
    EIGHT_OF_SPADES(Suit.SPADES, Number.EIGHT),
    NINE_OF_SPADES(Suit.SPADES, Number.NINE),
    TEN_OF_SPADES(Suit.SPADES, Number.TEN),
    JACK_OF_SPADES(Suit.SPADES, Number.JACK),
    QUEEN_OF_SPADES(Suit.SPADES, Number.QUEEN),
    KING_OF_SPADES(Suit.SPADES, Number.KING);

    private final Suit suit;
    private final Number number;

    Card(Suit suit, Number number) {
        this.suit = suit;
        this.number = number;
    }

    public String getCardName() {
        return this.number.name + this.suit.name;
    }

    public boolean isAce() {
        return this.number == Number.ACE;
    }

    public Set<Integer> getScore() {
        return Set.copyOf(this.number.score);
    }

    public enum Suit {

        HEARTS("하트", true),
        DIAMONDS("다이아", true),
        CLUBS("클로버", false),
        SPADES("스페이드", false);

        private final String name;
        private final boolean red;

        Suit(String name, boolean red) {
            this.name = name;
            this.red = red;
        }

        public String getName() {
            return name;
        }
    }


    public enum Number {
        ACE("A", Set.of(1, 11)),
        TWO("2", Set.of(2)),
        THREE("3", Set.of(3)),
        FOUR("4", Set.of(4)),
        FIVE("5", Set.of(5)),
        SIX("6", Set.of(6)),
        SEVEN("7", Set.of(7)),
        EIGHT("8", Set.of(8)),
        NINE("9", Set.of(9)),
        TEN("10", Set.of(10)),
        JACK("J", Set.of(10)),
        QUEEN("Q", Set.of(10)),
        KING("K", Set.of(10));

        private final String name;
        private final Set<Integer> score;

        Number(String name, Set<Integer> score) {
            this.name = name;
            this.score = score;
        }
    }
}
