package domain.user;

import static domain.card.Number.ACE;
import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.FOUR;
import static domain.card.Number.SEVEN;
import static domain.card.Number.TEN;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static view.Command.NO;
import static view.Command.YES;

import domain.Deck;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("커맨드가 YES 이면 덱에서 카드를 한장 받는다.")
    void receiveCardTest() {
        Player player = new Player(new Name("aa"), new Card(SPADE, TEN), new Card(SPADE, EIGHT));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> YES, deck);

        assertThat(player.getAllCards()).contains(card);
    }

    @Test
    @DisplayName("커맨드가 NO이면 카드를 받지 않는다.")
    void notReceiveCardTest() {
        Player player = new Player(new Name("aa"), new Card(SPADE, TEN), new Card(SPADE, EIGHT));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> NO, deck);

        assertThat(player.getAllCards()).doesNotContain(card);
    }

    @Test
    @DisplayName("블랙잭이면 커맨드가 YES 여도 받지 않는다.")
    void receiveCardWhenBlackJackTest() {
        Player player = new Player(new Name("aa"), new Card(SPADE, TEN), new Card(SPADE, ACE));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> YES, deck);

        assertThat(player.getAllCards()).doesNotContain(card);
    }

    @Test
    @DisplayName("카드의 합이 21 이상이면 카드를 더 받지 않는다.")
    void receiveCardWhenSumOver21() {
        Player player = new Player(new Name("aa"),
                new Card(SPADE, TEN), new Card(SPADE, FOUR), new Card(SPADE, SEVEN));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> YES, deck);

        assertThat(player.getAllCards()).doesNotContain(card);
    }
}
