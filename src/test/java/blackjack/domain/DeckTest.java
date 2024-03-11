package blackjack.domain;

import blackjack.domain.card.TrumpCard;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.TrumpCardFixture.aceSpadeTrumpCard;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("덱")
public class DeckTest {

    @DisplayName("원하는 순서로 생성에 성공한다.")
    @Test
    void shuffleCardInMyOrder() {
        // given
        ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();
        Deck deck = new Deck(shuffleStrategy);
        TrumpCard trumpCardAceSpade = aceSpadeTrumpCard();

        // when & then
        assertThat(deck.getCards().poll()).isEqualTo(trumpCardAceSpade);
    }
}
