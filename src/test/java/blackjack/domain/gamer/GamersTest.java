package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Cards;
import blackjack.domain.card.deck.FixDeck;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerName;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    @Test
    @DisplayName("Gamers 클래스는 Gamer들을 입력받으면 정상적으로 생성된다.")
    void create_gamers() {
        Gamer aki = new Player(new PlayerName("aki"));
        Gamer alien = new Player(new PlayerName("alien"));
        List<Gamer> gamers = List.of(aki, alien);

        assertThatCode(() -> new Gamers(gamers)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("initCards 메서드를 통해 모든 플레이어들이 처음에 2장의 카드를 받는다.")
    void init_cards_gamers() {
        Gamer aki = new Player(new PlayerName("aki"));
        Gamers gamers = new Gamers(List.of(aki));
        gamers.initCards(new FixDeck());
        Cards akiCards = aki.getCards();
        List<Card> cards = akiCards.get();

        assertThat(cards.get(0)).isEqualTo(new Card(CardNumber.TEN, CardType.SPADE));
        assertThat(cards.get(1)).isEqualTo(new Card(CardNumber.TEN, CardType.SPADE));
    }

    @Test
    @DisplayName("게이머의 이름이 중복되면 에러를 출력한다.")
    void duplicate_name_error() {
        Gamer aki1 = new Player(new PlayerName("aki"));
        Gamer aki2 = new Player(new PlayerName("aki"));

        assertThatThrownBy(() -> new Gamers(List.of(aki1, aki2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게이머의 이름이 딜러면 에러를 출력한다.")
    void same_dealer_name_error() {
        Gamer dealerPlayer = new Player(new PlayerName("딜러"));

        assertThatThrownBy(() -> new Gamers(List.of(dealerPlayer)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
