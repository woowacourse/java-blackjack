package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.Name;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetTest {

    private Name firstPlayer;
    private Name secondPlayer;
    private Bet bet;

    @BeforeEach
    void set_up() {
        firstPlayer = new Name("피즈");
        secondPlayer = new Name("스타크");
        bet = new Bet(List.of(firstPlayer, secondPlayer));
    }

    @DisplayName("게임에 참여한 플레이어만 값을 배팅할 수 없다.")
    @Test
    void 플레이어_금액_배팅_테스트() {

        Name unknownPlayer = new Name("신원미상");

        assertThatThrownBy(() -> bet.bettingMoney(unknownPlayer, 10_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임에 참여한 플레이어만 배팅이 가능합니다.");
    }

    @DisplayName("플레이어는 양수 이외의 값을 배팅할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-10_000, 0})
    void 플레이어_금액_배팅_테스트(int bettingMoney) {
        assertThatThrownBy(() -> bet.bettingMoney(firstPlayer, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 양수값만 가능합니다.");
    }
}
