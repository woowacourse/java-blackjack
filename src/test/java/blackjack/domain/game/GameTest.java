package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.Deck;
import blackjack.domain.game.Game;
import blackjack.domain.gameplayer.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameTest {
    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewGame() {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName" + i), Betting.of(2000)));
        }

        Stack<Card> cards = new Stack<>();

        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardSymbol cardSymbol : CardSymbol.values()) {
                cards.add(new Card(cardNumber, cardSymbol));
            }
        }

        Deck deck = new Deck(cards);

        // When, then
        assertDoesNotThrow(() -> new Game(deck, new Dealer(), new Players(players)));
    }
}
