package blackjack.model.rounds;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutcomeTest {
    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.initializeDealerWithHand();

        players = new Players();
        players.initializePlayersWithHand(dealer, List.of("a", "b"), List.of(1000, 2000));
    }

    @DisplayName("딜러는 최종 게임 결과를 생성한다")
    @Test
    void testDealerOutcome() {
        Outcome dealerOutcome = new Outcome();

        dealerOutcome.checkFinalOutcome(dealer, players);

        assertThat(dealerOutcome).isNotNull();
    }

    @DisplayName("플레이어는 최종 게임 결과를 생성한다")
    @Test
    void testPlayerOutcome() {
        Outcome playersOutcome = new Outcome();

        playersOutcome.checkFinalOutcome(dealer, players);

        assertThat(playersOutcome).isNotNull();
    }
}
