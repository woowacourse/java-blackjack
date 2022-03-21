package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.UserName;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetsTest {

    private Bets bets;

    @BeforeEach
    void setup() {
        Map<String, BetMoney> playerNameAndBets = new LinkedHashMap<>();
        playerNameAndBets.put("aki", new BetMoney("10000"));
        bets = new Bets(playerNameAndBets);
    }

    @Test
    @DisplayName("딜러가 Bust가 아니고, 플레이어가 Bust라면 플레이어는 베팅금액을 모두 잃는다.")
    void if_only_player_bust() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.SPADE));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(bets.calculatePlayerProfit(dealer, player)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 Bust가 아니고, 딜러 Bust라면 플레이어는 베팅한 금액만큼 수익을 얻는다.")
    void if_only_dealer_bust() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        assertThat(bets.calculatePlayerProfit(dealer, player)).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 Bust라면 플레이어의 수익은 0이다.")
    void if_dealer_and_player_bust() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));
        player.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        assertThat(bets.calculatePlayerProfit(dealer, player)).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고, 플레이어가 블랙잭이 아니라면 플레이어는 베팅금액을 모두 잃는다.")
    void if_only_dealer_blackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.KING, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(bets.calculatePlayerProfit(dealer, player)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 플레이어는 베팅금액의 1.5배를 얻는다.")
    void if_only_player_blackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        player.hit(Card.of(CardNumber.JACK, CardType.DIAMOND));

        assertThat(bets.calculatePlayerProfit(dealer, player)).isEqualTo(15000);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭이라면 플레이어의 수익은 0이다.")
    void if_dealer_and_player_blackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.KING, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        player.hit(Card.of(CardNumber.JACK, CardType.DIAMOND));

        assertThat(bets.calculatePlayerProfit(dealer, player)).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 Bust, 블랙잭이 아닐경우 점수를 비교하여 수익을 판단한다.")
    void compare_score() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.FIVE, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));
        dealer.hit(Card.of(CardNumber.THREE, CardType.DIAMOND));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(bets.calculatePlayerProfit(dealer, player)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어의 총 수익을 통해 딜러의 수익 판단한다.")
    void calculate_dealer_profit() {
        Player aki = new Player(new UserName("aki"));
        Map<String, Integer> playersProfit = new LinkedHashMap<>();
        playersProfit.put("aki", 10000);

        assertThat(bets.calculateDealerProfit(playersProfit)).isEqualTo(-10000);
    }
}
