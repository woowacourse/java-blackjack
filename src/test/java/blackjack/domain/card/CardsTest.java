package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardsTest {
    private static Stream<Arguments> provideCardsAndExpectedScore() {
        return Stream.of(
                Arguments.of(new Cards(new Card(CardNumber.ACE, Shape.CLOVER),
                        new Card(CardNumber.ACE, Shape.CLOVER)),12),
                Arguments.of(new Cards(new Card(CardNumber.ACE, Shape.CLOVER),
                        new Card(CardNumber.KING, Shape.CLOVER)),21)
        );
    }

    @Test
    @DisplayName("카드뭉치가 블랙잭일 때")
    void isBlackJackTest() {
        Cards cards = new Cards(new Card(CardNumber.ACE, Shape.CLOVER), new Card(CardNumber.KING, Shape.DIAMOND));
        assertTrue(cards.isBlackjack());
    }

    @Test
    @DisplayName("카드뭉치가 블랙잭이 아닐 때")
    void isntBlackJackTest() {
        Cards cards = new Cards(new Card(CardNumber.ACE, Shape.CLOVER), new Card(CardNumber.EIGHT, Shape.DIAMOND));
        cards.addCard(new Card(CardNumber.TWO, Shape.SPADE));
        assertFalse(cards.isBlackjack());
    }

    @ParameterizedTest
    @DisplayName("카드 뭉치 점수 확인")
    @MethodSource("provideCardsAndExpectedScore")
    void cardsScoreTest(Cards cards, int expectedScore) {
        assertThat(cards.calculateCardsScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 정보 확인")
    void cardInfoTest() {
        Cards cards = new Cards(new Card(CardNumber.ACE, Shape.CLOVER), new Card(CardNumber.EIGHT, Shape.DIAMOND));
        assertThat(cards.getFirstCardInfoToString()).isEqualTo("A클로버");
        assertThat(cards.getCardsInfoToString()).isEqualTo("A클로버, 8다이아몬드");
    }
}