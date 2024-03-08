package domain;

import java.util.List;
import java.util.stream.Stream;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandsTest {

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
        hands.add(new Card(Rank.EIGHT, Shape.CLOVER));

        //then
        Assertions.assertThat(hands.size()).isEqualTo(1);
    }

    @DisplayName("카드의 합을 구한다.")
    @ParameterizedTest
    @MethodSource("sumParameterProvider")
    void sum(final Hands hands, final int expected) {
        // given & when
        final int result = hands.sum();

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    //TODO: 디스플레이네임 수정 필요, 좀더 명확하고, 이해하기 쉽게
    // 코드 중복이 발생한다.
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
        final Hands sum1 = new Hands(
                List.of(new Card(Rank.SEVEN, Shape.SPADE), new Card(Rank.TWO, Shape.SPADE),
                        new Card(Rank.ACE, Shape.SPADE)));
        final Hands sum2 = new Hands(
                List.of(new Card(Rank.FOUR, Shape.SPADE), new Card(Rank.FIVE, Shape.SPADE),
                        new Card(Rank.ACE, Shape.SPADE)));

        // when && then
        Assertions.assertThat(sum1.calculateResult(sum2)).isEqualTo(Result.TIE);
    }

    @DisplayName("카드 합이 같은데 카드 갯수가 더 적으면 승리이다.")
    @Test
    void isWinBySize() {
        // given
        final Hands sum20Size2 = new Hands(
                List.of(new Card(Rank.NINE, Shape.SPADE), new Card(Rank.ACE, Shape.SPADE)));
        final Hands sum20Size3 = new Hands(
                List.of(new Card(Rank.FOUR, Shape.SPADE), new Card(Rank.FIVE, Shape.SPADE),
                        new Card(Rank.ACE, Shape.SPADE)));

        // when && then
        Assertions.assertThat(sum20Size2.calculateResult(sum20Size3)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("카드 합이 21이하이면서 승리한다.") //TODO 이름 변경
    void isWin() {
        //given
        final Hands sum20 = new Hands(
                List.of(new Card(Rank.SEVEN, Shape.SPADE), new Card(Rank.TWO, Shape.SPADE),
                        new Card(Rank.ACE, Shape.SPADE)));

        final Hands sum21 = new Hands(
                List.of(new Card(Rank.JACK, Shape.HEART),
                        new Card(Rank.ACE, Shape.SPADE)));

        //when && then
        Assertions.assertThat(sum21.calculateResult(sum20)).isEqualTo(Result.WIN);
    }


    @Test
    @DisplayName("카드 합이 21이하이면서 패배한다.")
    void isLose() {
        final Hands sum20 = new Hands(
                List.of(new Card(Rank.SEVEN, Shape.SPADE), new Card(Rank.TWO, Shape.SPADE),
                        new Card(Rank.ACE, Shape.SPADE)));

        final Hands sum21 = new Hands(
                List.of(new Card(Rank.JACK, Shape.HEART), new Card(Rank.ACE, Shape.SPADE)));

        //when && then
        Assertions.assertThat(sum20.calculateResult(sum21)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("카드 합이 21초과이면 패배한다.")
    void isLoseWhenCardSumGreater21() {
        //given
        final Hands sum18 = new Hands(
                List.of(new Card(Rank.SEVEN, Shape.HEART), new Card(Rank.ACE, Shape.SPADE)));

        final Hands sum22 = new Hands(
                List.of(new Card(Rank.NINE, Shape.HEART), new Card(Rank.EIGHT, Shape.SPADE),
                        new Card(Rank.FIVE, Shape.HEART)));

        //when && then
        Assertions.assertThat(sum22.calculateResult(sum18)).isEqualTo(Result.LOSE);
    }


    @Test
    @DisplayName("blackjack이 이긴다.")
    void isWinBlackJack() {
        //given
        final Hands blackJack = new Hands(
                List.of(new Card(Rank.JACK, Shape.HEART), new Card(Rank.ACE, Shape.SPADE)));

        final Hands sum19 = new Hands(
                List.of(new Card(Rank.NINE, Shape.HEART), new Card(Rank.TEN, Shape.SPADE)));

        //when && then
        Assertions.assertThat(blackJack.calculateResult(sum19)).isEqualTo(Result.WIN);
        Assertions.assertThat(sum19.calculateResult(blackJack)).isEqualTo(Result.LOSE);
    }

    static Stream<Arguments> sumParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(Rank.TWO, Shape.HEART),
                                new Card(Rank.EIGHT, Shape.SPADE),
                                new Card(Rank.JACK, Shape.CLOVER))),
                        20),
                Arguments.of(new Hands(List.of(new Card(Rank.THREE, Shape.DIAMOND),
                                new Card(Rank.NINE, Shape.CLOVER),
                                new Card(Rank.NINE, Shape.CLOVER))),
                        21)
        );
    }

    static Stream<Arguments> sumAce11ParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(Rank.ACE, Shape.HEART),
                                new Card(Rank.EIGHT, Shape.SPADE),
                                new Card(Rank.TWO, Shape.CLOVER))),
                        21),
                Arguments.of(new Hands(List.of(new Card(Rank.ACE, Shape.DIAMOND),
                                new Card(Rank.TWO, Shape.CLOVER),
                                new Card(Rank.FOUR, Shape.CLOVER),
                                new Card(Rank.TWO, Shape.CLOVER))),
                        19)
        );
    }

    static Stream<Arguments> sumAce1ParameterProvider() {
        return Stream.of(
                Arguments.of(new Hands(List.of(new Card(Rank.ACE, Shape.HEART),
                                new Card(Rank.NINE, Shape.SPADE),
                                new Card(Rank.NINE, Shape.CLOVER))),
                        19),
                Arguments.of(new Hands(List.of(new Card(Rank.ACE, Shape.DIAMOND),
                                new Card(Rank.EIGHT, Shape.CLOVER),
                                new Card(Rank.FIVE, Shape.CLOVER),
                                new Card(Rank.SIX, Shape.CLOVER))),
                        20)
        );
    }
}
