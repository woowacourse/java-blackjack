package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.blackjackgame.PlayersBlackjackResults;
import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.generator.CardGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {

    @DisplayName("크기를 반환한다.")
    @Test
    void getSize() {
        List<Player> given = getPlayers();

        Players players = new Players(given);
        int result = players.getSize();

        assertThat(result).isEqualTo(given.size());
    }

    @DisplayName("적절한 인덱싱의 플레이어를 반환한다.")
    @Test
    void getPlayer() {
        Player player = new Player(new Name("daon1"), new Betting(2));
        List<Player> given = List.of(
                new Player(new Name("daon1"), new Betting(1)),
                player,
                new Player(new Name("daon1"), new Betting(3))
        );

        Players players = new Players(given);
        Player result = players.getPlayer(given.indexOf(player));

        assertThat(result).isEqualTo(player);
    }

    @DisplayName("유효하지 않는 인덱스로 접근하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 5, 1990})
    void getPlayerInvalidIndex(int given) {
        Player player = new Player(new Name("daon1"), new Betting(2));
        List<Player> givenPlayers = List.of(
                new Player(new Name("daon1"), new Betting(1)),
                player,
                new Player(new Name("daon1"), new Betting(3))
        );

        Players players = new Players(givenPlayers);
        assertThatThrownBy(() -> players.getPlayer(given))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당 인덱스의 플레이어가 카드를 더 뽑을 수 있는지 반환한다.")
    @Test
    void canPlayerGetMoreCard() {
        Player daon1 = new Player(new Name("daon1"), new Betting(1));
        daon1.addCards(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.KING, CardShape.SPADE)
        ));
        List<Player> given = List.of(
                daon1,
                new Player(new Name("daon2"), new Betting(100)),
                new Player(new Name("daon3"), new Betting(2000))
        );

        Players players = new Players(given);

        assertAll(
                () -> assertThat(players.canPlayerGetMoreCard(0)).isEqualTo(false),
                () -> assertThat(players.canPlayerGetMoreCard(1)).isEqualTo(true),
                () -> assertThat(players.canPlayerGetMoreCard(2)).isEqualTo(true)
        );
    }

    @DisplayName("플레이어를 업데이트한다.")
    @Test
    void updatePlayer() {
        int index = 0;
        CardGenerator cardGenerator = new CardGenerator((range) -> 0);
        Players players = new Players(getPlayers());

        players.updatePlayer(index, cardGenerator);
        Player player = players.getPlayer(index);

        assertThat(player.getCards().getCards()).hasSize(1);
    }

    @DisplayName("모든 플레이어의 결과를 반영한다.")
    @Test
    void calculatePlayersResults() {
        Players players = new Players(getPlayers());
        Dealer dealer = new Dealer();

        PlayersBlackjackResults playersBlackjackResults = players.calculatePlayersResults(dealer);

        assertThat(playersBlackjackResults.getResults()).hasSize(players.getSize());
    }

    private List<Player> getPlayers() {
        return List.of(
                new Player(new Name("daon1"), new Betting(1)),
                new Player(new Name("daon1"), new Betting(2)),
                new Player(new Name("daon1"), new Betting(3))
        );
    }
}
