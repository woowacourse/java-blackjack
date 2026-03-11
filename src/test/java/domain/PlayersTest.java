package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    Players players;

    @BeforeEach
    void beforeEach() {
        players = new Players(List.of("pobi", "jason"));
    }

    @DisplayName("입력에 따른 Player 객체 생성")
    @Test
    void 이름이_정상적으로_들어왔을때() {
        assertThat(players.getSize()).isEqualTo(3); // 딜러 포함
    }

    @DisplayName("이름이 중복이면 예외가 발생한다")
    @Test
    void 이름이_중복이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Players(List.of("아나키", "아나키", "모아")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
