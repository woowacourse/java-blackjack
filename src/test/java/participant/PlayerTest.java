package participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.participant.Player;
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
public class PlayerTest {

    @Test
    void 카드의_합이_21_이하면_뽑을_수_있다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        Player player = new Player("루키", new ArrayList<>());
        player.addDraw(deck.drawCard());
        player.addDraw(deck.drawCard());
        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    void 카드의_합이_21_초과면_버스트다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.DIAMOND, CardValue.K)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        Player player = new Player("루키", new ArrayList<>());
        player.addDraw(deck.drawCard());
        player.addDraw(deck.drawCard());
        player.addDraw(deck.drawCard());
        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 에이스가_포함된_경우에_에이스를_최적의_값으로_계산한다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.CLOVER, CardValue.A), new TrumpCard(Suit.SPADE, CardValue.TEN),
                        new TrumpCard(Suit.DIAMOND, CardValue.TEN)));

        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        Player player = new Player("루키", new ArrayList<>());
        player.addDraw(deck.drawCard());
        player.addDraw(deck.drawCard());
        player.addDraw(deck.drawCard());

        assertThat(player.calculateCardSum()).isEqualTo(21);
    }

    @Test
    void 버스트가_되지_않으면_에이스는_11로_처리한다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.CLOVER, CardValue.A), new TrumpCard(Suit.DIAMOND, CardValue.TEN)));

        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        Player player = new Player("루키", new ArrayList<>());
        player.addDraw(deck.drawCard());
        player.addDraw(deck.drawCard());

        assertThat(player.calculateCardSum()).isEqualTo(21);
    }
}
