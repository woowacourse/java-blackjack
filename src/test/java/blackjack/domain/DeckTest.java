package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.TrumpCardFixture.aceSpadeTrumpCard;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("덱")
public class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();
        deck = new Deck(shuffleStrategy);
    }

    @DisplayName("카드 뭉치의 첫 번째 카드를 반환한다.")
    @Test
    void draw() {
        //given
        Card cardAceSpade = aceSpadeTrumpCard();

        //when & then
        assertThat(deck.draw()).isEqualTo(cardAceSpade);
    }
}
