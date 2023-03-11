package blackjack.player;

import static blackjack.Fixtures.BET_AMOUNT_10000;
import static blackjack.Fixtures.PLAYER_WITH_10000;
import static blackjack.Fixtures.PLAYER_WITH_20000;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Money;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.ShuffledCardsGenerator;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Player;
import blackjack.domain.participant.player.Players;
import java.util.Map;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("생성할 수 있다")
    @Test
    void create() {
        assertThatCode(() -> new Players()).doesNotThrowAnyException();
    }

    @DisplayName("플레이어들의 이름은 중복될 수 없다.")
    @Test
    void cannotHaveSameName() {
        Players players = new Players();
        Player rosie = new Player(new Name("로지"), BET_AMOUNT_10000);
        Player rosy = new Player(new Name("로지"), BET_AMOUNT_10000);
        Players newPlayers = players.add(rosie);

        assertThatThrownBy(() -> newPlayers.add(rosy))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 한 명을 추가할 수 있다.")
    void addPlayer() {
        //given
        Players players = new Players();
        Player player = new Player(new Name("로지"), BET_AMOUNT_10000);
        //when
        Players newPlayers = players.add(player);
        //then
        assertThat(newPlayers).extracting("players", InstanceOfAssertFactories.list(Player.class))
                .contains(player);
    }

    @DisplayName("플레이어들이 알아서 덱에서 카드를 받는다.")
    @Test
    void takeCardOnce() {
        //given
        Deck deck = new Deck(new ShuffledCardsGenerator());
        Players players = new Players();
        Player player = new Player(new Name("로지"), BET_AMOUNT_10000);
        Players newPlayers = players.add(player);
        //when
        newPlayers.hitFirstCards(deck);
        //then
        assertThat(player.showCards()).hasSize(2);
    }

    @Nested
    @DisplayName("최종 수익을 계산하는 기능")
    class CalculateEachPrize {
        @DisplayName("결과에 모든 플레이어가 포함되어있다.")
        @Test
        void containsAllPlayer() {
            Players players = new Players().add(PLAYER_WITH_10000).add(PLAYER_WITH_20000);
            Map<Player, Money> playerToPrize = players.calculateEachPrize(new Dealer());
            assertThat(playerToPrize.keySet()).contains(PLAYER_WITH_10000, PLAYER_WITH_20000);
        }
    }
}
