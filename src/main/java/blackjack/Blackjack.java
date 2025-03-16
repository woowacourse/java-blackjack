package blackjack;

import card.Card;
import card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import player.Dealer;
import player.Participant;
import player.Player;
import player.Players;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public Blackjack(List<String> names, Deck deck) {
        this.players = createPlayers(new Dealer(), createParticipants(names));
        this.deck = deck;
    }

    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public Map<String, List<Card>> openInitialCards() {
        return players.openInitialCards();
    }

    public void addCard(Player player) {
        player.drawOneCard(deck);
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public Map<String, Integer> getNameAndSumOfAllPlayers() {
        return players.mapToNameAndSum();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants();
    }

    private Players createPlayers(Player dealer, List<Player> participants) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(participants);
        return new Players(players);
    }

    private List<Player> createParticipants(List<String> names) {
        return names.stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }
}
