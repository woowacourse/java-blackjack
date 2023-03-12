package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    @DisplayName("플레이어 생성 시 생성자에 입력된 두 장의 카드만 가지는지 확인한다.")
    @Test
    void Should_ReturnException_When_initHandHasNotTwoCards() {
        Card spade5 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card heart8 = new Card(Symbol.HEART, CardValue.EIGHT);
        Player player = new Dealer(new Hand(spade5, heart8));
        List<Card> playerCards = player.getHand();

        Assertions.assertThat(playerCards).containsExactlyInAnyOrder(spade5, heart8);
    }


    @DisplayName("딜러가 가진 카드들의 합을 반환한다.")
    @ParameterizedTest(name = "딜러가 가진 카드의 합은 {1}이다.")
    @MethodSource("cardDummy")
    void Should_ReturnScore_When_Request(final Card firstCard, final Card secondCard, final List<Card> hitCards, final int expected) {
        Player player = new Dealer(new Hand(firstCard, secondCard));
        for (Card card : hitCards) {
            player.addCard(card);
        }

        assertThat(player.getScore()).isEqualTo(expected);
    }

    @DisplayName("참여자가 카드를 추가하면 가지고 있는 카드의 개수가 늘어난다")
    @Test
    void Should_SizePlusOne_When_AddCard() {
        Card spade5 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card clover8 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Card heartQ = new Card(Symbol.HEART, CardValue.QUEEN);

        Player player = new Dealer(new Hand(spade5, clover8));
        player.addCard(heartQ);

        Assertions.assertThat(player.getHand()).hasSize(3);
    }

    static Stream<Arguments> cardDummy() {
        return Stream.of(
                Arguments.arguments(new Card(Symbol.HEART, CardValue.FIVE),
                        new Card(Symbol.DIAMOND, CardValue.TWO), Collections.emptyList(), 7),
                Arguments.arguments(new Card(Symbol.SPADE, CardValue.ACE),
                        new Card(Symbol.SPADE, CardValue.ACE), Collections.emptyList(), 12),
                Arguments.arguments(new Card(Symbol.SPADE, CardValue.FIVE),
                        new Card(Symbol.SPADE, CardValue.ACE), List.of(new Card(Symbol.SPADE, CardValue.ACE)), 17),
                Arguments.arguments(new Card(Symbol.SPADE, CardValue.ACE),
                        new Card(Symbol.SPADE, CardValue.NINE), List.of(new Card(Symbol.SPADE, CardValue.FIVE)), 15)
        );
    }
}
