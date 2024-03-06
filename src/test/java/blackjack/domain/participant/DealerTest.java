package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러")
public class DealerTest {

    @DisplayName("딜러는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given
        ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();

        Deck deck = new Deck(shuffleStrategy);
        Dealer dealer = new Dealer("딜러");
        Card card = new Card(Rank.ACE, Suit.SPADE);

        //when
        dealer.draw(deck);

        //then
        assertThat(dealer.getHandCards()).contains(card);
    }
}
