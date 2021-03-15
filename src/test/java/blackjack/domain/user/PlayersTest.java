package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private Players players;

    @BeforeEach
    public void setUp() {
        players = new Players(Arrays.asList(
                new Player("amazzi", 10000.0),
                new Player("dani", 10000.0),
                new Player("pobi", 20000.0)));
    }

    @DisplayName("Players 객체를 생성한다.")
    @Test
    public void createPlayers() {
        assertThat(players).isInstanceOf(Players.class);
    }

    @DisplayName("각 플레이어에게 초기에 카드 두장을 배분한다.")
    @Test
    void DistributeToEachPlayer() {
        Deck deck = new Deck();
        players.drawInitialCardsToPlayers(deck);

        assertThat(players.getPlayers().stream()
                .allMatch(player -> player.cards().getCards().size() == 2)).isTrue();
    }

    @DisplayName("각 플레이어의 모든 카드를 보여준다.")
    @Test
    void showCardsByPlayers() {
        Deck deck = new Deck();
        players.drawInitialCardsToPlayers(deck);
        List<Cards> cardsGroup = players.showCardsByPlayers();

        assertThat(cardsGroup.stream()
                .allMatch(cards -> cards.getCards().size() == 2)).isTrue();
    }
}
