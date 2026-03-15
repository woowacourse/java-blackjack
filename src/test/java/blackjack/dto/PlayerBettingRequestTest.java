package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBettingRequestTest {

    @DisplayName("베팅 금액이 숫자일 경우 정상 반환을 확인한다.")
    @Test
    void betNumberAmount() {
        // given
        String nickname = "boye";
        String amount = "10000";

        // when
        PlayerBettingRequest playerBettingRequest = PlayerBettingRequest.of(nickname, amount);

        // then
        assertAll(
            () -> assertThat(playerBettingRequest.playerNickname()).isEqualTo("boye"),
            () -> assertThat(playerBettingRequest.amount()).isEqualTo(10000)
        );
    }

    @DisplayName("베팅 금액이 숫자가 아닐 경우 예외가 발생한다.")
    @Test
    void betNotNumberAmount() {
        // given
        String nickname = "boye";
        String amount = "zzang";

        // when & then
        assertThatThrownBy(() -> PlayerBettingRequest.of(nickname, amount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("금액은 숫자가 입력되어야 합니다.");
    }

    @DisplayName("베팅 금액이 정수 이상인 숫자가 들어올 경우에도 정상 반환된다.")
    @Test
    void betOverIntegerAmount() {
        // given
        String nickname = "boye";
        String amount = "2200000000";

        // when
        PlayerBettingRequest playerBettingRequest = PlayerBettingRequest.of(nickname, amount);

        // then
        assertThat(playerBettingRequest.amount()).isEqualTo(2200000000L);
    }

    @DisplayName("이름이 존재하지 않을 경우 예외가 발생한다.")
    @Test
    void validateEmptyName() {
        // given
        String playerNickname = "";

        // when & then
        assertThatThrownBy(() -> PlayerBettingRequest.createInitialRequest(playerNickname))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 공백이 될 수 없습니다.");
    }

    @Test
    @DisplayName("이름에 공백만 입력될 경우 예외가 발생한다.")
    void validateBlankName() {
        // given
        String playerNickname = " ";

        // when & then
        assertThatThrownBy(() -> PlayerBettingRequest.createInitialRequest(playerNickname))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 공백이 될 수 없습니다.");
    }
}
