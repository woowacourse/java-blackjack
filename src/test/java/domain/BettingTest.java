package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.JACK;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.TEN;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardHand;
import domain.card.Deck;
import domain.game.BettingRound;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.shuffler.RandomShuffler;
import fixture.CardFixture;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    public final int BET_AMOUNT = 10000;

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다")
    void playerBettingTest() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND), CardFixture.of(TWO, CLOVER)));
        Player player = new Player("pobi", cardHand);
        BettingRound bettingRound = new BettingRound();
        // when
        bettingRound.bet(player, BET_AMOUNT);
        // then
        assertThat(bettingRound.getPlayerInitialBetAmount(player)).isEqualTo(BET_AMOUNT);
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다")
    void testBetIsLostIfPlayerBusts() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        BettingRound bettingRound = new BettingRound();
        // when
        bettingRound.bet(player, BET_AMOUNT);
        player.hit(CardFixture.of(FIVE, CLOVER));
        bettingRound.betIsLostIfPlayerBusts(player);
        // then
        assertThat(bettingRound.getPlayerFinalBetAmount(player)).isEqualTo(0);
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 받는다")
    void testBlackJackBetAmount() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        BettingRound bettingRound = new BettingRound();
        // when
        bettingRound.bet(player, BET_AMOUNT);
        bettingRound.extraPayoutOnBlackjack(player);
        // then
        assertThat(bettingRound.getPlayerFinalBetAmount(player)).isEqualTo((int) (BET_AMOUNT * 1.5));
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다")
    void testWhenBothBlackJack() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        Dealer dealer = new Dealer(new Deck(new RandomShuffler()), cardHand);
        BettingRound bettingRound = new BettingRound();
        // when
        bettingRound.bet(player, BET_AMOUNT);
        bettingRound.refundBetOnBlackjackPush(player, dealer);
        // then
        assertThat(bettingRound.getPlayerFinalBetAmount(player)).isEqualTo(BET_AMOUNT);
    }

    @Test
    @DisplayName("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 승리해 베팅 금액을 받는다")
    void testPlayersReceiveBetIfDealerBusts() {
        // given
        CardHand playerCardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND)));
        CardHand dealerCardHand = new CardHand(
                Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(JACK, DIAMOND)));
        Player player1 = new Player("pobi", playerCardHand);
        Player player2 = new Player("jason", playerCardHand);
        Dealer dealer = new Dealer(new Deck(new RandomShuffler()), dealerCardHand);
        BettingRound bettingRound = new BettingRound();
        // when
        int player2BetAmount = 20000;
        bettingRound.bet(player1, BET_AMOUNT);
        bettingRound.bet(player2, player2BetAmount);
        dealer.hit(CardFixture.of(QUEEN, HEART));
        bettingRound.PlayersReceiveBetIfDealerBusts(player1, dealer);
        bettingRound.PlayersReceiveBetIfDealerBusts(player2, dealer);
        // then
        assertThat(bettingRound.getPlayerFinalBetAmount(player1)).isEqualTo(BET_AMOUNT * 2);
        assertThat(bettingRound.getPlayerFinalBetAmount(player2)).isEqualTo(player2BetAmount * 2);
    }
}
