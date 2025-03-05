package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("승무패를 계산하는지 확인합니다.")
    void decideGameResultTest(List<Card> cards, GameResult expected) {
        //given
        Player player = new Player("ㅎㅎ");
        cards.forEach(player::addCard);
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        dealer.addCard(Card.HEART_JACK);
        dealer.addCard(Card.SPADE_QUEEN); // 20

        //when & then
        Assertions.assertEquals(expected, dealer.decideGameResult(player));
    }

    public static Stream<Arguments> decideGameResultTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE), WIN),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), LOSE),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_ACE), WIN),
                Arguments.of(List.of(Card.HEART_SEVEN, Card.CLOVER_JACK, Card.CLOVER_THREE), DRAW),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE, Card.HEART_ACE), WIN)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인합니다.")
    void canGetMoreCardTest(List<Card> cards, boolean expected) {
        //given
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        cards.forEach(dealer::addCard);

        //when & then
        assertThat(dealer.canGetMoreCard()).isEqualTo(expected);
    }

    public static Stream<Arguments> canGetMoreCardTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_ACE), false),
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_FIVE), true),
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_SIX), true),
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_SEVEN), false),
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_ACE, Card.CLOVER_QUEEN), false),
                Arguments.of(List.of(Card.HEART_TEN, Card.HEART_QUEEN, Card.CLOVER_THREE), false),
                Arguments.of(List.of(Card.HEART_TEN, Card.DIA_JACK, Card.CLOVER_TWO), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("딜러가 가진 카드 중 하나가 포함됐는지 확인합니다.")
    void showFirstCardTest(List<Card> cards) {
        //given
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        cards.forEach(dealer::addCard);
        //when & then
        org.assertj.core.api.Assertions.assertThat(cards).contains(dealer.showAnyOneCard());
    }

    public static Stream<Arguments> showFirstCardTest() {
        return Stream.of(
                Arguments.of(List.of(Card.CLOVER_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE)),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN)),
                Arguments.of(List.of(Card.SPADE_QUEEN, Card.CLOVER_JACK, Card.CLOVER_ACE)),
                Arguments.of(List.of(Card.HEART_SEVEN, Card.CLOVER_JACK, Card.CLOVER_THREE)),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE))
        );
    }

    @Test
    @DisplayName("딜러의 카드가 없는 경우에 카드를 보여주려고 시도하면 예외를 반환합니다.")
    void validateShowFirstCardTest() {
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        Assertions.assertThrows(IllegalStateException.class, dealer::showAnyOneCard);
    }
}