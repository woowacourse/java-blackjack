package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {

    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of(Arrays.asList(
                new Card(Symbol.ACE, Shape.HEART),
                new Card(Symbol.FIVE, Shape.HEART)
            ), true), // 합 : 16
            Arguments.of(Arrays.asList(
                new Card(Symbol.ACE, Shape.HEART),
                new Card(Symbol.SIX, Shape.HEART)
            ), false) // 합 : 17
        );
    }

    @ParameterizedTest
    @DisplayName("딜러는 ace를 11로 계산하고, 카드의 합계가 16이하일 때 1장 더 받을 수 있다.")
    @MethodSource("generateData")
    public void isAbleToReceiveCard(List<Card> inputCards, boolean result) {
        Cards cards = new Cards(inputCards);
        Dealer dealer = new Dealer();
        dealer.receiveCards(cards);
        assertThat(dealer.isAbleToReceiveCard()).isEqualTo(result);
    }
}
