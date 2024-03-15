package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackGameTest {

    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    @Test
    void initializeDealer() {
        final Names names = new Names(List.of("pobi", "crong", "tebah"));
        final Players players = Players.from(names, List.of(new BetAmount(100), new BetAmount(100), new BetAmount(100)));
        final Dealer dealer = new Dealer(new Deck());
        final BlackjackGame blackjack = new BlackjackGame(players, dealer);

        assertThat(blackjack.getDealer().hand().size()).isEqualTo(2);
    }

    @DisplayName("플레이어에게 2장의 카드가 주어졌는지 확인한다")
    @Test
    void initializePlayers() {
        final Names names = new Names(List.of("pobi", "crong", "tebah"));
        final Players players = Players.from(names, List.of(new BetAmount(100), new BetAmount(100), new BetAmount(100)));
        final Dealer dealer = new Dealer(new Deck());
        final BlackjackGame blackjack = new BlackjackGame(players, dealer);

        assertThat(blackjack.getPlayers().get(0).hand().size()).isEqualTo(2);
    }

    @DisplayName("플레이어에게 1장의 카드를 추가로 지급한다")
    @Test
    void dealCardsToPlayer() {
        final Names names = new Names(List.of("pobi", "crong", "tebah"));
        final Players players = Players.from(names, List.of(new BetAmount(100), new BetAmount(100), new BetAmount(100)));
        final Dealer dealer = new Dealer(new Deck());
        final BlackjackGame blackjack = new BlackjackGame(players, dealer);
        final Player player = blackjack.getPlayers().get(0);
        blackjack.dealCard(player);

        assertThat(player.hand().size()).isEqualTo(3);
    }
}
