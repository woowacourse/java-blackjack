import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Name;
import domain.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("플레이어의 점수를 알 수 있다.")
    @Test
    void calculateScoreTest() {
        List<Card> cards = List.of(new Card("K", "하트"), new Card("3", "스페이드"));
        Player player = new Player(new Name("깃짱"), cards);
        assertThat(player.calculateScore()).isEqualTo(13);
    }
}
