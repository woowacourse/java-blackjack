package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("플레이어")
public class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("이름이 공백일 경우 예외가 발생한다.")
    void validateEmptyTest(String name) {
        // given & when & then
        assertThatCode(() -> new Player(name, new Hand(List.of())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 플레이어를 입력해 주세요.");
    }

    @Test
    @DisplayName("처음 받은 카드 두장을 반환한다.")
    void gameDealTest() {
        // given
        Player player = new Player("lemone", new Hand(List.of()));
        // when
        player.draw(List.of(Card.CLUB_ACE, Card.CLUB_NINE));

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 뽑기로 결정했을 때 카드 한장을 반환한다.")
    void gameHitTest() {
        // given
        Player player = new Player("lemone", new Hand(List.of()));
        // when
        player.draw(Card.DIAMOND_KING);

        // then
        assertThat(player.getCards().size()).isEqualTo(1);
    }
}
