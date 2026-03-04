import domain.Card;
import domain.CardNumber;
import domain.CardShape;
import domain.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("숫자에 대한 카드 점수를 계산한다.")
    void calculateNumberTotalScoreTest() {
        Player player = new Player();
        player.addCard(new Card(CardNumber.EIGHT, CardShape.CLUB));
        player.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));

        int totalScore = player.calculateTotalScore();

        Assertions.assertThat(totalScore).isEqualTo(12);
    }

    @Test
    @DisplayName("알파벳에 대한 카드 점수를 계산한다. (ex - J, Q, K)")
    void calculateAlphabetTotalScoreTest() {
        Player player = new Player();
        player.addCard(new Card(CardNumber.JACK, CardShape.CLUB));
        player.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));

        int totalScore = player.calculateTotalScore();

        Assertions.assertThat(totalScore).isEqualTo(14);
    }

    @Test
    @DisplayName("21을 초과하면 버스트이다.")
    void judgeBustTest() {
        Player player = new Player();
        player.addCard(new Card(CardNumber.JACK, CardShape.CLUB));
        player.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));
        player.addCard(new Card(CardNumber.EIGHT, CardShape.CLUB));
        int playerTotalScore = player.calculateTotalScore();
        boolean isBust = player.isBust(playerTotalScore);

        org.junit.jupiter.api.Assertions.assertTrue(isBust);
    }
}
