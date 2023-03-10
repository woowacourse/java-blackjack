package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("가진 카드의 합이 16 초과인지 확인한다")
    void checking_sum_is_over_16(List<Card> cards, boolean expected) {
        for (Card card : cards) {
            dealer.pick(card);
        }

        assertThat(dealer.canPick()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Number.QUEEN),
                                new Card(Shape.CLOVER, Number.FOUR)),
                        true),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Number.QUEEN),
                                new Card(Shape.CLOVER, Number.SEVEN)),
                        false)
        );
    }

    @Test
    @DisplayName("딜러의 이름은 '딜러'이다")
    void dealer_name_is_dealer() {
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("딜러는 도전자가 아니다")
    void dealer_is_not_challenger() {
        assertThat(dealer.isDealer()).isTrue();
        assertThat(dealer.isChallenger()).isFalse();
    }
}
