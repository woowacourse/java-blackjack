package blackjack.domain;

import java.util.List;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(DeckGenerator deckGenerator, List<String> playerNames) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(playerNames);
    }

    public void handInitialCards() {
        participants.handInitialCards(deck);
    }

    public void handOneCard(Player player) {
        Card card = deck.draw();
        player.take(card);
    }

    public Card openDealerFirstCard() {
        return participants.openDealerFirstCard();
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

    public GameResult getGameResult() {
        return participants.getGameResult();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<String> getPlayersName() {
        return participants.getPlayerNames();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
