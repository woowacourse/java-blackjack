package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void handOneCard(String playerName) {
        Player player = participants.findPlayerBy(playerName);
        Card card = deck.draw();
        player.take(card);
    }

    public boolean canDraw(String playerName) {
        Player player = participants.findPlayerBy(playerName);
        return player.canDraw();
    }

    public Card openDealerFirstCard() {
        return participants.openDealerFirstCard();
    }

    public List<String> findNotBustPlayerNames() {
        return participants.findNotBustPlayerNames();
    }

    public List<Card> openPlayerCards(String playerName) {
        Player player = participants.findPlayerBy(playerName);
        return player.getCards();
    }

    public int hitOrStayForDealer() {
        Dealer dealer = participants.getDealer();
        int hitCount = 0;

        while (dealer.canDraw()) {
            Card card = deck.draw();
            dealer.take(card);
            hitCount++;
        }

        return hitCount;
    }

    public GameResult computeDealerGameResult() {
        return participants.openDealerGameResult();
    }

    public Map<String, GameResult> computePlayerGameResults() {
        return participants.openPlayerGameResults();
    }

    public PlayerWinResults computePlayerWinResults() {
        return participants.computePlayerWinResults();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }
}
