package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetTest {

    private final Player player = Player.of("pobi");
    private final BigDecimal bettingAmount = new BigDecimal(10_000);

    @Test
    @DisplayName("플레이어 카드가 블랙잭인 경우 배팅 금액의 1.5배를 받는다.")
    void player_blackjack_receives_one_and_half_times_bet() {
        List<Card> blackjackCards = List.of(Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART));
        PlayerBet playerBet = PlayerBet.of(player, bettingAmount);

        player.addInitialCards(blackjackCards);
        playerBet.applyProfitIfBlackjackAndReturnApplied();

        assertThat(playerBet.amount().intValueExact()).isEqualTo(15_000);
    }

    @Test
    @DisplayName("플레이어 버스트인 경우 배팅 금액을 잃는다.")
    void player_bust_bettingAmount_lost() {
        List<Card> bustCards = List.of(Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.HEART),
                Card.of(Rank.K, Suit.CLOVER));
        PlayerBet playerBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(bustCards);

        playerBet.IsBustLoseBettingAmount();

        assertThat(playerBet.amount()).isEqualTo("-10000");
    }

    @Test
    @DisplayName("딜러 점수와 비교해 플레이어가 패배하면 배팅 금액을 잃는다.")
    void dealer_compare_score_if_lose_adjust_bettingAmount() {
        List<Card> loseCards = List.of(Card.of(Rank.J, Suit.SPADE), Card.of(Rank.FIVE, Suit.HEART));
        PlayerBet playerBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(loseCards);

        List<Card> winCards = List.of(Card.of(Rank.J, Suit.HEART), Card.of(Rank.SEVEN, Suit.HEART));
        Dealer dealer = Dealer.of(winCards);

        playerBet.applyProfitByDealerScore(dealer.calculateScore());

        assertThat(playerBet.amount()).isEqualTo(new BigDecimal(-10_000));
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우, 플레이어가 블랙잭이면 수익 0, 아닌 경우 배팅 금액 잃음")
    void dealer_isBlackjack_player_applyProfit() {
        List<Card> blackjackCards = List.of(Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART));
        PlayerBet playerDrawBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(blackjackCards);

        playerDrawBet.applyProfitIfDealerBlackjack();

        assertThat(playerDrawBet.amount()).isEqualTo("0");
    }

}