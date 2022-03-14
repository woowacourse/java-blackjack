package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    private Player player;
    private Card aceSpade;
    private Card queenSpade;

    @BeforeEach
    void before() {
        player = new Player("woowahan");
        aceSpade = Card.of(CardNumber.ACE, CardShape.SPADE);
        queenSpade = Card.of(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("딜러 이름과 동일한 이름을 입력할 경우 예외를 발생시킨다.")
    @Test
    void equals_dealer_name() {
        assertThatThrownBy(() -> new Player("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러와 동일한 이름은 사용할 수 없습니다.");
    }

    @DisplayName("플레이어의 카드 합이 21 미만일 경우 카드를 받는 것을 확인한다.")
    @Test
    void is_drawable_true() {
        player.drawInitCards(List.of(queenSpade, queenSpade));

        assertThat(player.isDrawable()).isTrue();
    }

    @DisplayName("플레이어의 카드 합이 21 이상일 경우 카드를 받지 못하는 것을 확인한다.")
    @Test
    void is_drawable_false() {
        player.drawInitCards(List.of(aceSpade, queenSpade, queenSpade));

        assertThat(player.isDrawable()).isFalse();
    }
}
