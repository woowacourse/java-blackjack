package blackjack.domain;

import static blackjack.fixture.TrumpCardFixture.aceSpadeTrumpCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.TrumpCard;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("덱")
public class DeckTest {

    @DisplayName("원하는 순서로 생성에 성공한다.")
    @Test
    void shuffleCardInMyOrder() {
        //given
        ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();
        Deck deck = new Deck(shuffleStrategy);
        TrumpCard trumpCardAceSpade = aceSpadeTrumpCard();

        //when & then
        assertThat(deck.getCards().get(0))
                .isEqualTo(trumpCardAceSpade);
    }
}
