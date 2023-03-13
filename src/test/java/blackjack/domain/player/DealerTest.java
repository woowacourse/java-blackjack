package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.CardFixture;
import blackjack.domain.card.Card;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private static Stream<Arguments> challengerCards() {
        return Stream.of(
                Arguments.of(List.of(CardFixture.CLOVER_KING, CardFixture.SPADE_SIX), Result.LOSE),
                Arguments.of(List.of(CardFixture.CLOVER_ACE, CardFixture.CLOVER_KING), Result.BLACKJACK),
                Arguments.of(List.of(CardFixture.DIAMOND_JACK, CardFixture.CLOVER_KING), Result.DRAW));
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(List.of(CardFixture.DIAMOND_JACK, CardFixture.SPADE_SIX), true),
                Arguments.of(List.of(CardFixture.DIAMOND_JACK, CardFixture.HEART_SEVEN), false));
    }

    @ParameterizedTest
    @MethodSource("challengerCards")
    @DisplayName("다른 Challenger의 게임 결과를 올바르게 판단하는지 확인한다")
    void judge(List<Card> cards, Result expected) {
        Challenger challenger = new Challenger("oing");
        challenger.pickStartCards(cards.get(0), cards.get(1));

        Dealer dealer = new Dealer();
        dealer.pickStartCards(CardFixture.CLOVER_KING, CardFixture.DIAMOND_JACK);

        assertThat(dealer.judge(challenger)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("카드를 뽑을 수 있는지 확인한다")
    void checking_sum_is_over_16(List<Card> cards, boolean expected) {
        Player player = new Dealer();
        for (Card card : cards) {
            player.pickCard(card);
        }

        assertThat(player.canPick()).isEqualTo(expected);
    }
}
