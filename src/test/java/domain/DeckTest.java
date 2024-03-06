package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱에 카드가 잘 추가된다.")
    void addCardTest() {
        Deck deck = new Deck();
        Card card = new Card(Shape.CLOVER, Rank.EIGHT);

        deck.addCard(card);
        Card result = deck.pickRandomCard();

        assertThat(result).isEqualTo(card);
    }

    @Test
    @DisplayName("랜덤 카드를 선택한다.")
    void pickRandomCardTest() {
        Deck deck = new Deck();

        Card card = new Card(Shape.HEART, Rank.EIGHT);
        deck.addCard(card);
        Card result = deck.pickRandomCard();

        assertThat(result).isEqualTo(card);
    }

    @Test
    @DisplayName("카드의 합을 계산한다.")
    void deckCalculateTotalScore() {
        Deck deck = new Deck();
        deck.addCard(new Card(Shape.HEART, Rank.SEVEN));
        deck.addCard(new Card(Shape.HEART, Rank.JACK));

        int result = deck.calculateTotalScore();

        assertThat(result).isEqualTo(17);
    }

    @Test
    @DisplayName("카드가 없는 경우 예외가 발생한다.")
    void validateDeckTest() {
        Deck deck = new Deck();

        Assertions.assertThatThrownBy(deck::calculateTotalScore)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("최적의 에이스 값, 11을 선택한다.")
    void calculateAceScoreWithSingleTest() {
        Deck deck = new Deck();
        deck.addCard(new Card(Shape.HEART, Rank.ACE));
        deck.addCard(new Card(Shape.HEART, Rank.JACK));

        int result = deck.calculateTotalScore();

        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("최적의 에이스 값, 11과 1을 선택한다.")
    void calculateAceScoreWithDoubleTest() {
        Deck deck = new Deck();
        deck.addCard(new Card(Shape.HEART, Rank.ACE));
        deck.addCard(new Card(Shape.CLOVER, Rank.ACE));

        int result = deck.calculateTotalScore();

        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("최적의 에이스 값, 11, 1, 1을 선택한다.")
    void calculateAceScoreWithTripleTest() {
        Deck deck = new Deck();
        deck.addCard(new Card(Shape.HEART, Rank.ACE));
        deck.addCard(new Card(Shape.CLOVER, Rank.ACE));
        deck.addCard(new Card(Shape.DIAMOND, Rank.ACE));

        int result = deck.calculateTotalScore();

        assertThat(result).isEqualTo(13);
    }

    @Test
    @DisplayName("최적의 에이스 값, 1과 1을 선택한다.")
    void calculateAceScoreWithDiverseTest() {
        Deck deck = new Deck();
        deck.addCard(new Card(Shape.HEART, Rank.JACK));
        deck.addCard(new Card(Shape.CLOVER, Rank.NINE));
        deck.addCard(new Card(Shape.DIAMOND, Rank.ACE));
        deck.addCard(new Card(Shape.DIAMOND, Rank.ACE));

        int result = deck.calculateTotalScore();

        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("카드의 개수를 확인한다.")
    void sizeTest() {
        Deck deck = new Deck();
        assertThat(deck.size()).isEqualTo(0);
    }
}
