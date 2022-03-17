package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardShape;

class CardGroupTest {

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("invalidParameters")
    @DisplayName("카드의 합을 테스트한다.")
    void getSum(Card card, int result, String testName) {
        CardGroup cardGroup = new CardGroup();
        cardGroup.addCard(new Card(CardShape.HEART, CardNumber.TWO));
        cardGroup.addCard(new Card(CardShape.HEART, CardNumber.Q));
        cardGroup.addCard(card);
        assertThat(cardGroup.getScore()).isEqualTo(result);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(new Card(CardShape.HEART, CardNumber.THREE), 15, "카드 숫자 합 12에서 3 추가시에 15"),
                Arguments.of(new Card(CardShape.HEART, CardNumber.K), 22, "카드 숫자 합 12에서 10 추가시에 22")
        );
    }

    @Test
    @DisplayName("카드의 수가 2개이고, 점수가 21점이면 블랙잭이다.")
    void isBlackJack() {
        CardGroup cardGroup = new CardGroup();
        cardGroup.addCard(new Card(CardShape.HEART, CardNumber.A));
        cardGroup.addCard(new Card(CardShape.HEART, CardNumber.Q));
        assertThat(cardGroup.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드의 수가 3개이고, 점수가 21점이면 블랙잭이 아니다.")
    void isNotBlackJack() {
        CardGroup cardGroup = new CardGroup();
        cardGroup.addCard(new Card(CardShape.HEART, CardNumber.K));
        cardGroup.addCard(new Card(CardShape.HEART, CardNumber.NINE));
        cardGroup.addCard(new Card(CardShape.HEART, CardNumber.TWO));
        assertThat(cardGroup.isBlackJack()).isFalse();
    }
}
