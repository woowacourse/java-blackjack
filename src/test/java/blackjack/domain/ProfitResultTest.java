package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.machine.Blackjack;
import blackjack.domain.machine.Profit;
import blackjack.domain.machine.ProfitResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitResultTest {
    private Dealer dealer;
    private Players players;
    private ProfitResult profitResult;

    @BeforeEach
    void setUp() {
        Map<String, Long> playersInfo = new LinkedHashMap<>();
        playersInfo.put("범블비", 10000L);

        players = new Players(playersInfo);
        dealer = new Dealer();

        //dealer: 12점, player: 13점
        IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(1, 2, 11, 10));
        Blackjack.of(intendedNumberGenerator, dealer, players);

        profitResult = ProfitResult.of(dealer, players);
    }

    @DisplayName("플레이어의 수익률 결과를 테스트")
    @Test
    void playerProfit() {
        Map<Participant, Profit> result = profitResult.getResult();

        assertThat(result.get(new Player("범블비", 0L)))
                .isEqualTo(new Profit(10000L));
    }

    @DisplayName("플레이어의 수익률 결과를 테스트")
    @Test
    void dealerProfit() {
        Map<Participant, Profit> result = profitResult.getResult();

        assertThat(result.get(new Dealer()))
                .isEqualTo(new Profit(-10000L));
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

        ProfitResult profitResult = ProfitResult.of(dealer, players);

        assertThat(profitResult.getResult().get(player))
                .isEqualTo(new Profit(15000L));
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

        ProfitResult profitResult = ProfitResult.of(dealer, players);

        assertThat(profitResult.getResult().get(player))
                .isEqualTo(new Profit(10000L));
    }
}
