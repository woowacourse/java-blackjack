package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("덱")
public class DeckTest {

    static class TestShuffleStrategy implements ShuffleStrategy {

        @Override
        public void shuffle(List<?> list) {
        }
    }

    @DisplayName("원하는 순서로 생성에 성공한다.")
    @Test
    void shuffleCardInMyOrder() {
        //given
        ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();
        Deck deck = new Deck(shuffleStrategy);
        Card card = new Card(Rank.ACE, Suit.SPADE);

        //when & then
        assertThat(deck.getCards().get(0))
                .isEqualTo(card);
    }
}
