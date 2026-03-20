package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("숫자에 대한 카드 점수를 계산한다.")
    void calculateNumberTotalScoreTest() {
        List<Card> cardsList1 = List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        );
        Cards cards1 = new Cards(cardsList1);
        int totalScore1 = cards1.calculateScore();

        List<Card> cardList2 = List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.TWO, CardSuit.CLUB)
        );
        Cards cards2 = new Cards(cardList2);
        int totalScore2 = cards2.calculateScore();

        assertThat(totalScore1).isEqualTo(12);
        assertThat(totalScore2).isEqualTo(10);
    }

    @Test
    @DisplayName("알파벳에 대한 카드 점수를 계산한다. (ex - J, Q, K)")
    void calculateAlphabetTotalScoreTest() {
        List<Card> cardList = List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        );
        Cards cards = new Cards(cardList);
        int totalScore = cards.calculateScore();

        assertThat(totalScore).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace에 대한 점수를 처리한다.")
    void judgeAceTest() {
        List<Card> cardList1 = new ArrayList<>(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        ));
        Cards cards1 = new Cards(cardList1);
        cards1.addCard(new Card(CardScore.ACE, CardSuit.CLUB));
        int player1TotalScore = cards1.calculateScore();

        List<Card> cardList2 = new ArrayList(List.of(
                new Card(CardScore.FOUR, CardSuit.CLUB),
                new Card(CardScore.ACE, CardSuit.CLUB)
        ));
        Cards cards2 = new Cards(cardList2);
        int player2TotalScore = cards2.calculateScore();

        assertThat(player1TotalScore).isEqualTo(15);
        assertThat(player2TotalScore).isEqualTo(15);
    }

    @Test
    @DisplayName("여러 장의 Ace에 대한 점수를 처리한다.")
    void judgeManyAceTest() {
        List<Card> cardList1 = new ArrayList<>(List.of(
                new Card(CardScore.EIGHT, CardSuit.CLUB),
                new Card(CardScore.ACE, CardSuit.CLUB)
        ));
        Cards cards1 = new Cards(cardList1);
        cards1.addCard(new Card(CardScore.ACE, CardSuit.SPADE));
        int player1TotalScore = cards1.calculateScore();

        List<Card> cardList2 = new ArrayList<>(List.of(
                new Card(CardScore.FOUR, CardSuit.CLUB),
                new Card(CardScore.ACE, CardSuit.CLUB)
        ));
        Cards cards2 = new Cards(cardList2);
        cards2.addCard(new Card(CardScore.ACE, CardSuit.SPADE));
        cards2.addCard(new Card(CardScore.ACE, CardSuit.HEART));
        int player2TotalScore = cards2.calculateScore();

        assertThat(player1TotalScore).isEqualTo(20);
        assertThat(player2TotalScore).isEqualTo(17);
    }

    @Test
    @DisplayName("21을 초과하면 버스트이다.")
    void judgeBustTest() {
        List<Card> cardList = new ArrayList<>(List.of(
                new Card(CardScore.JACK, CardSuit.CLUB),
                new Card(CardScore.FOUR, CardSuit.CLUB)
        ));
        Cards cards = new Cards(cardList);
        cards.addCard(new Card(CardScore.EIGHT, CardSuit.CLUB));
        boolean isBust = cards.isBust();

        assertTrue(isBust);
    }

    @Test
    @DisplayName("카드를 한 장 받는다.")
    void receiveOneCardTest() {
        Deck deck = new Deck();
        Card card = deck.draw();

        assertThat(deck.getCards().size()).isEqualTo(51);
        assertThat(card).isInstanceOf(Card.class);
        assertThat(card).isNotIn(deck.getCards());
    }
}
