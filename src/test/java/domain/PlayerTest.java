package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    @Test
    @DisplayName("숫자에 대한 카드 점수를 계산한다.")
    void calculateNumberTotalScoreTest() {
        List<Card> cards1 = List.of(new Card(CardNumber.EIGHT, CardSuit.CLUB),
                new Card(CardNumber.FOUR, CardSuit.CLUB));
        Player player1 = new Player("pobi", cards1);

        int totalScore1 = player1.calculateTotalScore();

        List<Card> cards2 = List.of(new Card(CardNumber.EIGHT, CardSuit.CLUB), new Card(CardNumber.TWO, CardSuit.CLUB));
        Player player2 = new Player("woni", cards2);

        int totalScore2 = player2.calculateTotalScore();

        Assertions.assertThat(totalScore1).isEqualTo(12);
        Assertions.assertThat(totalScore2).isEqualTo(10);
    }

    @Test
    @DisplayName("알파벳에 대한 카드 점수를 계산한다. (ex - J, Q, K)")
    void calculateAlphabetTotalScoreTest() {
        List<Card> cards = List.of(new Card(CardNumber.JACK, CardSuit.CLUB), new Card(CardNumber.FOUR, CardSuit.CLUB));

        Player player = new Player("pobi", cards);

        int totalScore = player.calculateTotalScore();

        Assertions.assertThat(totalScore).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace에 대한 점수를 처리한다.")
    void judgeAceTest() {
        List<Card> cards1 = new ArrayList(
                List.of(new Card(CardNumber.JACK, CardSuit.CLUB), new Card(CardNumber.FOUR, CardSuit.CLUB)));
        Player player1 = new Player("pobi", cards1);
        player1.addCard(new Card(CardNumber.ACE, CardSuit.CLUB));
        int player1TotalScore = player1.calculateTotalScore();

        List<Card> cards2 = new ArrayList(
                List.of(new Card(CardNumber.FOUR, CardSuit.CLUB), new Card(CardNumber.ACE, CardSuit.CLUB)));
        Player player2 = new Player("woni", cards2);
        int player2TotalScore = player2.calculateTotalScore();

        Assertions.assertThat(player1TotalScore).isEqualTo(15);
        Assertions.assertThat(player2TotalScore).isEqualTo(15);
    }

    @Test
    @DisplayName("여러 장의 Ace에 대한 점수를 처리한다.")
    void judgeManyAceTest() {
        List<Card> cards1 = new ArrayList<>(
                List.of(new Card(CardNumber.EIGHT, CardSuit.CLUB), new Card(CardNumber.ACE, CardSuit.CLUB)));
        Player player1 = new Player("pobi", cards1);
        player1.addCard(new Card(CardNumber.ACE, CardSuit.SPADE));
        int player1TotalScore = player1.calculateTotalScore();

        List<Card> cards2 = new ArrayList<>(
                List.of(new Card(CardNumber.FOUR, CardSuit.CLUB), new Card(CardNumber.ACE, CardSuit.CLUB)));
        Player player2 = new Player("woni", cards2);

        player2.addCard(new Card(CardNumber.ACE, CardSuit.SPADE));
        player2.addCard(new Card(CardNumber.ACE, CardSuit.HEART));
        int player2TotalScore = player2.calculateTotalScore();

        Assertions.assertThat(player1TotalScore).isEqualTo(20);
        Assertions.assertThat(player2TotalScore).isEqualTo(17);
    }

    @Test
    @DisplayName("21을 초과하면 버스트이다.")
    void judgeBustTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.JACK, CardSuit.CLUB), new Card(CardNumber.FOUR, CardSuit.CLUB)));
        Player player = new Player("pobi", cards);

        player.addCard(new Card(CardNumber.EIGHT, CardSuit.CLUB));
        boolean isBust = player.isBust();

        assertTrue(isBust);
    }

    @Test
    @DisplayName("카드를 한 장 받는다.")
    void receiveOneCardTest() {
        Deck deck = new Deck();
        Card card = deck.peekCard();

        Assertions.assertThat(deck.getCards().size()).isEqualTo(51);
        Assertions.assertThat(card).isInstanceOf(Card.class);
        Assertions.assertThat(card).isNotIn(deck.getCards());
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니라면 딜러보다 21에 가까워야 승리한다.")
    void normalNumberTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.FOUR, CardSuit.CLUB), new Card(CardNumber.ACE, CardSuit.CLUB)));
        Player player = new Player("woni", cards);
        Assertions.assertThat(player.compareScore(14)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트라면 딜러가 버스트가 아닐경우 패배한다.")
    void bustTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.FOUR, CardSuit.CLUB), new Card(CardNumber.ACE, CardSuit.CLUB)));
        Player player = new Player("woni", cards);
        player.addCard(new Card(CardNumber.TEN, CardSuit.SPADE));
        player.addCard(new Card(CardNumber.TEN, CardSuit.HEART));
        Assertions.assertThat(player.compareScore(14)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("만약 플레이어와 딜러 모두 버스트라면 무승부이다.")
    void drawBustTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.FOUR, CardSuit.CLUB), new Card(CardNumber.ACE, CardSuit.CLUB)));
        Player player = new Player("woni", cards);
        player.addCard(new Card(CardNumber.TEN, CardSuit.SPADE));
        player.addCard(new Card(CardNumber.TEN, CardSuit.HEART));
        Assertions.assertThat(player.compareScore(22)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("만약 플레이어와 딜러 모두 무승부이다.")
    void drawNumberTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardNumber.SIX, CardSuit.CLUB), new Card(CardNumber.ACE, CardSuit.CLUB)));
        Player player = new Player("woni", cards);
        Assertions.assertThat(player.compareScore(17)).isEqualTo(GameResult.DRAW);
    }
}
