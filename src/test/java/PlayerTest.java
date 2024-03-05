import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @DisplayName("사용자의 점수를 계산한다.")
    @Test
    void calculateScore() {
       Player player = new Player("user");
       player.receiveCard(new Card(Shape.HEART, Rank.KING));
       int score = player.calculateScore();

        Assertions.assertThat(score).isEqualTo(10);
    }

    @DisplayName("사용자의 점수가 21이하인지 확인한다.")
    @Test
    void canHit() {
        Player player = new Player("user");
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.DIA, Rank.KING));
        player.receiveCard(new Card(Shape.HEART, Rank.TWO));

        boolean canHit = player.canHit();

        Assertions.assertThat(canHit).isFalse();

    }
}
