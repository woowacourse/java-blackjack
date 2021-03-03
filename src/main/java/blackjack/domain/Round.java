package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Round {
    private final List<Card> shuffledCards;
    private final Dealer dealer;
    private final List<Player> players;

    public Round(List<Card> cards, Dealer dealer, List<Player> players) {
        this.shuffledCards = new ArrayList<>(cards);
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public List<Card> makeTwoCards() {
        return IntStream.range(0, 2)
                .mapToObj(count -> shuffledCards.remove(0))
                .collect(Collectors.toList());
    }

    public void initialize() {
        dealer.addFirstCards(makeTwoCards());
        players.forEach(player -> player.addFirstCards(makeTwoCards()));
    }

    public Map<String, List<Card>> initializeStatus() {
        Map<String, List<Card>> status = new LinkedHashMap<>();
        status.put(dealer.getName(), dealer.getCards());
        players.forEach(player -> status.put(player.getName(), player.getCards()));
        return status;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
