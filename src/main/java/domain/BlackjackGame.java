package domain;

import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Map;

public final class BlackjackGame {
    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame from(List<String> nameValues) {
        return new BlackjackGame(Participants.from(nameValues), new ShuffledDeck());
    }

    public void initializeGame() {
        participants.drawInitialCardsEachParticipant(deck);
    }

    public boolean hitOrStayByDealer() {
        return this.participants.hitOrStayByDealer(this.deck);
    }

    public Map<Player, Result> calculateAllResults() {
        return this.participants.calculateAllResults();
    }

    public void hitBy(Player player) {
        player.addCard(this.deck.draw());
    }

    public Dealer getDealer() {
        return this.participants.getDealer();
    }

    public List<Player> getPlayers() {
        return this.participants.getPlayers();
    }
}
