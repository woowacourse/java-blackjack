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

class StayTest {
    Hand hand;
    PlayerStatus playerStatus;
    BettingMoney bettingMoney;
    double profit;

    @BeforeEach
    void setUp() {
        hand = new Hand(Card.of(Suit.DIAMOND, Letter.KING), Card.of(Suit.CLOVER, Letter.JACK));
        playerStatus = new Stay(hand);
        bettingMoney = new BettingMoney("3000");
    }

    @Test
    @DisplayName("Stay 시 딜러가 bust 면 수익 베팅한 머니만큼인지 확인")
    void dealerBust() {
        profit = playerStatus.calculateProfit(false,
                new Score(22), hand.calculateScore(), bettingMoney);
        assertThat(profit).isEqualTo(3000);
    }

    @Test
    @DisplayName("딜러와 동점이면 수익 0")
    void equalScore() {
        profit = playerStatus.calculateProfit(false,
                new Score(20), hand.calculateScore(), bettingMoney);
        assertThat(profit).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러 점수가 높으면 수익은 베팅한 금액 * -1")
    void lose() {
        profit = playerStatus.calculateProfit(false,
                new Score(21), hand.calculateScore(), bettingMoney);
        assertThat(profit).isEqualTo(-3000);
    }

    @Test
    @DisplayName("플레이어 점수가 높으면 수익은 베팅한 금액만큼")
    void win() {
        profit = playerStatus.calculateProfit(false,
                new Score(19), hand.calculateScore(), bettingMoney);
        assertThat(profit).isEqualTo(3000);
    }
}
