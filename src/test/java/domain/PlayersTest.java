package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("모든 플레이어들의 버스트 여부 확인 테스트")
    void 모든_플레이어_버스트_여부_확인() {
        Players allBustPlayers = Players.from(
                List.of(PlayerFixture.createBust("pobi"),
                        PlayerFixture.createBust("jason"),
                        PlayerFixture.createBust("kang")));

        Players containBustPlayers = Players.from(
                List.of(PlayerFixture.createDefault("pobi"),
                        PlayerFixture.createBlackjack("jason"),
                        PlayerFixture.createBust("kang")));

        Players allNormalPlayers = Players.from(
                List.of(PlayerFixture.createDefault("pobi"),
                        PlayerFixture.createDefault("jason"),
                        PlayerFixture.createBlackjack("kang")));

        assertThat(allBustPlayers.isAllBust()).isTrue();
        assertThat(containBustPlayers.isAllBust()).isFalse();
        assertThat(allNormalPlayers.isAllBust()).isFalse();
    }
}