package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Guest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백이거나 빈 이름으로 플레이어를 생성하려할 시 에러를 던지는 확인")
    void checkEmptyOrSpaceNameError(String input) {
        assertThatThrownBy(() -> new Guest(input, (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("이름에 null값으로 플레이어를 생성하려할 시 에러를 던지는 확인")
    void checkNullNameError() {
        assertThatThrownBy(() -> new Guest(null, (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("참가자의 승무패 결과 확인")
    void playerWinDrawLoseTest() {
        Guest player = new Guest("pobi", (p) -> HitFlag.Y);
        player.win();
        assertThat(player.getWinDrawLoseString()).isEqualTo("승");
    }

    @Test
    @DisplayName("버스트 체크")
    void checkBustTest() {
        Deck deck = new Deck();
        Player player = new Guest("testPlayer", (p) -> HitFlag.Y);
        while (player.getCards().calculateScore() <= 21) {
            player.hit(deck.draw());
        }

        assertThat(player.isBust()).isTrue();
    }
}
