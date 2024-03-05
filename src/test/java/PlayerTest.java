import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @DisplayName("사용자의 점수를 계산한다.")
    @Test
    void calculateScore() {
        Player player = new Player("user");
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        int score = player.calculateScore();

        assertThat(score).isEqualTo(10);
    }

    @DisplayName("사용자의 점수가 21이하인지 확인한다.")
    @Test
    void canHit() {
        Player player = new Player("user");
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.DIA, Rank.KING));
        player.receiveCard(new Card(Shape.HEART, Rank.TWO));

        boolean canHit = player.canHit();

        assertThat(canHit).isFalse();

    }

    @Test
    @DisplayName("사용자의 카드중 ACE 포함여부를 반환한다. ")
    void hasAce() {
        Player player = new Player("user");
        player.receiveCard(new Card(Shape.HEART, Rank.ACE));

        //when
        boolean hasAce = player.hasAce();
        //then
        assertThat(hasAce).isTrue();
    }


    @Test
    @DisplayName("ace는 1 또는 11 중 하나로 결정")
    void calculateAceIsEleven() {
        Player player = new Player("user");
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.HEART, Rank.ACE));

        int totalScore = player.calculateScore();

        Assertions.assertThat(totalScore).isEqualTo(21);
    }

    @Test
    @DisplayName("ace는 1 또는 11 중 하나로 결정")
    void calculateAceIsOne() {
        Player player = new Player("user");
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.HEART, Rank.NINE));
        player.receiveCard(new Card(Shape.HEART, Rank.ACE));

        int totalScore = player.calculateScore();

        Assertions.assertThat(totalScore).isEqualTo(20);
    }
}
