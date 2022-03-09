package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("Players 클래스는 Player들을 입력받으면 정상적으로 생성된다.")
    void create_players() {
        Player aki = new Player(new Name("aki"));
        Player alien = new Player(new Name("alien"));
        List<Player> players = List.of(aki, alien);

        assertThatCode(() -> new Players(players)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("initCards 메서드를 통해 모든 플레이어들이 처음에 2장의 카드를 받는다.")
    void init_cards_players() {
        Player aki = new Player(new Name("aki"));
        Players players = new Players(List.of(aki));
        players.initCards(new FixDeck());
        Cards akiCards = aki.getCards();
        List<Card> cards = akiCards.get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
    }
}
