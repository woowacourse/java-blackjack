package blackjack.domain.gametable;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.FixedCardDeck;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTableTest {
    @Test
    @DisplayName("초기 카드 2장 배부 확인")
    void create() {
        final Participant dealer = new Dealer();
        final Player player = new Player();
        List<Player> playersValue = Arrays.asList(player);
        final Players players = new Players(playersValue);

        new GameTable(dealer, players, new FixedCardDeck());

        assertThat(dealer.getUnmodifiableCards()).hasSize(2);
        assertThat(player.getUnmodifiableCards()).hasSize(2);
    }

    @Test
    @DisplayName("추가 카드 배부 여부 확인")
    void giveCard() {
        final Participant dealer = new Dealer();
        final Player player = new Player();
        List<Player> playersValue = Arrays.asList(player);
        final Players players = new Players(playersValue);

        final GameTable gameTable = new GameTable(dealer, players, new FixedCardDeck());
        gameTable.giveCard(player);

        assertThat(player.getUnmodifiableCards()).hasSize(3);


    }

}