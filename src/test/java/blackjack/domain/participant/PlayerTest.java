package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어")
public class PlayerTest {

    private final ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();

    @DisplayName("플레이어에게 카드를 더 뽑을지 물어본다.")
    @Test
    void canReceiveCard() {
        //given
        Deck deck = new Deck(shuffleStrategy);

        //when
        Player canAddCardPlayer = new Player("choco");
        canAddCardPlayer.draw(deck);

        Player cantAddCardPlayer = new Player("clover");
        IntStream.range(0, 6)
                .forEach(i -> cantAddCardPlayer.draw(deck));

        //then
        assertThat(canAddCardPlayer.canReceiveCard()).isTrue();
        assertThat(cantAddCardPlayer.canReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given
        Deck deck = new Deck(shuffleStrategy);
        Player player = new Player("choco");
        Card card = new Card(Rank.ACE, Suit.SPADE);

        //when
        player.draw(deck);

        //then
        assertThat(player.getHandCards()).contains(card);
    }
}
