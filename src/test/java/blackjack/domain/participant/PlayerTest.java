package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어")
public class PlayerTest {

    private final ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();
    private Deck deck;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
    }

    @DisplayName("플레이어에게 카드를 더 뽑을지 물어본다.")
    @Test
    void canReceiveCard() {
        //given
        Player canAddCardPlayer = new Player("choco", dealer);
        Player cantAddCardPlayer = new Player("clover", dealer);

        //when
        canAddCardPlayer.draw(dealer);

        IntStream.range(0, 6)
                .forEach(i -> cantAddCardPlayer.draw(dealer));

        //then
        assertThat(canAddCardPlayer.canReceiveCard()).isTrue();
        assertThat(cantAddCardPlayer.canReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given
        Player player = new Player("choco", dealer);
        Card card = new Card(Rank.THREE, Suit.SPADE);

        //when
        player.draw(dealer);

        //then
        assertThat(player.getHandCards()).contains(card);
    }
}
