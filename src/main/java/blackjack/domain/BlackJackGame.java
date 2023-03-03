package blackjack.domain;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(DeckGenerator deckGenerator, List<String> playerNames) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(playerNames);
    }

    public void handOut() {
        participants.handOut(deck);
    }

    public boolean handOneCard(String playerName) {
        Player player = participants.findPlayerBy(playerName);
        List<Card> card = deck.draw(1);
        player.take(card.get(0));
        return participants.isAvailable(player);
    }

    public Map<String, List<Card>> openPlayersCards() {
        return participants.openPlayerCards();
    }

    public Card openDealerFirstCard() {
        return participants.openDealerFirstCard();
    }

    public List<String> findAvailablePlayerNames() {
        return participants.findAvailablePlayerNames();
    }

    public List<Card> openPlayerCards(String playerName) {
        Player player = participants.findPlayerBy(playerName);
        return player.getCards();
    }
}
