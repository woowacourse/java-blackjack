package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러")
public class DealerTest {

    private final ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();

    private Deck deck;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
    }

    @DisplayName("딜러는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given
        Card card = new Card(Rank.ACE, Suit.SPADE);

        //when
        dealer.draw();

        //then
        assertThat(dealer.getHandCards()).contains(card);
    }

    @DisplayName("딜러의 첫번째 카드를 공개한다.")
    @Test
    void showFirstCard() {
        //given
        Card card = new Card(Rank.ACE, Suit.SPADE);

        //when & then
        assertThat(dealer.showFirstCard()).isEqualTo(card);
    }
}
