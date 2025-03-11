package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.player.Players;

import java.util.List;

public final class BlackjackGame {
    
    private final BlackjackDeck deck;
    private final List<PlayerBlackjackCardHand> playerHands;
    private final DealerBlackjackCardHand dealerHand;
    
    private BlackjackGame(
            final BlackjackDeck deck,
            final List<PlayerBlackjackCardHand> playerHands,
            final DealerBlackjackCardHand dealerHand
    ) {
        this.deck = deck;
        this.playerHands = playerHands;
        this.dealerHand = dealerHand;
    }
    
    public static BlackjackGame from(final List<String> playerNames) {
        final BlackjackDeck deck = new BlackjackDeck();
        return new BlackjackGame(
                deck,
                Players.from(playerNames).toBlackjackCardHand(deck),
                new DealerBlackjackCardHand(deck)
        );
    }
    
    public int startAddingAndGetAddedSize() {
        return dealerHand.startAddingAndGetAddedSize(deck);
    }
    
    public List<PlayerBlackjackCardHand> getPlayerHands() {
        return playerHands;
    }
    
    public DealerBlackjackCardHand getDealerHand() {
        return dealerHand;
    }
    
    public BlackjackDeck getDeck() {
        return deck;
    }
    
    public Card draw() {
        return deck.draw();
    }
}
