package domain;

import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerBetTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("player");
    }

    @DisplayName("숫자 외 문자를 입력시 생성 검증에 실패한다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "123a", " ", "", "-10000"})
    void createPlayerBetInvalidInput(String value) {

        assertThatThrownBy(() -> new PlayerBet(player, value)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("10000 이상 숫자만 입력시 생성 검증에 성공한다")
    @ParameterizedTest
    @ValueSource(strings = {"10000", "123456"})
    void createPlayerBetValidInput(String value) {
        assertThatCode(() -> new PlayerBet(player, value)).doesNotThrowAnyException();
    }

    @DisplayName("10000원 미만은 베팅할 수 없다.")
    @Test
    void createPlayerBetByMinValue() {
        assertThatThrownBy(() -> new PlayerBet(player, "0")).isInstanceOf(IllegalArgumentException.class);
    }
}
