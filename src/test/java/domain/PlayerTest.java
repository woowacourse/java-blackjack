package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 받으면 핸드의 카드 개수가 증가한다")
    void drawCardByPlayer() {
        Player player = new Player("pobi");
        player.drawCard(new Card(1));
        assertEquals(1, player.getHand().getCards().size());
    }

    @Test
    @DisplayName("딜러가 카드를 받으면 핸드의 카드 개수가 증가한다")
    void drawCardByDealer() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(1));
        assertEquals(1, dealer.getHand().getCards().size());
    }
}