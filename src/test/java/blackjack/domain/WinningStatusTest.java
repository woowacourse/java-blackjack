package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class WinningStatusTest {
    
    @Test
    void 플레이어가_21에_더_근접하면_플레이어가_이긴다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_9, DIAMOND_10);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_5, DIAMOND_1);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_승리);
    }

    @Test
    void 딜러가_21에_더_근접하면_플레이어가_패배한다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_5, DIAMOND_1);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_9, DIAMOND_10);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
    }

    @Test
    void 플레이어와_딜러가_같은_합이면_무승부이다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_5, DIAMOND_1);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_5, DIAMOND_1);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.무승부);
    }

    @Test
    void 플레이어만_버스트라면_플레이어가_패배한다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_9, DIAMOND_10, HEART_3);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_5, DIAMOND_1);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
    }

    @Test
    void 플레이어와_딜러가_버스트라면_플레이어가_패배한다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_9, DIAMOND_10, HEART_3);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_9, DIAMOND_10, HEART_3);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_5, DIAMOND_1);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_9, DIAMOND_10, HEART_3);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_승리);
    }

    @Test
    void 플레이어만_블랙잭이라면_플레이어가_승리한다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_1, DIAMOND_10);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_5, DIAMOND_1);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_승리);
    }
    
    @Test
    void 플레이어와_딜러_모두_블랙잭이라면_무승부이다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_1, DIAMOND_10);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_1, DIAMOND_10);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.무승부);
    }

    @Test
    void 딜러만_블랙잭이라면_플레이어가_패배한다() {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_5, DIAMOND_1);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_1, DIAMOND_10);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
    }

    private static PlayerBlackjackCardHand playerWith(Card... cards) {
        return new PlayerBlackjackCardHand(DEFAULT_PLAYER, () -> List.of(cards));
    }

    private static DealerBlackjackCardHand dealerWith(Card... cards) {
        return new DealerBlackjackCardHand(() -> List.of(cards));
    }
}
