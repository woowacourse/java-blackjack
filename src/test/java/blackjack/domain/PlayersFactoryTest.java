package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersFactoryTest {

    @Test
    @DisplayName("입력된 참가자의 수가 2명 미만인 경우 예외 발생")
    void validateLength_under2() {
        // given
        String[] names = {"1"};

        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                PlayersFactory.from(names)
        ).withMessage("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
    }

    @Test
    @DisplayName("입력된 참가자의 수가 8명 초과인 경우 예외 발생")
    void validateLength_over8() {
        // given
        String[] names = {"1","2","3","4","5","6","7","8","9"};

        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                PlayersFactory.from(names)
        ).withMessage("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
    }

    @Test
    @DisplayName("입력된 참가자들로 이루어진 Players 객체 생성 확인")
    void createPlayers() {
        // given
        String[] names = {"milli", "doggy"};

        // when
        Players players = PlayersFactory.from(names);

        // then
        assertAll(
                () -> assertThat(players.getPlayers().get(0).getName()).isEqualTo("milli"),
                () -> assertThat(players.getPlayers().get(1).getName()).isEqualTo("doggy")
        );
     }
}
