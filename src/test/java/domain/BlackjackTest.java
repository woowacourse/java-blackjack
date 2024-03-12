package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackTest {

    @Test
    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    void initializeDealer() {
        final Players players = Players.from(List.of("pobi", "crong", "tebah"));
        final Dealer dealer = new Dealer(new Deck());
        final Blackjack blackjack = new Blackjack(players, dealer);

        assertThat(blackjack.getDealer().hand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 2장의 카드가 주어졌는지 확인한다")
    void initializePlayers() {
        final Players players = Players.from(List.of("pobi", "crong", "tebah"));
        final Dealer dealer = new Dealer(new Deck());
        final Blackjack blackjack = new Blackjack(players, dealer);

        assertThat(blackjack.getPlayers().get(0).hand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 1장의 카드를 추가로 지급한다")
    void dealCardsToPlayer() {
        final Players players = Players.from(List.of("pobi", "crong", "tebah"));
        final Dealer dealer = new Dealer(new Deck());
        final Blackjack blackjack = new Blackjack(players, dealer);
        final Player player = blackjack.getPlayers().get(0);
        blackjack.dealCard(player);

        assertThat(player.hand().size()).isEqualTo(3);
    }
}
