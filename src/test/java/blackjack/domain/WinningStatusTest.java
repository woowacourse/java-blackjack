package blackjack.domain;

import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class WinningStatusTest {
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패_모두_버스트가_아니라면_21에_근접한_손패가_이긴다() {
        final PlayerBlackjackCardHand winner = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        winner.addCard(HEART_10);
        winner.addCard(HEART_9);
        
        final PlayerBlackjackCardHand looser = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        looser.addCard(HEART_9);
        looser.addCard(HEART_8);
        
        assertThat(WinningStatus.determineWinningStatus(winner, looser)).isEqualTo(WinningStatus.WIN);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_한_손패가_버스트라면_버스트가_아닌_손패가_이긴다() {
        final PlayerBlackjackCardHand winner = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        winner.addCard(DIAMOND_10);
        winner.addCard(HEART_10);
        winner.addCard(CLOVER_10);
        
        final PlayerBlackjackCardHand looser = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        looser.addCard(DIAMOND_9);
        looser.addCard(HEART_1);
        
        assertThat(WinningStatus.determineWinningStatus(winner, looser)).isEqualTo(WinningStatus.LOSE);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_버스트라면_무승부이다() {
        final PlayerBlackjackCardHand winner = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        winner.addCard(DIAMOND_10);
        winner.addCard(HEART_10);
        winner.addCard(CLOVER_10);
        
        final PlayerBlackjackCardHand looser = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        looser.addCard(DIAMOND_9);
        looser.addCard(HEART_8);
        looser.addCard(HEART_7);
        
        assertThat(WinningStatus.determineWinningStatus(winner, looser)).isEqualTo(WinningStatus.DRAW);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_모두_21이고_한쪽만_블랙잭이라면_블랙잭이_이긴다() {
        final PlayerBlackjackCardHand winner = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        winner.addCard(DIAMOND_10);
        winner.addCard(DIAMOND_1);
        
        final PlayerBlackjackCardHand looser = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        looser.addCard(DIAMOND_9);
        looser.addCard(HEART_9);
        looser.addCard(HEART_3);
        
        assertThat(WinningStatus.determineWinningStatus(winner, looser)).isEqualTo(WinningStatus.BLACKJACK_WIN);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_모두_21이고_둘다_블랙잭이라면_무승부이다() {
        final PlayerBlackjackCardHand winner = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        winner.addCard(DIAMOND_10);
        winner.addCard(DIAMOND_1);
        
        final PlayerBlackjackCardHand looser = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        looser.addCard(HEART_10);
        looser.addCard(HEART_1);
        
        assertThat(WinningStatus.determineWinningStatus(winner, looser)).isEqualTo(WinningStatus.DRAW);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_모두_21이고_둘다_블랙잭이_아니라면_무승부이다() {
        final PlayerBlackjackCardHand winner = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        winner.addCard(DIAMOND_5);
        winner.addCard(DIAMOND_5);
        winner.addCard(DIAMOND_1);
        
        final PlayerBlackjackCardHand looser = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        looser.addCard(HEART_5);
        looser.addCard(HEART_5);
        looser.addCard(HEART_1);
        
        assertThat(WinningStatus.determineWinningStatus(winner, looser)).isEqualTo(WinningStatus.DRAW);
    }
    
}
