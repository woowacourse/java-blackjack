package domain.result;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultsTest {
    private Deck deck;
    private Dealer dealer;
    private Players players;
    private GameResults gameResults;

    @BeforeEach
    void setUp() {
        Stack<Card> cards = new Stack<>();
        cards.push(new Card(Symbol.TWO, Type.CLOVER));
        cards.push(new Card(Symbol.THREE, Type.CLOVER));
        cards.push(new Card(Symbol.FOUR, Type.CLOVER));
        cards.push(new Card(Symbol.FIVE, Type.CLOVER));
        cards.push(new Card(Symbol.SIX, Type.CLOVER));
        cards.push(new Card(Symbol.SEVEN, Type.CLOVER));
        cards.push(new Card(Symbol.EIGHT, Type.CLOVER));
        cards.push(new Card(Symbol.NINE, Type.CLOVER));
        deck = new Deck(cards);
        dealer = new Dealer(deck.dealInitCards());
        players = Players.valueOf(deck, Arrays.asList("a", "b", "c"));
        gameResults = new GameResults(dealer, players);
    }

    @Test
    @DisplayName("게임 결과 생성 테스트")
    void construct() {
        assertThat(new GameResults(dealer, players)).isNotNull();
    }
}
