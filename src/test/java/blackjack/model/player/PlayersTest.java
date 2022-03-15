package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @DisplayName("참가자 이름중 중복되는 이름이 있으면 예외를 발생한다.")
    @Test
    void construct_duplicate_name() {
        List<String> names = List.of("리버", "포키", "크리스", "리버");

        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
    }

}