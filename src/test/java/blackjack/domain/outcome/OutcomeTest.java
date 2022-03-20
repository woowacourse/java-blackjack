package blackjack.domain.outcome;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.UserName;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutcomeTest {

    @Test
    @DisplayName("딜러가 Bust가 아니고, 플레이어가 Bust라면 플레이어가 패배했다고 판단한다.")
    void if_only_player_bust() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.SPADE));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(Outcome.determinePlayerOutcome(dealer, player)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("플레이어가 Bust가 아니고, 딜러 Bust라면 플레이어가 승리했다고 판단한다.")
    void if_only_dealer_bust() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        assertThat(Outcome.determinePlayerOutcome(dealer, player)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 Bust라면 무승부라고 판단한다.")
    void if_dealer_and_player_bust() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.FIVE, CardType.SPADE));
        dealer.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));
        player.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        assertThat(Outcome.determinePlayerOutcome(dealer, player)).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고, 플레이어가 블랙잭이 아니라면 플레이어가 패배했다고 판단한다.")
    void if_only_dealer_blackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.KING, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(Outcome.determinePlayerOutcome(dealer, player)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 플레이어가 승리했다고 판단한다.")
    void if_only_player_blackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.TEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        player.hit(Card.of(CardNumber.JACK, CardType.DIAMOND));

        assertThat(Outcome.determinePlayerOutcome(dealer, player)).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭이라면 무승부라고 판단한다.")
    void if_dealer_and_player_blackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.KING, CardType.SPADE));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.ACE, CardType.CLOVER));
        player.hit(Card.of(CardNumber.JACK, CardType.DIAMOND));

        assertThat(Outcome.determinePlayerOutcome(dealer, player)).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 Bust, 블랙잭이 아닐경우 점수를 비교하여 승무패를 판단한다.")
    void compare_score() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.FIVE, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));
        dealer.hit(Card.of(CardNumber.THREE, CardType.DIAMOND));

        Player player = new Player(new UserName("aki"));
        player.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        player.hit(Card.of(CardNumber.TEN, CardType.DIAMOND));

        assertThat(Outcome.determinePlayerOutcome(dealer, player)).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 게임결과를 통해 딜러의 승무패를 판단한다.")
    void determine_dealer_outcome() {
        Player aki = new Player(new UserName("aki"));
        Player pobi = new Player(new UserName("pobi"));
        Map<Player, Outcome> playerOutcomes = new HashMap<>();
        playerOutcomes.put(pobi, Outcome.WIN);
        playerOutcomes.put(aki, Outcome.DRAW);

        Map<Outcome, Integer> dealerOutcome = Outcome.determineDealerOutcome(playerOutcomes);

        assertThat(dealerOutcome.get(Outcome.WIN)).isEqualTo(0);
        assertThat(dealerOutcome.get(Outcome.DRAW)).isEqualTo(1);
        assertThat(dealerOutcome.get(Outcome.LOSE)).isEqualTo(1);
    }
}
