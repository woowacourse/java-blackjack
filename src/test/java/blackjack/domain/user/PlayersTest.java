package blackjack.domain.user;

import blackjack.domain.card.Deck;
import blackjack.domain.card.NoneStrategy;
import blackjack.domain.user.exception.PlayersException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayersTest {

    @Test
    void of() {
        assertThat(Players.of("무늬, 그니, 포비")).isNotNull();
    }

    @Test
    void of_DuplicatedNames_ShouldThrowException() {
        assertThatThrownBy(() -> {
            Players.of("무늬, 무늬, 포비");
        }).isInstanceOf(PlayersException.class)
        .hasMessage("중복된 이름이 있으면 안 됩니다.");
    }

    @Test
    void of_DealerName_ShouldThrowException() {
        assertThatThrownBy(() -> {
            Players.of("무늬, 그니, 포비, 딜러");
        }).isInstanceOf(PlayersException.class)
        .hasMessage("플레이어의 이름은 딜러일 수 없습니다.");
    }

    @Test
    void receiveInitialCards() {
        Players players = Players.of("무늬, 그니, 포비");
        Deck deck = Deck.create(new NoneStrategy());

        players.receiveInitialCards(deck);

        for (Player player : players.getPlayers()) {
            assertThat(player.countCards()).isEqualTo(2);
        }
    }
}