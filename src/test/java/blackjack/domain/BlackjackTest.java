package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.machine.Blackjack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.strategy.NumberGenerator;

public class BlackjackTest {
    private Blackjack blackjack;
    private Players players;
    private Dealer dealer;
    private IntendedNumberGenerator intendedNumberGenerator;

    @BeforeEach
    void setUp() {
        List<String> playerNames = List.of("pobi", "jason");
        players = new Players(playerNames);
        dealer = new Dealer();
        blackjack = new Blackjack();
        intendedNumberGenerator = new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));

        blackjack.dealInitialCards(intendedNumberGenerator, dealer, players);
    }

    @DisplayName("플레이어, 딜러 카드 두장 분배 테스트")
    @Test
    void distributeInit() {
        Set<Integer> checker = new HashSet<>();
        int dealerCardSize = dealer.getMyCards().size();
        checker.add(dealerCardSize);

        List<Player> getPlayers = players.getPlayers();
        getPlayers.forEach(player -> checker.add(player.getMyCards().size()));

        assertThat(checker.size() == 1 && checker.contains(2)).isTrue();
    }

    @DisplayName("카드 한장 더 분배 테스트_딜러 hit")
    @Test
    void distributeOneMoreCardDealer() {
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(10));
        blackjack.dealAdditionalCardToDealer(numberGenerator, dealer);
        assertThat(dealer.getMyCards().size()).isEqualTo(3);
    }

    @DisplayName("카드 한장 더 분배 테스트_딜러 stay")
    @Test
    void distributeOneMoreCardDealer2() {
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(10, 4));
        blackjack.dealAdditionalCardToDealer(numberGenerator, dealer);
        assertThat(dealer.getMyCards().size()).isEqualTo(3);
    }

    @DisplayName("카드 한장 더 분배 테스트_플레이어")
    @Test
    void distributeOneMoreCardPlayer() {
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(10));
        Player player = players.getPlayers().get(0);
        blackjack.dealAdditionalCardToPlayer(numberGenerator, player);

        assertThat(player.getMyCards().size()).isEqualTo(3);
    }

    @DisplayName("최종 승패 기능 테스트")
    @Test
    void result() {
        assertDoesNotThrow(() -> blackjack.result(dealer, players));
    }
}
