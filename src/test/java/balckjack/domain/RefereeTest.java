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
        deck.addCard(new CourtCard("K"));
        deck.addCard(new StandardCard("4"));
        Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(14);
    }

    @Test
    void testBurstCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new CourtCard("K"));
        deck.addCard(new CourtCard("J"));
        deck.addCard(new CourtCard("Q"));

        Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(-1);
    }


    @Nested
    class aceCase {

        @Test
        void testOneAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard("K"));
            deck.addCard(new StandardCard("4"));
            deck.addCard(new AceCard());
            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(15);
        }

        @Test
        void testElevenAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard("2"));
            deck.addCard(new StandardCard("4"));
            deck.addCard(new AceCard());

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(17);
        }

        @Test
        void testManyAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard("2"));
            deck.addCard(new AceCard());
            deck.addCard(new AceCard());
            deck.addCard(new StandardCard("4"));
            deck.addCard(new AceCard());

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(19);
        }

        @Test
        void testManyAceBurstCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard("K"));
            deck.addCard(new AceCard());
            deck.addCard(new AceCard());
            deck.addCard(new CourtCard("J"));
            deck.addCard(new AceCard());

            Assertions.assertThat(referee.calculateDeckScore(deck)).isEqualTo(-1);
        }
    }


}