package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
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
        dealer.drawCard(deckForDealer);
        dealer.drawCard(deckForDealer);
    }

    private Deck createDeckForPlayer() {
        Stack<Card> cardsForPlayer = new Stack<>();

        cardsForPlayer.push(new Card(Symbol.ACE, Type.SPADE));
        cardsForPlayer.push(new Card(Symbol.QUEEN, Type.SPADE));

        return new Deck(cardsForPlayer);
    }

    private void drawPlayerCards(Deck deckForPlayer) {
        player = Player.of("무늬");
        player.drawCard(deckForPlayer);
        player.drawCard(deckForPlayer);
    }
}