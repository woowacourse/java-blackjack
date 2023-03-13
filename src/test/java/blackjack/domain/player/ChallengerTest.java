package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.domain.CardFixture;
import blackjack.domain.card.Card;
import blackjack.exception.InvalidPlayerNameException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChallengerTest {

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(List.of(CardFixture.CLOVER_KING, CardFixture.CLOVER_FIVE), true),
                Arguments.of(List.of(CardFixture.DIAMOND_JACK, CardFixture.CLOVER_FIVE, CardFixture.HEART_EIGHT),
                        false));
    }

    @Test
    @DisplayName("이름이 '딜러'인 경우 예외가 발생한다")
    void validate_name() {
        assertThrows(InvalidPlayerNameException.class, () -> new Challenger("딜러"));
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("카드를 뽑을 수 있는지 확인한다.")
    void checking_sum_is_over_21(List<Card> cards, boolean expected) {
        Player player = new Challenger("neo");
        for (Card card : cards) {
            player.pickCard(card);
        }

        assertThat(player.canPick()).isEqualTo(expected);
    }
}
