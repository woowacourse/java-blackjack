package blackjack.model.blackjack_player.player.betting;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.dealer.result.Result;
import blackjack.model.card.BlackJackCard;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardNumber;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BettingTest {

    private static Stream<Arguments> Result를_통해_잔액을_변경할_수_있다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        Betting.bet(100),
                        Result.DEALER_WIN,
                        makeHand(),
                        0
                ),
                Arguments.of(
                        Betting.bet(100),
                        Result.DRAW,
                        makeHand(),
                        100
                ),
                Arguments.of(
                        Betting.bet(100),
                        Result.DEALER_LOSE,
                        makeHand(createCard(CardNumber.ACE), createCard(CardNumber.TEN)),
                        250
                ),
                Arguments.of(
                        Betting.bet(100),
                        Result.DEALER_LOSE,
                        makeHand(createCard(CardNumber.SIX), createCard(CardNumber.TEN)),
                        200
                )
        );
    }

    private static Hand makeHand(final BlackJackCard... blackJackCards) {
        final Hand hand = Hand.empty();
        hand.addCards(new BlackJackCards(blackJackCards));
        return hand;
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    void 배팅_금액은_음수로_초기화할_수_없다(final int bettingMoney) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Betting.bet(bettingMoney));
    }

    @ParameterizedTest
    @MethodSource("Result를_통해_잔액을_변경할_수_있다_테스트_케이스")
    void Result_를_통해_잔액을_변경할_수_있다(
            final Betting betting,
            final Result result,
            final Hand hand,
            final int expectedBalance
    ) {

        betting.applyResult(result, hand);

        assertThat(betting.getBalance()).isEqualTo(expectedBalance);
    }
}
