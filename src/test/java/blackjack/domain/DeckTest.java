package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.strategy.shuffle.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.TrumpCardFixture.aceSpadeTrumpCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("카드 뭉치에 반환할 카드가 없으면 예외가 발생한다.")
    @Test
    void drawException() {
        //given
        for (int i = 0; i < Suit.values().length * Rank.values().length; i++) {
            deck.draw();
        }

        //when & then
        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalStateException.class);
    }
}
