package blackjack.domain;

import blackjack.domain.CardMachine;
import blackjack.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    private CardMachine cardMachine = new CardMachine();
    private Player player;

    @BeforeEach
    void before() {
        player = new Player(cardMachine.giveInitCard());
    }

    @DisplayName("y, Y를 입력 받았을 때 True 를 반환하는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"y", "Y"})
    void isGiven_true(String input) {
        final boolean given = player.isReceived(input);

        assertThat(given).isTrue();
    }

    @DisplayName("n, N를 입력 받았을 때 False 를 반환하는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"n", "N"})
    void isGiven_false(String input) {
        final boolean given = player.isReceived(input);

        assertThat(given).isFalse();
    }

    @DisplayName("y, n 외의 값을 입력할 경우 예외를 발생시킨다.")
    @Test
    void isGiven_exception() {
        assertThatThrownBy(() -> player.isReceived("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y, n 중에서 입력해주세요.");
    }
}
