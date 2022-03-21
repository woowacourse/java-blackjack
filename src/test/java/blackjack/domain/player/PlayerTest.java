package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백이거나 빈 이름으로 플레이어를 생성하려할 시 에러를 던지는 확인")
    void checkEmptyOrSpaceNameError(String input) {
        Deck deck = new Deck();
        assertThatThrownBy(() -> new Guest(input, deck, (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("이름에 null값으로 플레이어를 생성하려할 시 에러를 던지는 확인")
    void checkNullNameError() {
        Deck deck = new Deck();
        assertThatThrownBy(() -> new Guest(null, deck, (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("버스트 체크")
    void checkBustTest() {
        Deck deck = new Deck();
        Player player = new Guest("testPlayer", deck, (p) -> HitFlag.Y);
        while (player.getCards().calculateScore() <= 21) {
            player.hit(deck.pick());
        }

        assertThat(player.isBust()).isTrue();
    }
}
