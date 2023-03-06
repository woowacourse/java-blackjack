package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    CardDeck deck;

    @BeforeEach
    void init() {
        deck = new CardDeck();
    }

    @Test
    void addNullCard() {
        Assertions.assertThatThrownBy(() -> deck.addCard(null))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("아무런 카드도 입력되지 않았습니다.");
    }

    @Test
    void findCardCount() {
        deck.addCard(new CourtCard(Pattern.SPADE, "K"));
        deck.addCard(new StandardCard(Pattern.SPADE, "4"));
        Assertions.assertThat(deck.getCardCount()).isEqualTo(2);
    }

    @Test
    void testCommonCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new CourtCard(Pattern.SPADE, "K"));
        deck.addCard(new StandardCard(Pattern.SPADE, "4"));
        Assertions.assertThat(deck.calculateScore(deck)).isEqualTo(14);
    }

    @Test
    void testBurstCase() {
        CardDeck deck = new CardDeck();
        deck.addCard(new CourtCard(Pattern.SPADE, "K"));
        deck.addCard(new CourtCard(Pattern.SPADE, "J"));
        deck.addCard(new CourtCard(Pattern.SPADE, "Q"));

        Assertions.assertThat(deck.calculateScore(deck)).isEqualTo(-1);
    }


    @Nested
    class appendAceCase {

        @Test
        void testOneAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard(Pattern.SPADE, "K"));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.HEART));
            Assertions.assertThat(deck.calculateScore(deck)).isEqualTo(15);
        }

        @Test
        void testElevenAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard(Pattern.SPADE, "2"));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.HEART));

            Assertions.assertThat(deck.calculateScore(deck)).isEqualTo(17);
        }

        @Test
        void testManyAceCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new StandardCard(Pattern.SPADE, "2"));
            deck.addCard(new AceCard(Pattern.HEART));
            deck.addCard(new AceCard(Pattern.CLUB));
            deck.addCard(new StandardCard(Pattern.SPADE, "4"));
            deck.addCard(new AceCard(Pattern.DIAMOND));

            Assertions.assertThat(deck.calculateScore(deck)).isEqualTo(19);
        }

        @Test
        void testManyAceBurstCase() {
            CardDeck deck = new CardDeck();
            deck.addCard(new CourtCard(Pattern.SPADE, "K"));
            deck.addCard(new AceCard(Pattern.SPADE));
            deck.addCard(new AceCard(Pattern.HEART));
            deck.addCard(new CourtCard(Pattern.SPADE, "J"));
            deck.addCard(new AceCard(Pattern.CLUB));

            Assertions.assertThat(deck.calculateScore(deck)).isEqualTo(-1);
        }
    }
}