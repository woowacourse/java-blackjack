package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BettingTest {
    
    @Test
    void 플레이어가_21에_더_근접하면_플레이어가_이기고_베팅금액만큼_번다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_9, DIAMOND_10);
        final var dealer = dealerWith(DIAMOND_5, DIAMOND_1);
        
        // when
        final var result = player.compareHand(dealer);
        
        // then
        assertThat(result).isEqualTo(1000);
    }
    
    @Test
    void 딜러가_21에_더_근접하면_플레이어가_패배하고_베팅금액을_잃는다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_5, DIAMOND_1);
        final var dealer = dealerWith(DIAMOND_9, DIAMOND_10);

        // when
        final var result = player.compareHand(dealer);

        // then
        assertThat(result).isEqualTo(-1000);
    }

    @Test
    void 플레이어와_딜러가_같은_합이면_무승부이고_이익은_없다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_5, DIAMOND_1);
        final var dealer = dealerWith(DIAMOND_5, DIAMOND_1);
        
        // when
        final var result = player.compareHand(dealer);
        
        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void 플레이어만_버스트라면_플레이어가_패배하고_베팅금액을_잃는다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_10, DIAMOND_9, DIAMOND_5);
        final var dealer = dealerWith(DIAMOND_5, DIAMOND_1);
        
        // when
        final var result = player.compareHand(dealer);
        
        // then
        assertThat(result).isEqualTo(-1000);
    }

    @Test
    void 플레이어와_딜러가_버스트라면_플레이어가_패배하고_베팅금액을_잃는다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_10, DIAMOND_9, DIAMOND_5);
        final var dealer = dealerWith(DIAMOND_10, DIAMOND_9, DIAMOND_5);
        
        // when
        final var result = player.compareHand(dealer);
        
        // then
        assertThat(result).isEqualTo(-1000);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리하고_베팅금액만큼_번다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_5, DIAMOND_1);
        final var dealer = dealerWith(DIAMOND_9, DIAMOND_10, HEART_3);

        // when
        final var result = player.compareHand(dealer);
        
        // then
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이라면 베팅금액의 1.5배를 번다")
    void 플레이어만_블랙잭이라면_플레이어가_승리하고_베팅금액의_1점5배를_번다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_1, DIAMOND_10);
        final var dealer = dealerWith(DIAMOND_5, DIAMOND_1);

        // when
        final var result = player.compareHand(dealer);

        // then
        assertThat(result).isEqualTo(1500);
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭이라면_무승부이고_이익은_없다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_1, DIAMOND_10);
        final var dealer = dealerWith(DIAMOND_1, DIAMOND_10);

        // when
        final var result = player.compareHand(dealer);
        
        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void 딜러만_블랙잭이라면_플레이어가_패배한다() {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_5, DIAMOND_1);
        final var dealer = dealerWith(DIAMOND_1, DIAMOND_10);

        // when
        final var result = player.compareHand(dealer);
        
        // then
        assertThat(result).isEqualTo(-1000);
    }
    
    private static PlayerBettingBlackjackCardHand bettingPlayerWith(int money, Card... cards) {
        return PlayerBettingBlackjackCardHand.from(DEFAULT_PLAYER, money, () -> List.of(cards));
    }
    
    private static DealerBlackjackCardHand dealerWith(Card... cards) {
        return new DealerBlackjackCardHand(() -> List.of(cards));
    }
}
