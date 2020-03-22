package blackjack.domain.player;

import blackjack.domain.generic.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerInfoTest {

    private static Stream<Arguments> playerInfoProvider() {
        return Stream.of(
                Arguments.of(null, BettingMoney.of(0), "이름이 비었습니다."),
                Arguments.of("bebop", null, "베팅 금액이 비었습니다.")
        );
    }

    @DisplayName("이름이나 베팅금액이 비어있다면 Exception")
    @ParameterizedTest
    @MethodSource("playerInfoProvider")
    void nullThenThrowException(String name, BettingMoney bettingMoney, String message) {
        assertThatThrownBy(() -> new PlayerInfo(name, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @DisplayName("Gambler 객체로 만들기")
    @Test
    void toEntity() {
        //given
        PlayerInfo playerInfo = new PlayerInfo("bebop", BettingMoney.of(1000));

        //when
        Player player = playerInfo.toEntity();

        //then
        assertThat(player).isInstanceOf(Gambler.class);
        assertThat(player.getName()).isEqualTo("bebop");
        assertThat(player.getCardBundle()).isEmpty();
    }
}