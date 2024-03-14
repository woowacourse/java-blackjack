package blackjack.domain;

import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Shape.SPADE;
import static blackjack.domain.card.Value.KING;
import static blackjack.domain.card.Value.NINE;
import static blackjack.domain.card.Value.QUEEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("중복된 이름이 있으면 예외가 발생한다.")
    void duplicatedNamesTest() {
        List<String> names = List.of("loki", "pedro", "loki");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 존재합니다.");
    }

    @Test
    @DisplayName("플레이어들을 저장한다..")
    void getPlayersTest() {
        List<String> names = List.of("loki", "pedro", "jazz");

        Players players = new Players(names);
        assertThat(players.getPlayers()).hasSize(3);
    }

    @Test
    @DisplayName("플레이어들의 이름을 가져온다.")
    void getPlayerNamesTest() {
        List<String> names = List.of("loki", "pedro", "jazz");

        Players players = new Players(names);
        assertThat(players.getPlayerNames()).containsAll(names);
    }


    @Test
    @DisplayName("플레이어들이 모두 버스트인지 검사한다..")
    void playersAllBustTest() {
        List<String> names = List.of("player1", "player2");
        Players players = new Players(names);
        List<Player> playerGroup = players.getPlayers();

        List<Card> cards = new ArrayList<>(
                List.of(new Card(DIAMOND, KING), new Card(DIAMOND, QUEEN), new Card(DIAMOND, NINE),
                        new Card(SPADE, KING), new Card(SPADE, QUEEN), new Card(SPADE, NINE)));
        Deck deck = new Deck(cards);

        giveCardToPlayer(playerGroup.get(0), deck, 3);
        giveCardToPlayer(playerGroup.get(1), deck, 3);

        assertThat(players.isAllBusted()).isTrue();
    }

    private void giveCardToPlayer(Player player, Deck deck, int drawAmount) {
        for (int i = 0; i < drawAmount; i++) {
            player.hit(deck);
        }
    }
}
