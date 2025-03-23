package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final Dealer dealer;
    private final Players players;

    public Game(List<String> playerNames, Deck deck) {
        dealer = new Dealer(deck, deck.getInitialDeal());
        this.players = new Players(playerNames, dealer);
    }

    public void playerHit(String playerName) {
        Card card = dealer.pickCard();
        players.hit(playerName, card);
    }

    public void dealerHit() {
        Card card = dealer.pickCard();
        dealer.hit(card);
    }

    public void bet(String name, int betAmount) {
        players.bet(name, betAmount);
    }

    public boolean canPlayerHit(String name) {
        return players.canHit(name);
    }

    public boolean canDealerHit() {
        return dealer.canHit();
    }

    public double getDealerProfit() {
        double playersProfit = players.getPlayersProfit(dealer);
        return dealer.calculateProfit(playersProfit);
    }

    public List<Card> getCardsOf(String name) {
        return players.getCardsOf(name);
    }

    public List<GameParticipant> getParticipants() {
        return Stream.concat(Stream.of(dealer), players.getPlayers().stream())
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
