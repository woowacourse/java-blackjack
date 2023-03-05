package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DealerTest {
    Dealer dealer = new Dealer();

    @ParameterizedTest
    @MethodSource("cardProvider")
    @DisplayName("딜러는 패 합이 16이하면 카드를 한 장 받아야한다.")
    void shouldHit(Card card, boolean shouldHit) {
        dealer.receiveCard(new Card("6하트", 6));
        dealer.receiveCard(new Card("2클로버", 2));
        dealer.receiveCard(new Card("6다이아", 6));

        dealer.receiveCard(card);

        Assertions.assertThat(dealer.shouldHit()).isEqualTo(shouldHit);
    }

    private static Stream<Arguments> cardProvider() {
        return Stream.of(
                Arguments.of(new Card("A클로버", 11), true),
                Arguments.of(new Card("9하트", 9), false)
        );
    }
}
