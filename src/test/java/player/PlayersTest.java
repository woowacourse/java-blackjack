package player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;

class PlayersTest {
    @DisplayName("생성할 수 있다")
    @Test
    void create() {
        assertThatCode(() -> new Players()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 한 명을 추가할 수 있다.")
    void addPlayer() {
        Players players = new Players();
        Player player = new Player(new Name("로지"));
        players.add(player);
    }

    @DisplayName("인덱스에 해당하는 플레이어가 카드를 받을 수 있다.")
    @Test
    void takeCard() {
        Players players = new Players();
        Player player = new Player(new Name("폴로"));
        players.add(player);
        Card card = new Card(CardNumber.ACE, Pattern.CLOVER);

        players.takeCard(0, card);

        assertThat(player.showCards()).contains(card);
    }
}
