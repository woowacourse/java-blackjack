import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackDeck;
import domain.BlackjackDeckGenerator;
import domain.CardValue;
import domain.Dealer;
import domain.Suit;
import domain.TrumpCard;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import strategy.TestDrawStrategy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DealerTest {

    @Test
    void 카드의_합이_16_초과면_뽑을수_없다() {
        Deque<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.CLOVER, CardValue.NINE)));
        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new TestDrawStrategy(trumpCards));

        Dealer dealer = new Dealer();
        dealer.addDraw(deck.drawCard());
        dealer.addDraw(deck.drawCard());
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 카드의_합이_16_이하면_뽑을수_있다() {
        Deque<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.DIAMOND, CardValue.K)));
        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new TestDrawStrategy(trumpCards));

        Dealer dealer = new Dealer();
        assertThat(dealer.isDrawable()).isTrue();
    }
}
