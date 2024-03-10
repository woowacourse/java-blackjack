package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandsTest {

    @Test
    @DisplayName("손패에 카드를 추가한다.")
    void receiveCardTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TEN)
        ));

        hands.addCard(new Card(Shape.HEART, Rank.EIGHT));

        Assertions.assertThat(hands.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("손패에 있는 카드 점수를 계산한다.")
    void calculateScoreTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.TEN)
        ));

        int result = hands.calculateScore();

        Assertions.assertThat(result).isEqualTo(20);
    }

    @Test
    @DisplayName("최적의 에이스 값, 11을 선택한다.")
    void calculateAceScoreWithSingleTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.JACK)
        ));

        int result = hands.calculateScore();

        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("최적의 에이스 값, 11과 1을 선택한다.")
    void calculateAceScoreWithDoubleTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.ACE)
        ));

        int result = hands.calculateScore();

        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("최적의 에이스 값, 11, 1, 1을 선택한다.")
    void calculateAceScoreWithTripleTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.SPADE, Rank.ACE)
        ));

        int result = hands.calculateScore();

        assertThat(result).isEqualTo(13);
    }

    @Test
    @DisplayName("최적의 에이스 값, 1과 1을 선택한다.")
    void calculateAceScoreWithDiverseTest() {
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.CLOVER, Rank.NINE),
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.ACE)
        ));

        int result = hands.calculateScore();

        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("카드의 개수를 확인한다.")
    void sizeTest() {
        Hands hands = new Hands(new ArrayList<>());
        assertThat(hands.size()).isEqualTo(0);
    }
}
