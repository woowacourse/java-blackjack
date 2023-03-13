package domain;

import domain.card.Deck;
import domain.user.AllWinningAmountDto;
import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import java.util.List;

public final class BlackjackGame {

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public void initializeGame() {
        participants.drawInitialCardsEachParticipant(deck);
    }

    public boolean hitOrStayByDealer() {
        return this.participants.hitOrStayByDealer(this.deck);
    }

    public AllWinningAmountDto calculateAllWinningAmounts() {
        return this.participants.calculateWinningAmountOfAllPlayers();
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
