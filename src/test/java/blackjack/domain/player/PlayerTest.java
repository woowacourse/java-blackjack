package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.CardFixture;
import blackjack.domain.card.Card;
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

    private static Stream<Arguments> provideCards_bust() {
        return Stream.of(
                Arguments.of(List.of(CardFixture.CLOVER_KING, CardFixture.CLOVER_FIVE), false),
                Arguments.of(List.of(CardFixture.CLOVER_KING, CardFixture.CLOVER_FIVE, CardFixture.HEART_EIGHT), true),
                Arguments.of(List.of(CardFixture.CLOVER_ACE, CardFixture.HEART_ACE, CardFixture.SPADE_ACE,
                        CardFixture.CLOVER_FIVE), false)
        );
    }

    private static Stream<Arguments> provideCards_blackjack() {
        return Stream.of(
                Arguments.of(List.of(CardFixture.CLOVER_KING, CardFixture.CLOVER_ACE), true),
                Arguments.of(List.of(CardFixture.CLOVER_ACE, CardFixture.CLOVER_FIVE), false)
        );
    }

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
        player.pickStartCards(CardFixture.HEART_FOUR, CardFixture.CLOVER_KING);

        assertThat(player.getHoldingCards().getCards())
                .containsExactly(CardFixture.HEART_FOUR, CardFixture.CLOVER_KING);
    }

    @Test
    @DisplayName("추가 카드를 뽑는다.")
    void pick_card() {
        player.pickCard(CardFixture.DIAMOND_JACK);

        assertThat(player.getHoldingCards().getCards())
                .contains(CardFixture.DIAMOND_JACK);
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

    @ParameterizedTest
    @MethodSource("provideCards_blackjack")
    @DisplayName("블랙잭인지 확인")
    void is_blackjack(List<Card> cards, boolean expected) {
        for (Card card : cards) {
            player.pickCard(card);
        }

        assertThat(player.isBlackJack()).isEqualTo(expected);
    }
}
