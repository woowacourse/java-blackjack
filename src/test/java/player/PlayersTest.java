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

    @DisplayName("현재 플레이어의 인원수를 반환할 수 있다.")
    @Test
    void count() {
        Players players = new Players();
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        Player player3 = new Player(new Name("연어"));
        players.add(player1);
        players.add(player2);
        players.add(player3);

        assertThat(players.count()).isEqualTo(3);
    }
}
