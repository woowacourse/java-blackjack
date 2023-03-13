package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Constants;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름들을 반환한다.")
    void getPlayerNames() {
        List<Card> cards1 = List.of(new Card(Suit.CLOVER, Denomination.SIX), new Card(Suit.SPADE, Denomination.TEN));
        List<Card> cards2 = List.of(new Card(Suit.CLOVER, Denomination.NINE), new Card(Suit.SPADE, Denomination.SEVEN));
        List<Player> gamePlayers = List.of(new Player(new Name("seongha"), new Hand(cards1)), new Player(new Name("dino"), new Hand(cards2)));

        Players players = new Players(gamePlayers);

        Assertions.assertThat(players.getPlayerNames().size()).isEqualTo(2);
        Assertions.assertThat(players.getPlayerNames()).containsExactly("seongha", "dino");
    }

    @Test
    @DisplayName("플레이어의 이름으로 플레이어를 찾아서 반환한다.")
    void findPlayerByPlayerName() {
        List<Card> cards1 = List.of(new Card(Suit.CLOVER, Denomination.SIX), new Card(Suit.SPADE, Denomination.TEN));
        List<Card> cards2 = List.of(new Card(Suit.CLOVER, Denomination.NINE), new Card(Suit.SPADE, Denomination.SEVEN));
        Player player1 = new Player(new Name("seongha"), new Hand(cards1));
        Player player2 = new Player(new Name("dino"), new Hand(cards2));
        List<Player> gamePlayers = List.of(player1, player2);

        Players players = new Players(gamePlayers);

        Assertions.assertThat(players.findPlayerByPlayerName("seongha")).isEqualTo(player1);
        Assertions.assertThat(players.findPlayerByPlayerName("dino")).isEqualTo(player2);
    }

    @Test
    @DisplayName("플레이어의 이름으로 플레이어를 찾을 때 해당하는 플레이어가 없으면 예외를 던진다.")
    void findPlayerByPlayerNameNotFoundPlayer() {
        List<Card> cards1 = List.of(new Card(Suit.CLOVER, Denomination.SIX), new Card(Suit.SPADE, Denomination.TEN));
        List<Card> cards2 = List.of(new Card(Suit.CLOVER, Denomination.NINE), new Card(Suit.SPADE, Denomination.SEVEN));
        Player player1 = new Player(new Name("seongha"), new Hand(cards1));
        Player player2 = new Player(new Name("dino"), new Hand(cards2));
        List<Player> gamePlayers = List.of(player1, player2);

        Players players = new Players(gamePlayers);

        Assertions.assertThatThrownBy(() -> players.findPlayerByPlayerName("abcd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.ERROR_PREFIX + "해당 플레이어 이름과 일치하는 플레이어가 없습니다.");
    }
}
