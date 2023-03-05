package domain;

import domain.user.Participants;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(List<String> nameValues) {
        this.participants = new Participants(nameValues);
        this.deck = new Deck();
    }

    public void initializeGame() {
        participants.drawInitialCardsEachParticipant(deck);
    }

    public Dealer getDealer() {
        return this.participants.getDealer();
    }

    public List<Player> getPlayers() {
        return this.participants.getPlayers();
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
}
