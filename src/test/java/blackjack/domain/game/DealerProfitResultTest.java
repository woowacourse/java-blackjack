package blackjack.domain.game;


import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import blackjack.domain.money.BetAndProfit;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerProfitResultTest {
    private Dealer dealer;
    private Player winPlayer;
    private Player blackjackWinPlayer;
    private Player losePlayer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.of(Hand.of(CLOVER4, CLOVER5));
        winPlayer = Player.of("winPlayer", Hand.of(CLOVER6, CLOVER7));
        blackjackWinPlayer = Player.of("blackjackWinPlayer", Hand.of(CLOVER_ACE, CLOVER10));
        losePlayer = Player.of("losePlayer", Hand.of(CLOVER2, CLOVER3));
    }

    @DisplayName("of 메소드는 Dealer 와 Player 와 BetAndProfit 의 Map 을 전달받아 DealerProfitResult 를 생성해 반환한다.")
    @Test
    void of_returnsInstanceOfDealerProfitResult() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(winPlayer, BetAndProfit.from(Money.from(10000)));
        playerBetAndProfits.put(losePlayer, BetAndProfit.from(Money.from(15000)));
        playerBetAndProfits.put(blackjackWinPlayer, BetAndProfit.from(Money.from(20000)));

        // when
        DealerProfitResult dealerProfitResult = DealerProfitResult.of(dealer, playerBetAndProfits);

        // then
        assertThat(dealerProfitResult).isNotNull();
    }

    @DisplayName("Dealer 가 Player 에게 승리하였을때, 딜러의 수익은 플레이어가 베팅한 액수만큼 증가한다.")
    @Test
    void dealerGetProfitSameAsPlayerBetMoneyOnWin() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(losePlayer, BetAndProfit.from(Money.from(15000)));

        // when
        DealerProfitResult dealerProfitResult = DealerProfitResult.of(dealer, playerBetAndProfits);
        Money actual = dealerProfitResult.getProfit();
        Money expected = Money.from(15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Dealer 가 Player 에게 패배하였을때, 딜러의 수익은 플레이어가 베팅한 액수만큼 차감된다.")
    @Test
    void dealerLoseProfitSameAsPlayerBetMoneyOnLose() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(winPlayer, BetAndProfit.from(Money.from(15000)));

        // when
        DealerProfitResult dealerProfitResult = DealerProfitResult.of(dealer, playerBetAndProfits);
        Money actual = dealerProfitResult.getProfit();
        Money expected = Money.from(-15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Dealer 가 Player 에게 블랙잭으로 패배하였을때, 딜러의 수익은 플레이어가 베팅한 액수 * 1.5 만큼 차감된다.")
    @Test
    void dealerLoseProfitSameAsPlayerBetMultipliedOnePointFiveMoneyOnLoseByBlackjack() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(blackjackWinPlayer, BetAndProfit.from(Money.from(10000)));

        // when
        DealerProfitResult dealerProfitResult = DealerProfitResult.of(dealer, playerBetAndProfits);
        Money actual = dealerProfitResult.getProfit();
        Money expected = Money.from(-15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }


    @DisplayName("Dealer 가 여러 Player 에게 승리하고, 패배하고, 블랙잭으로 패배하였을 때, 딜러의 수익은 복합적으로 계산되어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {"10000,10000,10000,-15000", "15000,20000,10000,-20000", "0,0,0,0"})
    void calculateDealerProfitByPlayers(int losePlayerBetMoneyValue, int winPlayerBetMoneyValue,
                                        int winWithBlackjackPlayerBetMoneyValue, int profitTotal) {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(losePlayer, BetAndProfit.from(Money.from(losePlayerBetMoneyValue)));
        playerBetAndProfits.put(winPlayer, BetAndProfit.from(Money.from(winPlayerBetMoneyValue)));
        playerBetAndProfits.put(blackjackWinPlayer, BetAndProfit.from(Money.from(winWithBlackjackPlayerBetMoneyValue)));

        // when
        DealerProfitResult dealerProfitResult = DealerProfitResult.of(dealer, playerBetAndProfits);
        Money actual = dealerProfitResult.getProfit();
        Money expected = Money.from(profitTotal);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}