package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetTest {

    private final List<Card> blackjackCards = List.of(
            Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART));
    private final List<Card> winCards = List.of(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.HEART), Card.of(Rank.ACE, Suit.DIAMOND));
    private final List<Card> drawCards = List.of(
            Card.of(Rank.TEN, Suit.DIAMOND), Card.of(Rank.TEN, Suit.CLOVER));
    private final List<Card> loseCards = List.of(
            Card.of(Rank.TEN, Suit.HEART), Card.of(Rank.SEVEN, Suit.CLOVER));
    private final List<Card> bustCards = List.of(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.CLOVER), Card.of(Rank.K, Suit.HEART));


    private final Player player = Player.of("pobi");
    private final BigDecimal bettingAmount = new BigDecimal(10_000);

    @Test
    @DisplayName("딜러가 블랙잭이 아니며, 플레이어 카드가 블랙잭인 경우 배팅 금액의 1.5배를 받는다.")
    void player_blackjack_receives_one_and_half_times_bet() {
        PlayerBet playerBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(blackjackCards);

        playerBet.applyResult(Dealer.of(drawCards));

        assertThat(playerBet.amount().intValueExact()).isEqualTo(15_000);
    }

    @Test
    @DisplayName("플레이어 버스트인 경우 배팅 금액을 잃는다.")
    void player_bust_bettingAmount_lost() {
        PlayerBet playerBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(bustCards);

        playerBet.applyResult(Dealer.of(bustCards));

        assertThat(playerBet.amount().intValueExact()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("딜러 점수와 비교해 플레이어가 패배하면 배팅 금액을 잃는다.")
    void dealer_compare_score_if_lose_adjust_bettingAmount() {
        PlayerBet playerBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(loseCards);

        playerBet.applyResult(Dealer.of(winCards));

        assertThat(playerBet.amount().intValueExact()).isEqualTo(-10_000);
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우, 플레이어가 블랙잭이면 수익 0, 아닌 경우 배팅 금액 잃음")
    void dealer_isBlackjack_player_applyProfit() {
        List<Card> blackjackCards = List.of(Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART));
        PlayerBet playerDrawBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(blackjackCards);

        playerDrawBet.applyResult(Dealer.of(blackjackCards));

        assertThat(playerDrawBet.amount()).isZero();
    }

    @Test
    @DisplayName("딜러가 플레이보다 낮은 카드를 받으면 플레이어는 배팅 금액을 번다.")
    void dealer_lose_player_applyProfit() {
        PlayerBet playerDrawBet = PlayerBet.of(player, bettingAmount);
        player.addInitialCards(winCards);

        playerDrawBet.applyResult(Dealer.of(loseCards));

        assertThat(playerDrawBet.amount().intValueExact()).isEqualTo(10_000);
    }

}