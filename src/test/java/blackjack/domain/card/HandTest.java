package blackjack.domain.card;

import static blackjack.domain.player.Result.BLACKJACK_WIN;
import static blackjack.domain.player.Result.LOSE;
import static blackjack.domain.player.Result.PUSH;
import static blackjack.domain.player.Result.WIN;
import static blackjack.util.CardFixture.ACE_HEART;
import static blackjack.util.CardFixture.ACE_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Result;
import blackjack.util.CardFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class HandTest {

    static Stream<Arguments> playBlackjackSource() {
        return Stream.of(
                Arguments.of(CardFixture.BLACKJACK, CardFixture.BLACKJACK, PUSH),
                Arguments.of(CardFixture.BLACKJACK, CardFixture.valueOf(20), BLACKJACK_WIN),
                Arguments.of(CardFixture.BLACKJACK, CardFixture.valueOf(21), BLACKJACK_WIN),
                Arguments.of(CardFixture.valueOf(20), CardFixture.BLACKJACK, LOSE),
                Arguments.of(CardFixture.BUST, CardFixture.BLACKJACK, LOSE),
                Arguments.of(CardFixture.valueOf(20), CardFixture.valueOf(18), WIN),
                Arguments.of(CardFixture.valueOf(20), CardFixture.valueOf(20), PUSH),
                Arguments.of(CardFixture.valueOf(17), CardFixture.valueOf(20), LOSE),
                Arguments.of(CardFixture.valueOf(17), CardFixture.BUST, WIN),
                Arguments.of(CardFixture.BUST, CardFixture.valueOf(17), LOSE),
                Arguments.of(CardFixture.BUST, CardFixture.BUST, LOSE)
        );
    }

    static Stream<Arguments> isPlayableSource() {
        return Stream.of(
                Arguments.of(CardFixture.BLACKJACK, false),
                Arguments.of(CardFixture.BUST, false),
                Arguments.of(CardFixture.valueOf(20), true)
        );
    }

    @Test
    void 카드를_입력받아_핸드를_생성한다() {
        final List<Card> cards = CardFixture.BLACKJACK;

        final Hand hand = new Hand(cards);

        assertThat(hand.score()).isEqualTo(21);
    }

    @ParameterizedTest(name = "승패를 구한다. 겜블러: {0}, 딜러: {1}, 결과: {2}")
    @MethodSource("playBlackjackSource")
    void 승패를_구한다(final List<Card> cards, final List<Card> dealerCards, final Result expectedResult) {
        final Hand hand = new Hand(cards);
        final Hand dealerHand = new Hand(dealerCards);

        final Result result = hand.play(dealerHand);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void 카드가_추가된다() {
        final Hand hand = new Hand();

        hand.add(ACE_HEART);

        assertThat(hand.getSymbols()).containsExactly("A하트");
    }

    @ParameterizedTest(name = "카드를 뽑을 수 있는지 확인한다. 입력: {0}, 결과: {1}")
    @MethodSource("isPlayableSource")
    void 카드를_뽑을_수_있는지_확인한다(final List<Card> cards, final boolean result) {
        final Hand hand = new Hand(cards);

        assertThat(hand.isPlayable()).isEqualTo(result);
    }

    @Test
    void 점수_산정에_들어갈_결과값을_반환한다() {
        final Hand hand = new Hand(List.of(ACE_SPADE));

        final int result = hand.score();

        assertThat(result).isEqualTo(11);
    }

    @Test
    void 카드를_더_뽑을_수_없는_상태로_변경한다() {
        final Hand hand = new Hand(CardFixture.valueOf(19));

        hand.stay();

        assertThat(hand.isPlayable()).isFalse();
    }
}
