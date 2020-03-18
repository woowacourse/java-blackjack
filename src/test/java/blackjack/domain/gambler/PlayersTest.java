package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Names;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void create() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못");
    }

    @DisplayName("플레이어 최대인원 초과시 예외 발생")
    @Test
    void validMaximumPlayerCount() {
        assertThatThrownBy(() -> new Players(new Names("jamie1,jamie2,jamie3,jamie4,jamie5,jamie6,jamie7,jamie8")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여 인원");
    }

    @DisplayName("플레이어들의 이름들을 받아오는 테스트")
    @Test
    void getPlayerNames() {
        Players players = new Players(new Names(("jamie,ravie")));
        assertThat(players.getPlayerNames().get(0)).isEqualTo("jamie");
        assertThat(players.getPlayerNames().get(1)).isEqualTo("ravie");
    }
}
