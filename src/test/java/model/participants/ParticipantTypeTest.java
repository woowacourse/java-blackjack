package model.participants;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantTypeTest {

    @DisplayName("참가자 유형이 딜러인지 확인한다.")
    @Test
    void isDealer() {
        assertThat(ParticipantType.isDealer("딜러")).isTrue();
    }

    @DisplayName("참가자 유형이 플레이어인지 확인한다.")
    @Test
    void isPlayer() {
        assertThat(ParticipantType.isDealer("플레이어")).isFalse();
    }
}
