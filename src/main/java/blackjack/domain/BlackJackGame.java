package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(DeckGenerator deckGenerator, List<String> playerNames) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(playerNames);
    }

    public void handInitialCards() {
        int neededNumberOfCards = participants.getNeededNumberOfCards();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < neededNumberOfCards; i++) {
            cards.add(deck.draw());
        }
        participants.handInitialCards(new Deck(cards));
    }

    public void handOneCard(Player player) {
        Card card = deck.draw();
        player.take(card);
    }

    public Card openDealerFirstCard() {
        return participants.openDealerFirstCard();
    }

    public List<Player> findCanHitPlayers() {
        return participants.findCanHitPlayer();
    }

    public int hitOrStayForDealer() {
        Dealer dealer = participants.getDealer();
        int hitCount = 0;

        while (dealer.canHit()) {
            Card card = deck.draw();
            dealer.take(card);
            hitCount++;
        }

        return hitCount;
    }

    public GameResult computeDealerGameResult() {
        return participants.openDealerGameResult();
    }

    public GameResult computePlayerGameResult(String playerName) {
        return participants.openPlayerGameResult(playerName);
    }

    public PlayerWinResults computePlayerWinResults() {
        return participants.computePlayerWinResults();
    }

    public List<String> getPlayersName() {
        return participants.getPlayerNames();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
