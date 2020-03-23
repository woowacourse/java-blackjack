package blackjack.domain.user;

import blackjack.domain.deck.Deck;
import blackjack.domain.user.exception.PlayersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {
    Map<String, String> playerProperties;

    @BeforeEach
    void setUp() {
        playerProperties = new HashMap<>();
    }

    @Test
    void of() {
        playerProperties.put("무늬", "1000");
        playerProperties.put("그니", "2000");
        playerProperties.put("포비", "3000");

        assertThat(Players.of(playerProperties)).isNotNull();
    }

    @Test
    void of_DealerName_ShouldThrowException() {
        playerProperties.put("딜러", "1000");
        playerProperties.put("그니", "2000");
        playerProperties.put("포비", "3000");

        assertThatThrownBy(() -> {
            Players.of(playerProperties);
        }).isInstanceOf(PlayersException.class)
                .hasMessage("플레이어의 이름은 딜러일 수 없습니다.");
    }

    @Test
    void receiveInitialCards() {
        playerProperties.put("무늬", "1000");
        playerProperties.put("그니", "2000");
        playerProperties.put("포비", "3000");
        Players players = Players.of(playerProperties);
        Deck deck = Deck.createWithShuffle();

        players.drawCardsAtFirst(deck);

        for (Player player : players.getPlayers()) {
            assertThat(player.getCards().size()).isEqualTo(2);
        }
    }
}