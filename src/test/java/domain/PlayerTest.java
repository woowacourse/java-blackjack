package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("최대 점수 조건을 넘지 않을 경우 카드를 뽑는다.")
    void name() {
        Cards cards = new Cards(List.of(new Card(1), new Card(2)));
        Player player = new Player(new Name("capy"), cards);

        List<Card> drawCards = player.draw();

        assertThat(drawCards).hasSize(3);
    }
}
