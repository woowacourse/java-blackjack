package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        };
    }


    @Test
    @DisplayName("플레이어는 초기 카드 2장을 받는다")
    void get_two_cards() {
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);
        player.pickStartCards(card1, card2);

        assertThat(player.getHoldingCards().getCards())
                .containsExactly(card1, card2);
    }

    @Test
    @DisplayName("추가 카드를 뽑는다.")
    void pick_card() {
        Card card = new Card(Shape.DIAMOND, Number.JACK);
        player.pick(card);

        assertThat(player.getHoldingCards().getCards())
                .contains(card);
    }
}
