import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.BlackjackDeck;
import domain.BlackjackDeckGenerator;
import domain.CardValue;
import domain.Suit;
import domain.TrumpCard;
import domain.strategy.BlackjackDrawStrategy;
import except.BlackJackException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import strategy.TestDrawStrategy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DeckTest {

    @Test
    void 트럼프_카드를_덱에서_뽑을_수_있다() {
        Deque<TrumpCard> trumpCards = new LinkedList<>(List.of(new TrumpCard(Suit.CLOVER, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.EIGHT)));
        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new TestDrawStrategy(trumpCards));

        assertThat(deck.drawCard()).isEqualTo(trumpCards.getFirst());
    }

    @Test
    void 덱에서_카드가_없으면_예외가_발생한다() {
        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new BlackjackDrawStrategy());
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertThatThrownBy(deck::drawCard).isInstanceOf(BlackJackException.class);
    }
}
