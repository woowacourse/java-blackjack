package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 생성_시_null_players_예외발생() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("players는 null이 들어올 수 없습니다.");
    }

    @Test
    void 생성_시_중복이름_예외발생() {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        final List<String> names = Arrays.asList("name", "name");

        assertThatThrownBy(() -> Players.createPlayers(names, text -> 1000, cardDeck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 간에 중복이 있으면 안됩니다.");
    }

    @Test
    void 생성_시_유저가_2명미만인_경우_예외발생() {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        final List<String> names = List.of("name");

        assertThatThrownBy(() -> Players.createPlayers(names, text -> 1000, cardDeck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 2명 이상 8명 이하만 들어올 수 있습니다.");
    }

    @Test
    void 모든_턴이_종료될_때_hit_하는_경우_예외발생() {
        final Cards cards = new Cards(List.of(new Card(SPADES, KING), new Card(SPADES, QUEEN)));
        final Player player = new Player(new Name("name"), 1000, cards);
        final Players players = new Players(List.of(player));
        players.hitCurrentTurnPlayer(new Card(SPADES, JACK));

        assertThatThrownBy(() -> players.hitCurrentTurnPlayer(new Card(SPADES, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }

    @Test
    void 모든_턴이_종료될_때_stay_하는_경우_예외발생() {
        final Cards cards = new Cards(List.of(new Card(SPADES, KING), new Card(SPADES, QUEEN)));
        final Player player = new Player(new Name("name"), 1000, cards);
        final Players players = new Players(List.of(player));
        players.stayCurrentTurnPlayer();

        assertThatThrownBy(() -> players.stayCurrentTurnPlayer())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }
}
