package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    @ParameterizedTest
    @MethodSource("challengerCards")
    @DisplayName("다른 Challenger의 게임 결과를 올바르게 판단하는지 확인한다")
    void judge(List<Card> cards, Result expected) {
        Challenger challenger = new Challenger("oing");
        challenger.pickStartCards(cards.get(0), cards.get(1));

        Dealer dealer = new Dealer();
        dealer.pickStartCards(new Card(Shape.HEART, Symbol.KING), new Card(Shape.SPADE, Symbol.QUEEN));

        assertThat(dealer.judge(challenger)).isEqualTo(expected);
    }

    private static Stream<Arguments> challengerCards() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.SIX)),
                        Result.LOSE),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.ACE),
                                new Card(Shape.CLOVER, Symbol.KING)),
                        Result.BLACKJACK),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.JACK),
                                new Card(Shape.CLOVER, Symbol.KING)),
                        Result.DRAW)
        );
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

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.SIX)),
                        true),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.SEVEN)),
                        false)
        );
    }
}
