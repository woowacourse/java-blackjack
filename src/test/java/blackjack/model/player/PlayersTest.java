package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.model.card.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("Player들이 카드를 두장 뽑으면 새로운 Players를 반환한다.")
    @Test
    void drawcards_new_Players() {
        CardDeck deck = new CardDeck();
        Players players = Players.from(List.of("리버", "포키", "크리스"));

        Players otherPlayers = players.drawCardsBy(deck);

        assertThat(players).isNotEqualTo(otherPlayers);
    }
}