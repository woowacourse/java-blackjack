package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Dealer dealer;
    private final Players players;

    public Game(List<String> playerNames, Deck deck) {
        dealer = new Dealer(deck, deck.getInitialDeal());
        this.players = new Players(playerNames, dealer.pickInitialDeal());
    }

    public void playerHit(String playerName) {
        Card card = dealer.pickCard();
        players.findPlayerByName(playerName).hit(card);
    }

    public void dealerHit() {
        Card card = dealer.pickCard();
        dealer.hit(card);
    }

    public boolean doesDealerNeedCard() {
        return dealer.doesNeedCard();
    }

    public boolean canHit(String playerName) {
        return players.findPlayerByName(playerName).canHit();
    }

    public List<GameParticipant> getParticipants() {
        List<GameParticipant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<Card> getPlayerCards(String playerName) {
        return players.findPlayerByName(playerName).getCards();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
