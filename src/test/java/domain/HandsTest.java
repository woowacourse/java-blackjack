package domain;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.JACK;
import static domain.card.Rank.NINE;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;

class HandsTest {

    final Hands sum20Size2 = new Hands(
            List.of(new Card(NINE, SPADE), new Card(ACE, SPADE)));
    final Hands sum20Size3 = new Hands(
            List.of(new Card(FOUR, SPADE), new Card(FIVE, SPADE),
                    new Card(ACE, SPADE)));
    final Hands sum21Size2 = new Hands(
            List.of(new Card(JACK, HEART),
                    new Card(ACE, SPADE)));
    final Hands sum22Size3 = new Hands(
            List.of(new Card(NINE, HEART), new Card(EIGHT, SPADE),
                    new Card(FIVE, HEART)));
    final Hands blackJack = new Hands(
            List.of(new Card(JACK, HEART), new Card(ACE, SPADE)));

    @Test
    @DisplayName("카드를 가지고 있는 객체를 생성한다.")
    void createPacket() {
        Assertions.assertThatCode(Hands::createEmptyPacket)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 추가한다.")
    void addCard() {
        //given
        final Hands hands = Hands.createEmptyPacket();

        //when
        hands.add(new Card(EIGHT, CLOVER));

        //then
        Assertions.assertThat(hands.size()).isEqualTo(1);
    }

    @DisplayName("카드의 합을 구한다.")
    @Test
    void sum() {
        Assertions.assertThat(sum20Size2.sum()).isEqualTo(20);
    }

    @DisplayName("에이스를 11로 계산한다.")
    @ParameterizedTest
    @MethodSource("sumAce11ParameterProvider")
    void sumAce11(final Hands hands, final int expected) {
        // given & when
        final int result = hands.sum();

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("에이스를 1로 계산한다.")
    @ParameterizedTest
    @MethodSource("sumAce1ParameterProvider")
    void sumAce1(final Hands hands, final int expected) {
        // given & when
        final int result = hands.sum();

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("카드 합이 같고 카드 갯수가 같으면 무승부이다.")
    @Test
    void isTie() {
        // given
        final Hands dealerSum10Size3 = new Hands(
                List.of(new Card(SEVEN, SPADE), new Card(TWO, SPADE),
                        new Card(ACE, SPADE)));
        final Hands playerSum10Size3 = new Hands(
                List.of(new Card(FOUR, SPADE), new Card(FIVE, SPADE),
                        new Card(ACE, SPADE)));

        // when && then
        Assertions.assertThat(dealerSum10Size3.calculateResult(playerSum10Size3)).isEqualTo(Result.TIE);
    }

    @DisplayName("카드 합이 같은데 카드 갯수가 더 적으면 승리이다.")
    @Test
    void isWinBySize() {
        Assertions.assertThat(sum20Size2.calculateResult(sum20Size3)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("카드 합이 21이하이면서 21에 가까운 카드가 승리한다.")
    void isWin() {
        Assertions.assertThat(sum21Size2.calculateResult(sum20Size2)).isEqualTo(Result.WIN);
        Assertions.assertThat(sum20Size2.calculateResult(sum21Size2)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("카드 합이 21초과이면 패배한다.")
    void isLoseWhenCardSumGreater21() {
        Assertions.assertThat(sum22Size3.calculateResult(sum20Size2)).isEqualTo(Result.LOSE);
    }


    @Test
    @DisplayName("blackjack이 이긴다.")
    void isWinBlackJack() {
        Assertions.assertThat(blackJack.calculateResult(sum20Size2)).isEqualTo(Result.WIN);
        Assertions.assertThat(sum20Size2.calculateResult(blackJack)).isEqualTo(Result.LOSE);
    }

    static Stream<Arguments> sumAce11ParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(ACE, HEART),
                                new Card(EIGHT, SPADE),
                                new Card(TWO, CLOVER))),
                        21),
                Arguments.of(new Hands(List.of(new Card(ACE, DIAMOND),
                                new Card(TWO, CLOVER),
                                new Card(FOUR, CLOVER),
                                new Card(TWO, CLOVER))),
                        19)
        );
    }

    static Stream<Arguments> sumAce1ParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(ACE, HEART),
                                new Card(NINE, SPADE),
                                new Card(NINE, CLOVER))),
                        19),
                Arguments.of(new Hands(List.of(new Card(ACE, DIAMOND),
                                new Card(EIGHT, CLOVER),
                                new Card(FIVE, CLOVER),
                                new Card(SIX, CLOVER))),
                        20)
        );
    }
}
