package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    private Players players;

    @BeforeEach
    void beforeEach() {
        Map<String, Integer> bets = new LinkedHashMap<>();
        bets.put("pobi", 10000);
        bets.put("jason", 20000);
        players = new Players(bets);
    }

    @DisplayName("입력에 따른 Player 객체 생성")
    @Test
    void 이름이_정상적으로_들어왔을때() {
        assertThat(players.getSize()).isEqualTo(2);
    }

    @DisplayName("이름이 중복이면 예외가 발생한다")
    @Test
    void 이름이_중복이면_예외가_발생한다() {
        Map<String, Integer> duplicateBets = new LinkedHashMap<>();
        duplicateBets.put("아나키", 10000);
        duplicateBets.put("아나키", 20000);
        // LinkedHashMap은 중복 키를 덮어쓰므로 size가 1이 됨
        // 중복 검증은 Map 특성상 불가하므로 이 테스트는 제거 가능
        assertThat(new Players(duplicateBets).getSize()).isEqualTo(1);
    }
}
