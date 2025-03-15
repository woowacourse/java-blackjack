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
import domain.card.Rank;
import domain.card.Suit;
import domain.game.BettingSession;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.shuffler.RandomShuffler;
import fixture.CardFixture;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    private final int PLAYER1_BET_AMOUNT = 10000;
    private final int PLAYER2_BET_AMOUNT = 20000;
    private BettingSession bettingSession;

    @BeforeEach
    void setUp() {
        bettingSession = new BettingSession();
    }

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다")
    void playerBettingTest() {
        // given
        Player player = createPlayer("pobi", TEN, HEART, NINE, DIAMOND);
        // when
        bettingSession.bet(player, PLAYER1_BET_AMOUNT);
        // then
        assertThat(bettingSession.getBetAmount(player)).isEqualTo(PLAYER1_BET_AMOUNT);
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다")
    void testBetIsLostIfPlayerBusts() {
        // given
        Player player = createPlayer("pobi", TEN, HEART, NINE, DIAMOND);
        Dealer dealer = createDealer(TEN, SPADE, NINE, CLOVER);
        // when
        bettingSession.bet(player, PLAYER1_BET_AMOUNT);
        player.hit(CardFixture.of(FIVE, CLOVER));
        bettingSession.calculateProfit(List.of(player), dealer);
        // then
        assertThat(bettingSession.getEarnings(player)).isEqualTo(-PLAYER1_BET_AMOUNT);
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 받는다")
    void testBlackJackBetAmount() {
        // given
        Player player = createPlayer("pobi", TEN, HEART, ACE, DIAMOND);
        Dealer dealer = createDealer(TEN, SPADE, NINE, CLOVER);
        // when
        bettingSession.bet(player, PLAYER1_BET_AMOUNT);
        bettingSession.calculateProfit(List.of(player), dealer);
        // then
        assertThat(bettingSession.getEarnings(player)).isEqualTo((int) (PLAYER1_BET_AMOUNT * 1.5));
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다")
    void testWhenBothBlackJack() {
        // given
        Player player = createPlayer("pobi", TEN, HEART, ACE, DIAMOND);
        Dealer dealer = createDealer(TEN, SPADE, ACE, CLOVER);
        // when
        bettingSession.bet(player, PLAYER1_BET_AMOUNT);
        bettingSession.calculateProfit(List.of(player), dealer);
        // then
        assertThat(bettingSession.getEarnings(player)).isEqualTo(PLAYER1_BET_AMOUNT);
    }

    @Test
    @DisplayName("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 승리해 베팅 금액을 받는다")
    void testPlayersReceiveBetIfDealerBusts() {
        // given
        Player player1 = createPlayer("pobi", TEN, HEART, TWO, DIAMOND);
        Player player2 = createPlayer("jason", NINE, HEART, THREE, DIAMOND);
        Dealer dealer = createDealer(TEN, SPADE, JACK, CLOVER);
        // when
        bettingSession.bet(player1, PLAYER1_BET_AMOUNT);
        bettingSession.bet(player2, PLAYER2_BET_AMOUNT);
        dealer.hit(CardFixture.of(QUEEN, HEART));
        bettingSession.calculateProfit(List.of(player1, player2), dealer);
        // then
        assertThat(bettingSession.getEarnings(player1)).isEqualTo(PLAYER1_BET_AMOUNT);
        assertThat(bettingSession.getEarnings(player2)).isEqualTo(PLAYER2_BET_AMOUNT);
    }

    @Test
    @DisplayName("게임 결과를 바탕으로 플레이어가 승리할 경우 최종 수익을 계산한다")
    void testCalculateProfitWhenPlayerWin() {
        // given
        Player player1 = createPlayer("pobi", TEN, HEART, ACE, DIAMOND); // 블랙잭 승리
        Player player2 = createPlayer("jason", TEN, DIAMOND, TWO, DIAMOND); // 승리
        Dealer dealer = createDealer(TEN, SPADE, JACK, CLOVER); // 버스트
        // when
        bettingSession.bet(player1, PLAYER1_BET_AMOUNT);
        bettingSession.bet(player2, PLAYER2_BET_AMOUNT);
        player2.hit(CardFixture.of(ACE, SPADE));
        dealer.hit(CardFixture.of(QUEEN, HEART));
        bettingSession.calculateProfit(List.of(player1, player2), dealer);
        // then
        assertThat(bettingSession.getPlayerProfit(player1)).isEqualTo((int) (PLAYER1_BET_AMOUNT * 1.5));
        assertThat(bettingSession.getPlayerProfit(player2)).isEqualTo(PLAYER2_BET_AMOUNT);
        assertThat(bettingSession.getDealerProfit()).isEqualTo(
                (int) (-PLAYER1_BET_AMOUNT * 1.5) - PLAYER2_BET_AMOUNT);
    }

    @Test
    @DisplayName("게임 결과를 바탕으로 딜러가 1승 1패 했을 때 최종 수익을 계산한다")
    void testCalculateProfitWhenDealer1Win1Lose() {
        // given
        Player player1 = createPlayer("pobi", TWO, HEART, EIGHT, DIAMOND); // 승리
        Player player2 = createPlayer("jason", SEVEN, DIAMOND, KING, DIAMOND); // 패배
        Dealer dealer = createDealer(THREE, SPADE, NINE, CLOVER); // 1승 1패
        // when
        bettingSession.bet(player1, PLAYER1_BET_AMOUNT);
        bettingSession.bet(player2, PLAYER2_BET_AMOUNT);
        player1.hit(CardFixture.of(ACE, CLOVER));
        dealer.hit(CardFixture.of(EIGHT, HEART));
        bettingSession.calculateProfit(List.of(player1, player2), dealer);
        // then
        assertThat(bettingSession.getPlayerProfit(player1)).isEqualTo(PLAYER1_BET_AMOUNT);
        assertThat(bettingSession.getPlayerProfit(player2)).isEqualTo(-PLAYER2_BET_AMOUNT);
        assertThat(bettingSession.getDealerProfit()).isEqualTo(-PLAYER1_BET_AMOUNT + PLAYER2_BET_AMOUNT);
    }

    private Player createPlayer(String name, Rank rank1, Suit suit1, Rank rank2, Suit suit2) {
        return new Player(name, new CardHand(Set.of(
                CardFixture.of(rank1, suit1),
                CardFixture.of(rank2, suit2)
        )));
    }

    private Dealer createDealer(Rank rank1, Suit suit1, Rank rank2, Suit suit2) {
        return new Dealer(new Deck(new RandomShuffler()), new CardHand(Set.of(
                CardFixture.of(rank1, suit1),
                CardFixture.of(rank2, suit2)
        )));
    }
}
