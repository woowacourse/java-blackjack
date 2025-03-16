package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("이름 리스트 테스트")
public class ParticipantNamesTest {

    @Test
    void 플레이어이름이_4개를_초과하면_예외가_발생한다() {
        ParticipantName drago = new ParticipantName("drago");
        ParticipantName duei = new ParticipantName("duei");
        ParticipantName brown = new ParticipantName("brown");
        ParticipantName neo = new ParticipantName("neo");
        ParticipantName parang = new ParticipantName("parang");

        assertThatThrownBy(() -> new ParticipantNames(List.of(drago, duei, brown, neo, parang)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 최대 4개까지 입력 가능합니다.");
    }

    @Test
    void 플레이어이름이_중복되면_예외가_발생한다() {
        ParticipantName drago = new ParticipantName("drago");

        assertThatThrownBy(() -> new ParticipantNames(List.of(drago, drago)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 중복될 수 없습니다.");
    }
}
