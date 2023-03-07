package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.Deck;
import blackjack.domain.gameplayer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    private Deck deck;
    private GamePlayer gamePlayer;

    @BeforeEach
    void init() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            players.add(new Player(new Name("newName")));
        }

        gamePlayer = new GamePlayer(new Players(players), new Dealer());

        Stack<Card> cards = new Stack<>();

        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardSymbol cardSymbol : CardSymbol.values()) {
                cards.add(new Card(cardNumber, cardSymbol));
            }
        }

        deck = new Deck(cards);
    }

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewGame() {
        assertDoesNotThrow(() -> new Game(deck, gamePlayer));
    }

}
