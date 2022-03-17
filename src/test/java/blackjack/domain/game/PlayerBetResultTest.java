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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

public class PlayerBetResultTest {
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

    @DisplayName("of 메소드는 Player 와 BetAndProfit 의 Map 과 Dealer 를 전달받아 PlayerBetResult 를 생성해 반환한다.")
    @Test
    void of_returnsInstanceOfPlayerBetResult() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(winPlayer, BetAndProfit.from(Money.from(10000)));
        playerBetAndProfits.put(losePlayer, BetAndProfit.from(Money.from(10000)));

        // when
        PlayerBetResult playerBetResult = PlayerBetResult.of(playerBetAndProfits, dealer);

        // then
        assertThat(playerBetResult).isNotNull();
    }

    @DisplayName("findBetAndProfitBy 메소드에 Player 를 전달하면 해당 Player 의 BetAndProfit 을 반환한다.")
    @Test
    void findBetAndProfitBy_withPlayerReturnsBetAndProfit() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(winPlayer, BetAndProfit.from(Money.from(15000)));
        playerBetAndProfits.put(losePlayer, BetAndProfit.from(Money.from(20000)));

        System.out.println(playerBetAndProfits);

        // when
        PlayerBetResult playerBetResult = PlayerBetResult.of(playerBetAndProfits, dealer);
        Money actual = playerBetResult.findBetAndProfitBy(winPlayer).getBetMoney();
        Money expected = Money.from(15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("findBy 메소드에 존재하지 않는 Player 를 전달하면 예외가 발생한다.")
    @Test
    void findBy_withNotExistingPlayerThrowsException() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(winPlayer, BetAndProfit.from(Money.from(15000)));

        // when
        PlayerBetResult playerBetResult = PlayerBetResult.of(playerBetAndProfits, dealer);

        // then
        assertThatThrownBy(() -> playerBetResult.findBetAndProfitBy(losePlayer))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("존재하지 않는 플레이어입니다.");
    }

    @DisplayName("Player 가 Dealer 에게 승리하였을때, 플레이어의 수익은 베팅한 액수대로 설정된다.")
    @Test
    void playerGetProfitSameAsBetMoneyOnWin() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(winPlayer, BetAndProfit.from(Money.from(15000)));
        PlayerBetResult playerBetResult = PlayerBetResult.of(playerBetAndProfits, dealer);

        // when
        BetAndProfit betAndProfit = playerBetResult.findBetAndProfitBy(winPlayer);
        Money actual = betAndProfit.getProfitMoney();
        Money expected = Money.from(15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Player 가 Dealer 에게 블랙잭으로 승리하였을때, 플레이어의 수익은 베팅한 액수대로 설정된다.")
    @Test
    void playerGetBlackjackProfitOnWinWithBlackjack() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(blackjackWinPlayer, BetAndProfit.from(Money.from(10000)));
        PlayerBetResult playerBetResult = PlayerBetResult.of(playerBetAndProfits, dealer);

        // when
        BetAndProfit betAndProfit = playerBetResult.findBetAndProfitBy(blackjackWinPlayer);
        Money actual = betAndProfit.getProfitMoney();
        Money expected = Money.from(15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Player 가 Dealer 에게 패배하였을때, 플레이어의 수익은 베팅한 액수의 음수로 설정된다.")
    @Test
    void playerLoseProfitSameAsNegativeBetMoneyOnLose() {
        // given
        Map<Player, BetAndProfit> playerBetAndProfits = new HashMap<>();
        playerBetAndProfits.put(losePlayer, BetAndProfit.from(Money.from(15000)));
        PlayerBetResult playerBetResult = PlayerBetResult.of(playerBetAndProfits, dealer);

        // when
        BetAndProfit betAndProfit = playerBetResult.findBetAndProfitBy(losePlayer);
        Money actual = betAndProfit.getProfitMoney();
        Money expected = Money.from(-15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
