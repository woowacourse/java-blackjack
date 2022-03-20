package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.machine.Blackjack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Player;

public class BlackjackTest {
    private Blackjack blackjack;
    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Map<String, Long> playersInfo = new LinkedHashMap<>();
        playersInfo.put("범블비",1000L);
        playersInfo.put("잉", 2000L);

        players = new Players(playersInfo);
    }

    @DisplayName("플레이어, 딜러 카드 두장 분배 테스트")
    @Test
    void distributeInit() {
        dealer = new Dealer();
        blackjack = new Blackjack();
        blackjack.dealInitialCards(dealer, players);

        Set<Integer> checker = new HashSet<>();
        int dealerCardSize = dealer.getMyCards().size();
        checker.add(dealerCardSize);

        List<Player> getPlayers = players.getPlayers();
        getPlayers.forEach(player -> checker.add(player.getMyCards().size()));

        assertThat(checker.size() == 1).isTrue();
    }

    @DisplayName("카드 한장 더 분배 테스트_딜러")
    @Test
    void distributeOneMoreCardDealer() {
        dealer = new Dealer();
        blackjack = new Blackjack();
        dealer.addCard(Card.TWO_HEART);
        dealer.addCard(Card.FIVE_CLOVER);

        blackjack.dealAdditionalCardToDealer(dealer);

        assertThat(dealer.getMyCards().size()).isEqualTo(3);
    }

    @DisplayName("카드 한장 더 분배 테스트_플레이어")
    @Test
    void distributeOneMoreCardPlayer() {
        blackjack = new Blackjack();
        Player player = players.getPlayers().get(0);
        player.addCard(Card.TWO_HEART);
        player.addCard(Card.FIVE_CLOVER);

        blackjack.dealAdditionalCardToPlayer(player);

        assertThat(player.getMyCards().size()).isEqualTo(3);
    }

    @DisplayName("최종 승패 기능 테스트")
    @Test
    void result() {
        assertDoesNotThrow(() -> blackjack.record(dealer, players));
    }

    @DisplayName("최종 수익 기능 테스트")
    @Test
    void profitResult() {
        assertDoesNotThrow(() -> blackjack.profit(dealer, players));
    }
}