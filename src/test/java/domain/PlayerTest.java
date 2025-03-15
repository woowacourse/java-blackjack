package domain;

import static domain.GameResult.BLACKJACK;
import static domain.GameResult.LOSE;
import static domain.GameResult.TIE;
import static domain.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    Player player;

    @BeforeEach
    void makePlayer() {
        player = new Player(new PlayerName("코기"), new BettingMoney(10000));
    }

    @Test
    @DisplayName("특정 카드를 뽑는지 확인합니다.")
    void addCardTest() {
        //given
        Card card = Card.CLOVER_EIGHT;

        Cards newCards = new Cards(List.of(card));

        //when
        player.receiveCards(newCards);

        //then
        Assertions.assertTrue(player.getCards().hasCommonCard(newCards));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("플레이어가 카드를 더 받을 수 있는지 확인한다.")
    void canGetMoreCardTest(List<Card> cards, boolean expected) {
        //given
        for (Card card : cards) {
            player.receiveCards(new Cards(List.of(card)));
        }

        int playStandard = 20;
        // when
        boolean canGetMoreCard = player.isDrawable(playStandard);
        // then
        assertThat(canGetMoreCard).isEqualTo(expected);
    }

    public static Stream<Arguments> canGetMoreCardTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK), false),
                Arguments.of(List.of(Card.CLOVER_JACK, Card.CLOVER_QUEEN), true),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_ACE), true),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_THREE), false),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("딜러 숫자가 20일 때, 승무패를 계산하는지 확인합니다.")
    void decideGameResultTest(List<Card> cards, GameResult expected) {
        //given
        for (Card card : cards) {
            player.receiveCards(new Cards(List.of(card)));
        }
        Cards emptyCards = new Cards(new ArrayList<>());
        Dealer dealer = new Dealer(new Deck(emptyCards));
        dealer.receiveCards(new Cards(List.of(Card.HEART_JACK, Card.SPADE_QUEEN))); // 20

        //when & then
        Assertions.assertEquals(expected, player.decideGameResult(dealer));
    }

    public static Stream<Arguments> decideGameResultTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_NINE, Card.CLOVER_THREE), LOSE), // 버스트
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_QUEEN), BLACKJACK), // 21
                Arguments.of(List.of(Card.HEART_TEN, Card.CLOVER_JACK, Card.CLOVER_ACE), WIN), // 21
                Arguments.of(List.of(Card.HEART_SEVEN, Card.CLOVER_JACK, Card.CLOVER_THREE), TIE) // 20
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("딜러나 플레이어가 버스트인 경우 승패를 확인합니다.")
    void decideGameResultBustTest(List<Card> cards, GameResult expected) {
        //given
        for (Card card : cards) {
            player.receiveCards(new Cards(List.of(card)));
        }

        Cards emptyCards = new Cards(new ArrayList<>());
        Dealer dealer = new Dealer(new Deck(emptyCards));

        List<Card> givenCards = List.of(Card.HEART_JACK, Card.SPADE_SIX, Card.CLOVER_SEVEN);
        for (Card givenCard : givenCards) {
            dealer.receiveCards(new Cards(List.of(givenCard)));
        }

        //when & then
        Assertions.assertEquals(expected, player.decideGameResult(dealer));
    }

    public static Stream<Arguments> decideGameResultBustTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_QUEEN, Card.CLOVER_JACK, Card.CLOVER_THREE), LOSE),
                Arguments.of(List.of(Card.HEART_TWO, Card.CLOVER_JACK, Card.CLOVER_TEN), LOSE)
        );
    }
}
