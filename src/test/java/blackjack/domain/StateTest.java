package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StateTest {

    @ParameterizedTest
    @MethodSource("provideBustTest")
    @DisplayName("카드의 총합이 21이 넘는 경우 버스트")
    void bust(Cards cards, boolean expected) {
        // when
        State actual = State.from(cards);

        // then
        assertThat(actual == State.BUST).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBustTest() {
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Card HEART_TWO = new Card(Pattern.HEART, Denomination.TWO);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);

        Cards bustCards = new Cards(List.of(DIAMOND_TEN, CLOVER_TEN));
        Cards notBustCards = new Cards(List.of(DIAMOND_TEN, CLOVER_TEN));

        bustCards.add(HEART_TWO);
        notBustCards.add(SPADE_ACE);

        return Stream.of(
                Arguments.of(bustCards, true),
                Arguments.of(notBustCards, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBlackJackTest")
    @DisplayName("점수의 총합이 21이면서 2장이면 블랙잭이다.")
    void blackJack(Cards cards, boolean expected) {
        // when
        State actual = State.from(cards);

        // then
        assertThat(actual == State.BLACKJACK).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBlackJackTest() {
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Card SPADE_ACE = new Card(Pattern.SPADE, Denomination.ACE);

        Cards blackJackCards = new Cards(List.of(DIAMOND_TEN, SPADE_ACE));
        Cards notBlackJackCards = new Cards(List.of(DIAMOND_TEN, SPADE_ACE));

        notBlackJackCards.add(CLOVER_TEN);

        return Stream.of(
                Arguments.of(blackJackCards, true),
                Arguments.of(notBlackJackCards, false)
        );
    }

    @Test
    @DisplayName("버스트도 블랙잭도 아니면 NOTHING을 반환한다")
    void nothing() {
        // given
        Card DIAMOND_TEN = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card CLOVER_TEN = new Card(Pattern.CLOVER, Denomination.TEN);
        Card HEART_ACE = new Card(Pattern.HEART, Denomination.ACE);

        Cards cards = new Cards(List.of(DIAMOND_TEN, CLOVER_TEN));
        cards.add(HEART_ACE);

        // when
        State actual = State.from(cards);

        // then
        assertThat(actual).isEqualTo(State.NOTHING);
    }
}
