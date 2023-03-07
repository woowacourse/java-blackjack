package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름들을 반환한다.")
    void getPlayerNames() {
        List<Card> cards1 = List.of(new Card(Suit.CLOVER, Denomination.SIX), new Card(Suit.SPADE, Denomination.TEN));
        List<Card> cards2 = List.of(new Card(Suit.CLOVER, Denomination.NINE), new Card(Suit.SPADE, Denomination.SEVEN));
        List<Player> gamePlayers = List.of(new Player(new Name("seongha"), new HandCards(cards1)), new Player(new Name("dino"), new HandCards(cards2)));

        Players players = new Players(gamePlayers);

        Assertions.assertThat(players.getPlayerNames().size()).isEqualTo(2);
        Assertions.assertThat(players.getPlayerNames()).containsExactly("seongha", "dino");
    }
}
