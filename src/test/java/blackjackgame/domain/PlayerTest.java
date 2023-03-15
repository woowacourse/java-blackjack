package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Symbol;
import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Player;

class PlayerTest {

    static Stream<Arguments> cardDummy() {
        Card ace = Card.of(Symbol.SPADE, CardValue.ACE);
        Card five = Card.of(Symbol.SPADE, CardValue.FIVE);
        Card nine = Card.of(Symbol.SPADE, CardValue.NINE);
        Card ten = Card.of(Symbol.SPADE, CardValue.JACK);

        return Stream.of(
            Arguments.arguments(List.of(ace, ten), Collections.emptyList(), 21),
            Arguments.arguments(List.of(ace, five), Collections.emptyList(), 16),
            Arguments.arguments(List.of(five, ace), List.of(ace), 17),
            Arguments.arguments(List.of(ten, ten), List.of(ace), 21),
            Arguments.arguments(List.of(ten, nine), List.of(five), 24),
            Arguments.arguments(List.of(ace, nine), List.of(ace, five), 16),
            Arguments.arguments(List.of(five, nine), List.of(ace, nine), 24)
        );
    }

    @DisplayName("플레이어가 카드 중에 에이스가 있을때 총합이 10을 넘지 않으면 총합에 10을 더한다.")
    @ParameterizedTest(name = "플레이어가 가진 카드의 합은 {2}이다.")
    @MethodSource("cardDummy")
    void Should_ReturnScore_When_Request(List<Card> initCards, List<Card> additionCards, int expected) {
        Player player = Dealer.from(initCards);
        for (Card card : additionCards) {
            player.draw(card);
        }

        assertThat(player.scoreValue()).isEqualTo(expected);
    }
}
