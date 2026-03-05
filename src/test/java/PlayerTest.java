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
        Player player1 = new Player("pobi");
        player1.addCard(new Card(CardNumber.EIGHT, CardShape.CLUB));
        player1.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));

        int totalScore1 = player1.calculateTotalScore();

        Player player2 = new Player("woni");
        player2.addCard(new Card(CardNumber.EIGHT, CardShape.CLUB));
        player2.addCard(new Card(CardNumber.TWO, CardShape.CLUB));

        int totalScore2 = player2.calculateTotalScore();

        Assertions.assertThat(totalScore1).isEqualTo(12);
        Assertions.assertThat(totalScore2).isEqualTo(10);
    }

    @Test
    @DisplayName("알파벳에 대한 카드 점수를 계산한다. (ex - J, Q, K)")
    void calculateAlphabetTotalScoreTest() {
        Player player = new Player("pobi");
        player.addCard(new Card(CardNumber.JACK, CardShape.CLUB));
        player.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));

        int totalScore = player.calculateTotalScore();

        Assertions.assertThat(totalScore).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace에 대한 점수를 처리한다.")
    void judgeAceTest() {
        Player player1 = new Player("pobi");
        player1.addCard(new Card(CardNumber.JACK, CardShape.CLUB));
        player1.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));
        player1.addCard(new Card(CardNumber.ACE, CardShape.CLUB));
        int player1TotalScore = player1.calculateTotalScore();

        Player player2 = new Player("woni");
        player2.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));
        player2.addCard(new Card(CardNumber.ACE, CardShape.CLUB));
        int player2TotalScore = player2.calculateTotalScore();

        Assertions.assertThat(player1TotalScore).isEqualTo(15);
        Assertions.assertThat(player2TotalScore).isEqualTo(15);
    }

    @Test
    @DisplayName("여러 장의 Ace에 대한 점수를 처리한다.")
    void judgeManyAceTest() {
        Player player1 = new Player("pobi");
        player1.addCard(new Card(CardNumber.EIGHT, CardShape.CLUB));
        player1.addCard(new Card(CardNumber.ACE, CardShape.CLUB));
        player1.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        int player1TotalScore = player1.calculateTotalScore();

        Player player2 = new Player("woni");
        player2.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));
        player2.addCard(new Card(CardNumber.ACE, CardShape.CLUB));
        player2.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        player2.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        int player2TotalScore = player2.calculateTotalScore();

        Assertions.assertThat(player1TotalScore).isEqualTo(20);
        Assertions.assertThat(player2TotalScore).isEqualTo(17);
    }

    @Test
    @DisplayName("21을 초과하면 버스트이다.")
    void judgeBustTest() {
        Player player = new Player("pobi");
        player.addCard(new Card(CardNumber.JACK, CardShape.CLUB));
        player.addCard(new Card(CardNumber.FOUR, CardShape.CLUB));
        player.addCard(new Card(CardNumber.EIGHT, CardShape.CLUB));
        int playerTotalScore = player.calculateTotalScore();
        boolean isBust = player.isBust(playerTotalScore);

        org.junit.jupiter.api.Assertions.assertTrue(isBust);
    }
}
