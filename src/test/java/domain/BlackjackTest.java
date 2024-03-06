package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("딜러에게 2장의 카드가 주어졌는지 확인한다")
    void initializeDealer() {
        Blackjack blackjack = new Blackjack(Players.from("a,b,c".split(",")), new Player(), new Deck());
        blackjack.initializeDealer();
        assertThat(blackjack.getDealer().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 2장의 카드가 주어졌는지 확인한다")
    void initializePlayers() {
        Blackjack blackjack = new Blackjack(Players.from("a,b,c".split(",")), new Player(), new Deck());
        blackjack.initializePlayers();
        assertThat(blackjack.getPlayers().getPlayers().get(0).getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 1장의 카드를 추가로 지급한다")
    void dealCardsToPlayer() {
        Blackjack blackjack = new Blackjack(Players.from("a,b,c".split(",")), new Player(), new Deck());
        Player player = blackjack.getPlayers().getPlayers().get(0);

        blackjack.initializePlayers();
        blackjack.dealCardsToPlayer(player);

        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
