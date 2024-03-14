package domain.state.run;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.player.Hands;
import domain.state.fininsh.Finished;
import domain.state.fininsh.Stand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {

    @Test
    void draw() {

    }

    @Test
    @DisplayName("사용자가 그만 받기를 원했을 때 끝난 상태인지 확인한다")
    void stand() {
        final Hit hit = new Hit(new Hands(List.of()));

        assertThat(hit.stand()).isInstanceOf(Finished.class).isInstanceOf(Stand.class);
    }
}
