package blackJack.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackJack.domain.Card;
import blackJack.domain.Denomination;
import blackJack.domain.Symbol;

class PlayerTest {

    @Test
    @DisplayName("Player 생성 테스트")
    void createValidPlayer() {
        assertThat(new Player("rookie")).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    @DisplayName("Player 이름 검증 테스트")
    void checkPlayerName(String value) {
        assertThatThrownBy(() -> new Player(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("Player 카드 합계 계산 테스트")
    void calculateScore() {
        Player player = new Player("rookie");
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));
        assertThat(player.calculateScore()).isEqualTo(8);
    }
}
