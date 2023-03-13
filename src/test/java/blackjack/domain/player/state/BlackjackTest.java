package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Suit;
import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Hand;
import blackjack.domain.result.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackTest {
    Hand hand;
    PlayerStatus playerStatus;

    @BeforeEach
    void setup() {
        hand = new Hand(Card.of(Suit.CLOVER, Letter.ACE), Card.of(Suit.DIAMOND, Letter.KING));
        playerStatus = PlayerStatus.checkInitialBlackJack(hand);
    }

    @Test
    @DisplayName("딜러, 플레이어 모두 블랙잭이면 플레이어 수익 0 확인")
    void profit() {
        double profit = playerStatus.calculateProfit(true,
                new Score(30), new Score(10), new BettingMoney("3000"));

        assertThat(profit).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 플레이어의 수익은 배팅 금액의 1.5배")
    void profit2() {
        double profit = playerStatus.calculateProfit(false,
                new Score(30), new Score(10), new BettingMoney("3000"));

        assertThat(profit).isEqualTo(4500);

    }
}
