package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Hit;
import blackjack.domain.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Dealer dealer;
    private Player player;
    private Cards blackjackCards;
    private Cards scoreTwentyCards;
    private Cards scoreTenCards;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player("bepoz", 10);
        blackjackCards = new Cards(new Card(CardNumber.ACE, Shape.CLOVER), new Card(CardNumber.KING, Shape.CLOVER));
        scoreTwentyCards = new Cards(new Card(CardNumber.KING, Shape.CLOVER), new Card(CardNumber.KING, Shape.CLOVER));
        scoreTenCards = new Cards(new Card(CardNumber.EIGHT, Shape.HEART), new Card(CardNumber.TWO, Shape.SPADE));
    }

    @Test
    @DisplayName("플레이어가 블랙잭 일 때 확인")
    void playerBlackjackTest() {
        player.state = new Blackjack(blackjackCards);
        dealer.state = new Stay(scoreTwentyCards);
        assertEquals(player.getProfit(dealer), 15);

        dealer.state = new Blackjack(blackjackCards);
        assertEquals(player.getProfit(dealer), 0);
    }

    @Test
    @DisplayName("플레이어가 버스트 일 때 확인")
    void playerBustTest() {
        player.state = new Hit(scoreTwentyCards);
        player.addCard(new Card(CardNumber.QUEEN, Shape.CLOVER));
        assertEquals(player.getProfit(dealer), -10);
    }

    @Test
    @DisplayName("플레이어가 스테이고 딜러가 버스트 또는 블랙잭인 경우 확인")
    void playerStayTestWhenDealerBlackjackOrBust() {
        player.state = new Stay(scoreTwentyCards);
        dealer.state = new Blackjack(blackjackCards);
        assertEquals(player.getProfit(dealer), -10);

        player.state = new Stay(scoreTenCards);
        dealer.state = new Hit(scoreTwentyCards);
        dealer.addCard(new Card(CardNumber.EIGHT, Shape.CLOVER));
        assertEquals(player.getProfit(dealer), 10);
    }

    @Test
    @DisplayName("플레이어가 스테이고 딜러 또한 스테이인 경우")
    void playerStayTestWhenDealerNotBlackjackOrBust() {
        player.state = new Stay(scoreTenCards);
        dealer.state = new Hit(scoreTwentyCards);
        dealer.addCard(new Card(CardNumber.ACE, Shape.CLOVER));
        assertEquals(player.getProfit(dealer), -10);

        player.state = new Stay(scoreTwentyCards);
        dealer.state = new Stay(scoreTenCards);
        assertEquals(player.getProfit(dealer), 10);
    }
}