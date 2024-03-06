package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어가 카드를 뽑아서 저장한다.")
    @Test
    void drawTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);
        Decks decks = new Decks();

        // when
        player.hit(decks);

        // then
        assertThat(player.getHand()).hasSize(1);
    }

    @DisplayName("플레이어가 가진 카드의 점수를 알 수 있다.")
    @Test
    void calculateTotalScoreTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);
        Decks decks = new Decks();

        // when
        int beforeScore = player.calculateTotalScore();
        player.hit(decks);
        int afterScore = player.calculateTotalScore();

        // then
        assertThat(beforeScore).isLessThan(afterScore);
    }
}
