package blackjack.domain.result;

import blackjack.domain.playing.card.Card;
import blackjack.domain.playing.card.Symbol;
import blackjack.domain.playing.card.Type;
import blackjack.domain.playing.deck.Deck;
import blackjack.domain.playing.user.Dealer;
import blackjack.domain.playing.user.Player;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultTest {
    private Dealer dealer;
    private Player player;

    @Test
    void of() {
        Deck deckForDealer = createDeckForDealer();
        drawDealerCards(deckForDealer);

        Deck deckForPlayers = createDeckForPlayer();
        drawPlayerCards(deckForPlayers);

        assertThat(PlayerResult.of(player, dealer)).isEqualTo(ResultType.WIN);
    }

    private Deck createDeckForDealer() {
        Stack<Card> cardsForDealer = new Stack<>();

        cardsForDealer.push(new Card(Symbol.TWO, Type.SPADE));
        cardsForDealer.push(new Card(Symbol.THREE, Type.SPADE));

        return new Deck(cardsForDealer);
    }

    private void drawDealerCards(Deck deckForDealer) {
        dealer = Dealer.create();
        dealer.drawCardsInTurn(deckForDealer);
        dealer.drawCardsInTurn(deckForDealer);
    }

    private Deck createDeckForPlayer() {
        Stack<Card> cardsForPlayer = new Stack<>();

        cardsForPlayer.push(new Card(Symbol.ACE, Type.SPADE));
        cardsForPlayer.push(new Card(Symbol.QUEEN, Type.SPADE));

        return new Deck(cardsForPlayer);
    }

    private void drawPlayerCards(Deck deckForPlayer) {
        player = Player.of("무늬");
        player.drawCardsInTurn(deckForPlayer);
        player.drawCardsInTurn(deckForPlayer);
    }
}