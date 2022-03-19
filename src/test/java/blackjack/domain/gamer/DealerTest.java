package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardShape;
import blackjack.domain.gamer.role.Dealer;

class DealerTest {

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("invalidParameters")
    @DisplayName("딜러 카드 더하기 테스트")
    void addCard(Card card, int result, String testName) {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardNumber.Q));
        dealer.addCard(card);

        dealer.addCard(new Card(CardShape.HEART, CardNumber.A));
        assertThat(dealer.getScore()).isEqualTo(result);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(new Card(CardShape.HEART, CardNumber.FIVE), 16, "카드 숫자 합 15에서 Ace 추가시에 16"),
                Arguments.of(new Card(CardShape.HEART, CardNumber.SIX), 17, "카드 숫자 합 16에서 Ace 추가시에 17"),
                Arguments.of(new Card(CardShape.HEART, CardNumber.SEVEN), 17, "카드 숫자 합 17에서 Ace 추가시에 17")
        );
    }
}
