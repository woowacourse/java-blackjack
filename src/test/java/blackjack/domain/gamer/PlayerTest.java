package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Deck;

public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백이거나 빈 이름으로 플레이어를 생성하려할 시 에러를 던지는 확인")
    void checkEmptyOrSpaceNameError(String input) {
        assertThatThrownBy(() -> new Player(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("이름에 null값으로 플레이어를 생성하려할 시 에러를 던지는 확인")
    void checkNullNameError() {
        assertThatThrownBy(() -> new Player(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("카드의 합이 21 이하여서 뽑을 수 있는지 확인")
    void canDrawTest() {
        Player player = new Player("a");

        assertThat(player.canDraw()).isTrue();
    }

    @Test
    @DisplayName("플레이어 카드의 합이 21 초과여서 버스트인지 확인")
    void bustTest() {
        Deck deck = Deck.create();
        Player a = new Player("a");

        while (a.canDraw()) {
            a.drawCard(deck.draw());
        }

        assertThat(a.isBust()).isTrue();
    }
}
