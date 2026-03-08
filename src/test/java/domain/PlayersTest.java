package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("입력에 따른 Player 객체 생성")
    @Test
    void 이름이_정상적으로_들어왔을때() {
        Players players = new Players(List.of("아나키", "포비", "모아"));
        assertThat(players.getSize()).isEqualTo(4); // 딜러 포함
    }

    @DisplayName("플레이어가 없으면 예외가 발생한다")
    @Test
    void 플레이어가_없으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
