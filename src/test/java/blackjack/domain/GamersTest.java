package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    @Test
    @DisplayName("Gamers 클래스는 Gamer들을 입력받으면 정상적으로 생성된다.")
    void create_gamers() {
        Gamer aki = new Player(new Name("aki"));
        Gamer alien = new Player(new Name("alien"));
        List<Gamer> gamers = List.of(aki, alien);

        assertThatCode(() -> new Gamers(gamers)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("initCards 메서드를 통해 모든 플레이어들이 처음에 2장의 카드를 받는다.")
    void init_cards_gamers() {
        Gamer aki = new Player(new Name("aki"));
        Gamers gamers = new Gamers(List.of(aki));
        gamers.initCards(new FixDeck());
        Cards akiCards = aki.getCards();
        List<Card> cards = akiCards.get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
    }
}
