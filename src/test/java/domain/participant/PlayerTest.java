package domain.participant;

import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.Money;
import domain.card.Card;
import domain.card.CardSuit;
import domain.card.CardValue;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("숫자에 대한 카드 점수를 계산한다.")
    void calculateNumberTotalScoreTest() {
        List<Card> cards1 = List.of(new Card(CardValue.EIGHT, CardSuit.CLUB),
                new Card(CardValue.FOUR, CardSuit.CLUB));
        Player player1 = new Player(cards1, "pobi", new Money(1000));

        int totalScore1 = player1.calculateScore();

        List<Card> cards2 = List.of(new Card(CardValue.EIGHT, CardSuit.CLUB), new Card(CardValue.TWO, CardSuit.CLUB));
        Player player2 = new Player(cards2, "woni", new Money(1000));

        int totalScore2 = player2.calculateScore();

        Assertions.assertThat(totalScore1).isEqualTo(12);
        Assertions.assertThat(totalScore2).isEqualTo(10);
    }

    @Test
    @DisplayName("알파벳에 대한 카드 점수를 계산한다. (ex - J, Q, K)")
    void calculateAlphabetTotalScoreTest() {
        List<Card> cards = List.of(new Card(CardValue.JACK, CardSuit.CLUB), new Card(CardValue.FOUR, CardSuit.CLUB));

        Player player = new Player(cards, "pobi", new Money(1000));

        int totalScore = player.calculateScore();

        Assertions.assertThat(totalScore).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace에 대한 점수를 처리한다.")
    void judgeAceTest() {
        List<Card> cards1 = new ArrayList(
                List.of(new Card(CardValue.JACK, CardSuit.CLUB), new Card(CardValue.FOUR, CardSuit.CLUB)));
        Player player1 = new Player(cards1, "pobi", new Money(1000));
        player1.addCard(new Card(CardValue.ACE, CardSuit.CLUB));
        int player1TotalScore = player1.calculateScore();

        List<Card> cards2 = new ArrayList(
                List.of(new Card(CardValue.FOUR, CardSuit.CLUB), new Card(CardValue.ACE, CardSuit.CLUB)));
        Player player2 = new Player(cards2, "woni", new Money(1000));
        int player2TotalScore = player2.calculateScore();

        Assertions.assertThat(player1TotalScore).isEqualTo(15);
        Assertions.assertThat(player2TotalScore).isEqualTo(15);
    }

    @Test
    @DisplayName("여러 장의 Ace에 대한 점수를 처리한다.")
    void judgeManyAceTest() {
        List<Card> cards1 = new ArrayList<>(
                List.of(new Card(CardValue.EIGHT, CardSuit.CLUB), new Card(CardValue.ACE, CardSuit.CLUB)));
        Player player1 = new Player(cards1, "pobi", new Money(1000));
        player1.addCard(new Card(CardValue.ACE, CardSuit.SPADE));
        int player1TotalScore = player1.calculateScore();

        List<Card> cards2 = new ArrayList<>(
                List.of(new Card(CardValue.FOUR, CardSuit.CLUB), new Card(CardValue.ACE, CardSuit.CLUB)));
        Player player2 = new Player(cards2, "woni", new Money(1000));
        player2.addCard(new Card(CardValue.ACE, CardSuit.SPADE));
        player2.addCard(new Card(CardValue.ACE, CardSuit.HEART));
        int player2TotalScore = player2.calculateScore();

        Assertions.assertThat(player1TotalScore).isEqualTo(20);
        Assertions.assertThat(player2TotalScore).isEqualTo(17);
    }

    @Test
    @DisplayName("21을 초과하면 버스트이다.")
    void judgeBustTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardValue.JACK, CardSuit.CLUB), new Card(CardValue.FOUR, CardSuit.CLUB)));
        Player player = new Player(cards, "pobi", new Money(1000));
        player.addCard(new Card(CardValue.EIGHT, CardSuit.CLUB));
        boolean isBust = player.isBust();

        assertTrue(isBust);
    }

    @Test
    @DisplayName("카드를 한 장 받는다.")
    void receiveOneCardTest() {
        Deck deck = new Deck();
        Card card = deck.draw();

        Assertions.assertThat(deck.getCards().size()).isEqualTo(51);
        Assertions.assertThat(card).isInstanceOf(Card.class);
        Assertions.assertThat(card).isNotIn(deck.getCards());
    }
}
