package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.dto.CardResponse;
import java.util.List;
import java.util.Map;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void distributeInitialCards(final Deck deck) {
        players.distributeInitialCards(deck);
        dealer.drawInitialCard(deck.popCard(), deck.popCard());
    }

    public Player findPlayerByName(final String playerName) {
        return players.findPlayerByName(playerName);
    }

    public boolean isPlayerDrawable(final String playerName) {
        return players.isDrawable(playerName);
    }

    public void drawPlayerCard(final String playerName, final Card card) {
        players.draw(playerName, card);
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void drawDealerCard(final Card card) {
        dealer.drawCard(card);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Map<String, Integer> calculatePlayersScore() {
        return players.calculatePlayersScore();
    }

    public Map<String, List<CardResponse>> getPlayersCards() {
        return players.getPlayersCards();
    }
}
