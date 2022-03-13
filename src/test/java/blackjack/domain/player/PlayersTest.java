package blackjack.domain.player;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.FixDeck;
import blackjack.domain.card.Type;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("Players 클래스는 Gamer들을 입력받으면 정상적으로 생성된다.")
    void create_players() {
        Player aki = new Participant(new Name("aki"));
        Player alien = new Participant(new Name("alien"));
        List<Player> players = List.of(aki, alien);

        assertThatCode(() -> new Players(players)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("initCards 메서드를 통해 모든 플레이어들이 처음에 2장의 카드를 받는다.")
    void init_cards_players() {
        Player aki = new Participant(new Name("aki"));
        Players players = new Players(List.of(aki));
        players.dealCards(new FixDeck());
        Cards akiCards = aki.getCards();
        List<Card> cards = akiCards.get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
    }

    @Test
    @DisplayName("Player의 이름이 중복되면 에러를 출력한다.")
    void duplicate_name_error() {
        Player aki1 = new Participant(new Name("aki"));
        Player aki2 = new Participant(new Name("aki"));

        assertThatThrownBy(() -> new Players(List.of(aki1, aki2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Player의 이름이 딜러면 에러를 출력한다.")
    void same_dealer_name_error() {
        Player player = new Participant(new Name("딜러"));

        assertThatThrownBy(() -> new Players(List.of(player)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
