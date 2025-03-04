package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {
    @Test
    @DisplayName("플레이어가 승리할 경우 테스트")
    void 플레이어가_승리할_경우_테스트(){
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));

        Player player = new Player();
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        Judge judge = new Judge();
        boolean result = judge.checkPlayerWin(dealer, player);
        assertTrue(result);
    }

    @Test
    @DisplayName("플레이어가 패배할 경우 테스트")
    void 플레이어가_패배할_경우_테스트(){
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        Player player = new Player();
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));

        Judge judge = new Judge();
        boolean result = judge.checkPlayerWin(dealer, player);
        assertFalse(result);
    }
}