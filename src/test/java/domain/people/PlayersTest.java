package domain.people;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Deck;

class PlayersTest {

    private Players players;
    private Deck deck;

    @BeforeEach
    void setUp() {
        players = Players.from(List.of("leo", "reo", "reoleo"));
        deck = Deck.from((orderedDeck) -> orderedDeck);
    }

    @Test
    @DisplayName("참가자들의 이름에 문제가 없으면 참가자들을 정상적으로 생성한다.")
    void participantsTest() {
        assertThatCode(() -> Players.from(List.of("leo", "reo", "reoleo"))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자들이 카드를 한 장씩 받는다.")
    void participantsDealTest() {
        for (Player player : players.getPlayers()) {
            player.receiveCard(deck.draw());
        }
        List<Player> players = this.players.getPlayers();
        for (Player player : players) {
            assertThat(player.fetchHand().size()).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("플레이어(딜러 제외)의 목록을 반환한다.")
    void findPlayersTest() {
        assertThat(players.getPlayers().size()).isEqualTo(3);
    }

}
