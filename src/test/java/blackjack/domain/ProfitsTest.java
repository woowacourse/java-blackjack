package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.machine.Profit;
import blackjack.domain.machine.Profits;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitsTest {
    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        Map<String, Long> playersInfo = new LinkedHashMap<>();
        playersInfo.put("범블비", 10000L);

        players = new Players(playersInfo);
        dealer = new Dealer();
    }

    @DisplayName("플레이어의 수익률 결과를 테스트")
    @Test
    void playerProfit() {
        Player player = players.getPlayers().get(0);
        player.addCard(Card.J_CLOVER);
        player.addCard(Card.TWO_CLOVER);

        Dealer dealer = new Dealer();
        dealer.addCard(Card.EIGHT_CLOVER);
        dealer.addCard(Card.THREE_SPADE);

        Profits profits = Profits.of(dealer, players);
        Map<Participant, Profit> result = profits.getResult();

        assertThat(result.get(player).getMoney())
                .isEqualTo(new Profit(10000L).getMoney());
    }

    @DisplayName("딜러의 수익률 결과를 테스트")
    @Test
    void dealerProfit() {
        Player player = players.getPlayers().get(0);
        player.addCard(Card.FIVE_CLOVER);
        player.addCard(Card.TWO_HEART);

        Dealer dealer = new Dealer();
        dealer.addCard(Card.EIGHT_CLOVER);
        dealer.addCard(Card.TWO_DIAMOND);

        Profits profits = Profits.of(dealer, players);
        Map<Participant, Profit> result = profits.getResult();

        assertThat(result.get(new Dealer()).getMoney())
                .isEqualTo(new Profit(10000L).getMoney());
    }

    @DisplayName("플레이어가 블랙잭인 경우 수익률 테스트")
    @Test
    void OnlyPlayerBlackjack() {
        Player player = players.getPlayers().get(0);
        player.addCard(Card.A_CLOVER);
        player.addCard(Card.J_CLOVER);

        Dealer dealer = new Dealer();
        dealer.addCard(Card.EIGHT_CLOVER);
        dealer.addCard(Card.FIVE_CLOVER);

        Profits profits = Profits.of(dealer, players);

        assertThat(profits.getResult().get(player).getMoney())
                .isEqualTo(new Profit(15000L).getMoney());
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 수익률 테스트")
    @Test
    void alsoDealerBlackjack() {
        Player player = players.getPlayers().get(0);
        player.addCard(Card.A_CLOVER);
        player.addCard(Card.J_CLOVER);

        Dealer dealer = new Dealer();
        dealer.addCard(Card.A_SPADE);
        dealer.addCard(Card.J_SPADE);

        Profits profits = Profits.of(dealer, players);

        assertThat(profits.getResult().get(player).getMoney())
                .isEqualTo(new Profit(10000L).getMoney());
    }
}