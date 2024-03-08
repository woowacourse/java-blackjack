import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Player;
import domain.constants.Score;
import domain.constants.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드를 저장한다.")
    @Test
    void saveCard() {
        Player player = new Player("pobi");
        player.drawCard(new Card(Score.ACE, Shape.CLOVER));
        int totalSize = player.getTotalSize();
        assertThat(totalSize).isEqualTo(1);
    }


    @DisplayName("카드의 총 점수를 계산한다.")
    @Nested
    class calculateScore {
        @DisplayName("에이스 카드가 없는 경우 단순 합산한다.")
        @Test
        void calculateScoreWithNoAce() {
            Player player = new Player("pobi");
            player.drawCard(new Card(Score.EIGHT, Shape.CLOVER));
            player.drawCard(new Card(Score.NINE, Shape.CLOVER));
            int totalScore = player.calculateResultScore();
            assertThat(totalScore).isEqualTo(17);
        }

        @DisplayName("에이스 카드가 11로 계산되었을 때 21을 초과하면 1로 계산한다.")
        @Test
        void calculateScoreWithAceIfBusted() {
            Player player = new Player("pobi");
            player.drawCard(new Card(Score.EIGHT, Shape.CLOVER));
            player.drawCard(new Card(Score.THREE, Shape.CLOVER));
            player.drawCard(new Card(Score.ACE, Shape.CLOVER));

            int totalScore = player.calculateResultScore();
            assertThat(totalScore).isEqualTo(12);
        }
    }

    @DisplayName("에이스 카드가 포함된 카드를 받았을 때 1로 계산한다.")
    @Test
    void drawAceCardAndCalculateScoreOne() {
        Player player = new Player("pobi");
        player.drawCard(new Card(Score.EIGHT, Shape.CLOVER));
        player.drawCard(new Card(Score.THREE, Shape.CLOVER));
        player.drawCard(new Card(Score.ACE, Shape.CLOVER));

        int totalScore = player.calculateScoreWhileDraw();
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("카드를 더 받을 수 있는지 확인한다.")
    @Test
    void isAbleToDrawCard() {
        Player player = new Player("pobi");
        player.drawCard(new Card(Score.TEN, Shape.CLOVER));
        player.drawCard(new Card(Score.TEN, Shape.DIAMOND));
        player.drawCard(new Card(Score.THREE, Shape.CLOVER));

        boolean ableToDrawCard = player.isAbleToDrawCard();
        assertThat(ableToDrawCard).isFalse();
    }
}
