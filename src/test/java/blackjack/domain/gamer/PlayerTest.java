package blackjack.domain.gamer;

import blackjack.domain.card.CardPicker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("플레이어 이름은 공백일 경우 예외가 발생한다.")
    void validateEmptyTest(String name) {
        // given & when & then
        assertThatCode(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 플레이어를 입력해 주세요.");
    }

    @Test
    @DisplayName("플레이어 한명이 처음 받은 카드 두장을 반환한다.")
    void gameDealTest() {
        // given
        Player player = new Player("lemone");
        CardPicker cardPicker = new CardPicker();
        // when
        player.deal(cardPicker);

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어 한명이 카드를 뽑기로 결정했을 때 카드 한장을 반환한다.")
    void gameHitTest() {
        // given
        Player player = new Player("lemone");
        CardPicker cardPicker = new CardPicker();
        // when
        player.hit(cardPicker);

        // then
        assertThat(player.getCards().size()).isEqualTo(1);
    }
}
