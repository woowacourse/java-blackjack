package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(Arrays.asList(
                new Card(Suit.HEART, Face.ACE),
                new Card(Suit.CLOVER, Face.ACE)));
    }

    @Test
    @DisplayName("카드를 두장 넣으면, 사이즈가 2가 나온다.")
    void size() {

        assertThat(cards.getCardsAsList()).hasSize(2);
    }

    @Test
    @DisplayName("카드를 두장 넣고 한장 추가하면, 사이즈가 3이 나온다.")
    void add() {
        cards.add(new Card(Suit.CLOVER, Face.NINE));

        assertThat(cards.getCardsAsList()).hasSize(3);
    }

    @Test
    @DisplayName("22점이 나오는경우, Bust가 발생한다.")
    void bust() {
        cards.add(new Card(Suit.DIAMOND, Face.JACK));
        cards.add(new Card(Suit.DIAMOND, Face.KING));

        assertThat(cards.isBust()).isTrue();
    }

    @Test
    @DisplayName("카드 A,A 를 뽑으면, 12점이 나온다.")
    void calculateCardDoubleA() {

        assertThat(cards.getScore()).isEqualTo(12);
        assertThat(cards.getScore()).isNotEqualTo(2);
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 A,A,10를 뽑으면, 12점이 나온다.")
    void calculateCardDoubleAWithNumber() {
        cards.add(new Card(Suit.DIAMOND, Face.JACK));
        assertThat(cards.getScore()).isEqualTo(12);
        assertThat(cards.getScore()).isNotEqualTo(2);
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 A,A,A 를 뽑으면, 13점이 나온다.")
    void calculateCardTripleA() {
        cards = new Cards(Arrays.asList(
                new Card(Suit.HEART, Face.ACE),
                new Card(Suit.CLOVER, Face.ACE),
                new Card(Suit.SPADE, Face.ACE)));

        assertThat(cards.getScore()).isEqualTo(13);
        assertThat(cards.getScore()).isNotEqualTo(3);
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 A,A,A,10 를 뽑으면, 13점이 나온다.")
    void calculateCardTripleAWithNumber() {
        cards = new Cards(Arrays.asList(
                new Card(Suit.HEART, Face.ACE),
                new Card(Suit.CLOVER, Face.ACE),
                new Card(Suit.DIAMOND, Face.ACE),
                new Card(Suit.DIAMOND, Face.TEN)));

        assertThat(cards.getScore()).isEqualTo(13);
        assertThat(cards.getScore()).isNotEqualTo(3);
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 A,A,A,A 를 뽑으면, 14점이 나온다.")
    void calculateCardQuadA() {
        cards = new Cards(Arrays.asList(
                new Card(Suit.HEART, Face.ACE),
                new Card(Suit.CLOVER, Face.ACE),
                new Card(Suit.SPADE, Face.ACE),
                new Card(Suit.DIAMOND, Face.ACE)));

        assertThat(cards.getScore()).isEqualTo(14);
        assertThat(cards.getScore()).isNotEqualTo(4);
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 A,A,A,A,10 를 뽑으면, 14점이 나온다.")
    void calculateCardQuadAWithNumber() {
        cards = new Cards(Arrays.asList(
                new Card(Suit.HEART, Face.ACE),
                new Card(Suit.CLOVER, Face.ACE),
                new Card(Suit.SPADE, Face.ACE),
                new Card(Suit.DIAMOND, Face.ACE),
                new Card(Suit.DIAMOND, Face.TEN)));

        assertThat(cards.getScore()).isEqualTo(14);
        assertThat(cards.getScore()).isNotEqualTo(24);
        assertThat(cards.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 A,A,A,A,10,10 를 뽑으면, 24점이 나오고 bust한다.")
    void calculateCardBusted() {
        cards = new Cards(Arrays.asList(
                new Card(Suit.HEART, Face.ACE),
                new Card(Suit.CLOVER, Face.ACE),
                new Card(Suit.SPADE, Face.ACE),
                new Card(Suit.DIAMOND, Face.ACE),
                new Card(Suit.DIAMOND, Face.TEN),
                new Card(Suit.SPADE, Face.TEN)));

        assertThat(cards.getScore()).isEqualTo(24);
        assertThat(cards.isBust()).isTrue();
    }
}
