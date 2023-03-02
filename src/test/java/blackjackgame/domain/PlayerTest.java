package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    static Stream<Arguments> cardDummy() {
        return Stream.of(
            Arguments.arguments(List.of(new Card(Symbol.SPADE, CardValue.ACE), new Card(Symbol.SPADE, CardValue.ACE)),
                12),
            Arguments.arguments(List.of(new Card(Symbol.SPADE, CardValue.FIVE), new Card(Symbol.SPADE, CardValue.ACE),
                new Card(Symbol.SPADE, CardValue.ACE)), 17),
            Arguments.arguments(List.of(new Card(Symbol.SPADE, CardValue.ACE), new Card(Symbol.SPADE, CardValue.NINE),
                new Card(Symbol.SPADE, CardValue.FIVE)), 15)
        );
    }

    @DisplayName("딜러가 가진 카드들의 합을 반환한다.")
    @ParameterizedTest
    @MethodSource("cardDummy")
    void Should_ReturnScore_When_Request(List<Card> cards, int expected) {

        Player player = new Dealer();
        for (Card card : cards) {
            player.addCard(card);
        }

        assertThat(player.getScore()).isEqualTo(expected);
    }

    @DisplayName("참여자가 카드를 추가하면 가지고 있는 카드의 개수가 늘어난다")
    @Test
    void Should_SizePlusOne_When_AddCard() {
        Player player = new Guest(new Name("pobi"));
        Card card1 = new Card(Symbol.SPADE, CardValue.FIVE);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(new Card(Symbol.HEART, CardValue.QUEEN));

        assertThat(player.getSize()).isEqualTo(3);
    }
}
