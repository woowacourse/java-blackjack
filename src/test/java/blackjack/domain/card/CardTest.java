package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CardTest {

    @DisplayName("카드 52개 생성 확인")
    @Test
    void test1() {
        int actualCardDeckSize = Card.create().size();

        assertThat(actualCardDeckSize).isEqualTo(52);
    }

    @DisplayName("52종류 카드 객체 1회 생성 확인")
    @Test
    void test2() {
        List<Card> cards = Card.create();
        List<Card> expectedCards = Card.create();

        Card card = Card.of(Type.TWO, Figure.HEART);
        Card expectedCard = Card.of(Type.TWO, Figure.HEART);

        assertThat(card == expectedCard).isTrue();
        assertThat(cards).isEqualTo(expectedCards);
    }

    @DisplayName("에이스 카드 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE, true", "TWO, false", "KING, false"})
    void test3(Type type, boolean expected) {
        Card card = Card.of(type, Figure.DIAMOND);

        assertThat(card.isAce()).isEqualTo(expected);
    }

    @DisplayName("카드의 값 확인")
    @ParameterizedTest
    @CsvSource(value = {"TWO, 2", "KING, 10", "QUEEN, 10", "JACK, 10", "ACE, 11"})
    void test4(Type type, int expectedValue) {
        Card card = Card.of(type, Figure.HEART);

        int actualValue = card.cardValue();

        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @DisplayName("카드 정보 확인")
    @ParameterizedTest
    @CsvSource(value = {"TWO, DIAMOND, 2다이아몬드", "JACK, CLOVER, J클로버", "KING, HEART, K하트", "ACE, SPADE, A스페이드"})
    void test5(Type type, Figure figure, String expectedInfo) {
        Card card = Card.of(type, figure);

        String actualInfo = card.cardInfo();

        assertThat(actualInfo).isEqualTo(expectedInfo);
    }
}
