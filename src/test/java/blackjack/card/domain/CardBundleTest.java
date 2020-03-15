package blackjack.card.domain;

import blackjack.card.domain.component.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.card.domain.CardBundleHelper.aCardBundle;
import static blackjack.card.domain.component.Symbol.HEART;
import static org.assertj.core.api.Assertions.assertThat;

class CardBundleTest {

    private static Stream<Arguments> cardBundleProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.TEN), true),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.NINE), false),
                Arguments.of(aCardBundle(CardNumber.ACE, CardNumber.TEN, CardNumber.ACE), false)
        );
    }

    private static Stream<Arguments> isBurstProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.TWO), true),
                Arguments.of(aCardBundle(CardNumber.KING, CardNumber.ACE), false)
        );
    }

    @DisplayName("카드의 점수 계산 로직, ACE가 있는 경우에 21을 넘지 않으면 10을 더한 점수를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE, 20", "TWO,21"})
    void calculateScore(CardNumber number, int expect) {
        //given
        CardBundle cardBundle = CardBundle.emptyBundle();
        cardBundle.addCard(Card.of(HEART, CardNumber.ACE));
        cardBundle.addCard(Card.of(HEART, CardNumber.EIGHT));
        cardBundle.addCard(Card.of(HEART, number));

        //when
        int actual = cardBundle.calculateScore();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("CardBundle 이 블랙잭인지 아닌지 확인")
    @ParameterizedTest
    @MethodSource("cardBundleProvider")
    void isBlackjack(CardBundle cardBundle, boolean result) {
        assertThat(cardBundle.isBlackjack()).isEqualTo(result);
    }

    @DisplayName("CardBundle 이 Burst(>21) 인지 확인")
    @ParameterizedTest
    @MethodSource("isBurstProvider")
    void isBurst(CardBundle cardBundle, boolean result) {
        assertThat(cardBundle.isBurst()).isEqualTo(result);
    }
}