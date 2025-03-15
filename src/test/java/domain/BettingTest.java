package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardHand;
import domain.card.Deck;
import domain.game.BettingSession;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.shuffler.RandomShuffler;
import fixture.CardFixture;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    private final int BET_AMOUNT = 10000;

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다")
    void playerBettingTest() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        BettingSession bettingSession = new BettingSession();
        // when
        bettingSession.bet(player, BET_AMOUNT);
        // then
        assertThat(bettingSession.getBetAmount(player)).isEqualTo(BET_AMOUNT);
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다")
    void testBetIsLostIfPlayerBusts() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        BettingSession bettingSession = new BettingSession();
        // when
        bettingSession.bet(player, BET_AMOUNT);
        player.hit(CardFixture.of(FIVE, CLOVER));
        bettingSession.recordEarningsOnLoss(player);
        // then
        assertThat(bettingSession.getEarnings(player)).isEqualTo(-1 * BET_AMOUNT);
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 받는다")
    void testBlackJackBetAmount() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        BettingSession bettingSession = new BettingSession();
        // when
        bettingSession.bet(player, BET_AMOUNT);
        bettingSession.applyBlackjackBonus(player);
        // then
        assertThat(bettingSession.getEarnings(player)).isEqualTo((int) (BET_AMOUNT * 1.5));
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다")
    void testWhenBothBlackJack() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        Dealer dealer = new Dealer(new Deck(new RandomShuffler()), cardHand);
        BettingSession bettingSession = new BettingSession();
        // when
        bettingSession.bet(player, BET_AMOUNT);
        bettingSession.refundBetOnBlackjackPush(player, dealer);
        // then
        assertThat(bettingSession.getEarnings(player)).isEqualTo(BET_AMOUNT);
    }

    @Test
    @DisplayName("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 승리해 베팅 금액을 받는다")
    void testPlayersReceiveBetIfDealerBusts() {
        // given
        CardHand playerCardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(TWO, DIAMOND)));
        CardHand dealerCardHand = new CardHand(
                Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(JACK, DIAMOND)));
        Player player1 = new Player("pobi", playerCardHand);
        Player player2 = new Player("jason", playerCardHand);
        Dealer dealer = new Dealer(new Deck(new RandomShuffler()), dealerCardHand);
        BettingSession bettingSession = new BettingSession();
        // when
        int player2BetAmount = 20000;
        bettingSession.bet(player1, BET_AMOUNT);
        bettingSession.bet(player2, player2BetAmount);
        dealer.hit(CardFixture.of(QUEEN, HEART));
        bettingSession.rewardEarningsOnDealerBust(player1, dealer);
        bettingSession.rewardEarningsOnDealerBust(player2, dealer);
        // then
        assertThat(bettingSession.getEarnings(player1)).isEqualTo(BET_AMOUNT);
        assertThat(bettingSession.getEarnings(player2)).isEqualTo(player2BetAmount);
    }

    @Test
    @DisplayName("게임 결과를 바탕으로 플레이어가 승리할 경우 최종 수익을 계산한다")
    void testCalculateProfitWhenPlayerWin() {
        // given
        CardHand player1CardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND))); // 블랙잭 승리
        CardHand player2CardHand = new CardHand(
                Set.of(CardFixture.of(TEN, DIAMOND), CardFixture.of(TWO, DIAMOND))); // 승리
        CardHand dealerCardHand = new CardHand(
                Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(JACK, DIAMOND))); // 버스트
        Player player1 = new Player("pobi", player1CardHand);
        Player player2 = new Player("jason", player2CardHand);
        Dealer dealer = new Dealer(new Deck(new RandomShuffler()), dealerCardHand);
        BettingSession bettingSession = new BettingSession();
        // when
        int player2BetAmount = 20000;
        bettingSession.bet(player1, BET_AMOUNT);
        bettingSession.bet(player2, player2BetAmount);
        player2.hit(CardFixture.of(ACE, SPADE));
        dealer.hit(CardFixture.of(QUEEN, HEART));
        bettingSession.calculateProfit(List.of(player1, player2), dealer);
        // then
        assertThat(bettingSession.getPlayerProfit(player1)).isEqualTo((int) (BET_AMOUNT * 1.5));
        assertThat(bettingSession.getPlayerProfit(player2)).isEqualTo(player2BetAmount);
    }

    @Test
    @DisplayName("게임 결과를 바탕으로 딜러가 1승 1패 했을 때 최종 수익을 계산한다")
    void testCalculateProfitWhenDealer1Win1Lose() {
        // given
        CardHand player1CardHand = new CardHand(
                Set.of(CardFixture.of(TWO, HEART), CardFixture.of(EIGHT, DIAMOND))); // 승리
        CardHand player2CardHand = new CardHand(
                Set.of(CardFixture.of(SEVEN, DIAMOND), CardFixture.of(KING, DIAMOND))); // 패배
        CardHand dealerCardHand = new CardHand(
                Set.of(CardFixture.of(THREE, CLOVER), CardFixture.of(NINE, DIAMOND))); // 1승 1패
        Player player1 = new Player("pobi", player1CardHand);
        Player player2 = new Player("jason", player2CardHand);
        Dealer dealer = new Dealer(new Deck(new RandomShuffler()), dealerCardHand);
        BettingSession bettingSession = new BettingSession();
        // when
        int player2BetAmount = 20000;
        bettingSession.bet(player1, BET_AMOUNT);
        bettingSession.bet(player2, player2BetAmount);
        player1.hit(CardFixture.of(ACE, CLOVER));
        dealer.hit(CardFixture.of(EIGHT, HEART));
        bettingSession.calculateProfit(List.of(player1, player2), dealer);
        // then
        assertThat(bettingSession.getPlayerProfit(player1)).isEqualTo(BET_AMOUNT);
        assertThat(bettingSession.getPlayerProfit(player2)).isEqualTo(-1 * player2BetAmount);
    }
}
