package balckjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RefereeTest {

    Referee referee;

    @BeforeEach
    void init() {
        referee = new Referee();
    }

    @Test
    void testCommonCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new CourtCard(Pattern.SPADE, "K"));
        deck.addCard(new StandardCard(Pattern.SPADE, "4"));
        Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(14);
    }

    @Test
    void testBurstCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new CourtCard(Pattern.SPADE, "K"));
        deck.addCard(new CourtCard(Pattern.SPADE, "J"));
        deck.addCard(new CourtCard(Pattern.SPADE, "Q"));

        Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(-1);
    }


    @Nested
    class aceCase {

        @Test
        void testOneAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard(Pattern.SPADE, "K"));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.HEART));
            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(15);
        }

        @Test
        void testElevenAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard(Pattern.SPADE, "2"));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.HEART));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(17);
        }

        @Test
        void testManyAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard(Pattern.SPADE, "2"));
            deck.addCard(new AceCard(Pattern.HEART));
            deck.addCard(new AceCard(Pattern.CLUB));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.DIAMOND));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(19);
        }

        @Test
        void testManyAceBurstCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard(Pattern.SPADE, "K"));
            deck.addCard(new AceCard(Pattern.SPADE));
            deck.addCard(new AceCard(Pattern.HEART));
            deck.addCard(new CourtCard(Pattern.SPADE, "J"));
            deck.addCard(new AceCard(Pattern.CLUB));

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(-1);
        }
    }


}