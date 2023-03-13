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
        deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
        deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
        Assertions.assertThat(deck.getCards().size()).isEqualTo(2);
    }

    @Test
    void calculateCardsScoreNotIncludeAce() {
        deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
        deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
        Assertions.assertThat(deck.calculateScore()).isEqualTo(new Score(14));
    }

    @Test
    void isBlackJack() {
        deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
        deck.addCard(new Card(Pattern.SPADE, Denomination.ACE));
        Assertions.assertThat(deck.isBlackJack()).isTrue();
    }

    @Test
    void isNotBlackJack() {
        deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
        deck.addCard(new Card(Pattern.SPADE, Denomination.SIX));
        deck.addCard(new Card(Pattern.SPADE, Denomination.ACE));
        Assertions.assertThat(deck.isBlackJack()).isFalse();
    }


    @Nested
    class appendAceCase {

        @Test
        void testOneAceCase() {
            deck.addCard(new Card(Pattern.SPADE, Denomination.KING));
            deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
            deck.addCard(new Card(Pattern.HEART, Denomination.ACE));
            Assertions.assertThat(deck.calculateScore()).isEqualTo(new Score(15));
        }

        @Test
        void testElevenAceCase() {
            deck.addCard(new Card(Pattern.SPADE, Denomination.TWO));
            deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
            deck.addCard(new Card(Pattern.HEART, Denomination.ACE));

            Assertions.assertThat(deck.calculateScore()).isEqualTo(new Score(17));
        }

        @Test
        void testManyAceCase() {
            deck.addCard(new Card(Pattern.SPADE, Denomination.TWO));
            deck.addCard(new Card(Pattern.HEART, Denomination.ACE));
            deck.addCard(new Card(Pattern.CLOVER, Denomination.ACE));
            deck.addCard(new Card(Pattern.SPADE, Denomination.FOUR));
            deck.addCard(new Card(Pattern.DIAMOND, Denomination.ACE));

            Assertions.assertThat(deck.calculateScore()).isEqualTo(new Score(19));
        }
    }

}