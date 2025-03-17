package participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.participant.Dealer;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DeckGenerator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import strategy.TestDeckGenerateStrategy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DealerTest {

    @Test
    void 카드의_합이_16_초과면_뽑을수_없다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.CLOVER, CardValue.NINE)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        Dealer dealer = new Dealer(new ArrayList<>());
        dealer.addDraw(deck.drawCard());
        dealer.addDraw(deck.drawCard());
        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 카드의_합이_16_이하면_뽑을수_있다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.DIAMOND, CardValue.K)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        Dealer dealer = new Dealer(new ArrayList<>());
        assertThat(dealer.isDrawable()).isTrue();
    }
}
