package domain.player;

import domain.deck.Card;
import domain.deck.Deck;
import domain.deck.Rank;
import domain.deck.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerTest {
    Deck deck = new Deck();

    @DisplayName("플레이어가 처음 카드를 뽑으면 패의 크기는 1이다.")
    @Test
    void drawTest() {

        final String testName = "test";
        Player player = new Player(testName);

        assertEquals(0, player.getCards().size());
        player.drawCard(deck.popCard());
        assertEquals(1, player.getCards().size());
    }

    @DisplayName("플레이어가 딜러보다 점수가 높은 경우 isWin은 True를 반환한다.")
    @Test
    void isWinTrueTest() {
        Player player = new Player("hardy");
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.QUEEN));
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.KING));

        int dealerScore = 19;
        assertTrue(player.isWin(dealerScore));
    }

    @DisplayName("플레이어가 딜러보다 점수가 높지 않을 경우 isWin은 False를 반환한다.")
    @Test
    void isWinFalseTest() {
        Player player = new Player("hardy");
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.QUEEN));
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.KING));

        int dealerScore = 21;
        assertFalse(player.isWin(dealerScore));
    }

    @DisplayName("플레이어가 딜러와 점수가 같을 경우 isDraw은 True를 반환한다.")
    @Test
    void isDrawTrueTest() {
        Player player = new Player("hardy");
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.QUEEN));
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.KING));

        int dealerScore = 20;
        assertTrue(player.isDraw(dealerScore));
    }

    @DisplayName("플레이어가 딜러와 점수가 같지 않을 경우 isDraw은 False를 반환한다.")
    @Test
    void isDrawFalseTest() {
        Player player = new Player("hardy");
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.QUEEN));
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.KING));

        int dealerScore = 19;
        assertFalse(player.isDraw(dealerScore));
    }

    @DisplayName("플레리어의 점수가 21보다 작을 때 false를 반환한다")
    @Test
    void isLargerThanBlackJackNumberFalseTest() {
        Player player = new Player("hardy");
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.QUEEN));
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.KING));

        assertFalse(player.isLargerThanBlackJackNumber());
    }

    @DisplayName("플레리어의 점수가 21보다 작을 때 false를 반환한다")
    @Test
    void isLargerThanBlackJackNumberTrueTest() {
        Player player = new Player("hardy");
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.QUEEN));
        player.drawCard(Card.getCard(Suit.DIAMOND, Rank.KING));
        player.drawCard(Card.getCard(Suit.HEART, Rank.QUEEN));

        assertTrue(player.isLargerThanBlackJackNumber());
    }
}
