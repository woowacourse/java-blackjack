import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackDeckGenerator;
import domain.CardValue;
import domain.Deck;
import domain.Suit;
import domain.TrumpCard;
import domain.strategy.TestDrawStrategy;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DeckTest {

    @Test
    void 트럼프_카드를_덱에서_뽑을_수_있다() {
        List<TrumpCard> trumpCards = new ArrayList<>(List.of(new TrumpCard(Suit.CLOVER, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.EIGHT)));
        Deck deck = new Deck(new BlackjackDeckGenerator(), new TestDrawStrategy(trumpCards));
        assertThat(deck.drawCard())
                .isEqualTo(trumpCards.get(0));
    }
}
