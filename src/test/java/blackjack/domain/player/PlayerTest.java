package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player() {
            @Override
            public Boolean canPick() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public boolean isDealer() {
                return false;
            }
        };
    }


    @Test
    @DisplayName("플레이어는 초기 카드 2장을 받는다")
    void start_with_two_cards() {
        Card card1 = new Card(Shape.HEART, Symbol.FOUR);
        Card card2 = new Card(Shape.CLOVER, Symbol.KING);
        player.pickStartCards(card1, card2);

        assertThat(player.getHoldingCards().getCards())
                .containsExactly(card1, card2);
    }

    @Test
    @DisplayName("추가 카드를 뽑는다.")
    void pick_card() {
        Card card = new Card(Shape.DIAMOND, Symbol.JACK);
        player.pickCard(card);

        assertThat(player.getHoldingCards().getCards())
                .contains(card);
    }

    @ParameterizedTest
    @MethodSource("provideCards_bust")
    @DisplayName("버스트인지 확인")
    void is_bust(List<Card> cards, boolean expected) {
        for (Card card : cards) {
            player.pickCard(card);
        }

        assertThat(player.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards_bust() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.FIVE)),
                        false),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.FIVE),
                                new Card(Shape.HEART, Symbol.EIGHT)),
                        true),
                Arguments.of(
                        List.of(
                                new Card(Shape.CLOVER, Symbol.ACE),
                                new Card(Shape.SPADE, Symbol.ACE),
                                new Card(Shape.HEART, Symbol.ACE),
                                new Card(Shape.CLOVER, Symbol.FIVE)),
                        false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCards_blackjack")
    @DisplayName("블랙잭인지 확인")
    void is_blackjack(List<Card> cards, boolean expected) {
        for (Card card : cards) {
            player.pickCard(card);
        }

        assertThat(player.isBlackJack()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards_blackjack() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Symbol.QUEEN),
                                new Card(Shape.CLOVER, Symbol.ACE)),
                        true),
                Arguments.of(
                        List.of(
                                new Card(Shape.CLOVER, Symbol.ACE),
                                new Card(Shape.SPADE, Symbol.FIVE)),
                        false)
        );
    }
}
