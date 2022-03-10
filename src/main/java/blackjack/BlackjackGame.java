package blackjack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public BlackjackGame(List<String> names) {
        this.deck = Deck.create();
        this.dealer = new Dealer(new HoldCards(deck.draw(), deck.draw()));
        this.players = toPlayers(names);
    }

    private List<Player> toPlayers(List<String> names) {
        return names.stream()
                .map(name -> new Player(name, new HoldCards(deck.draw(), deck.draw())))
                .collect(Collectors.toList());
    }

    public void receiveOneMoreCard(Player player) {
        player.putCard(deck.draw());
    }
    
    public boolean isDealerReceiveOneMoreCard() {
        if (dealer.shouldHaveMoreCard()) {
            dealer.putCard(deck.draw());
            return true;
        }
        return false;
    }
    
    public Map<Outcome, List<Player>> getGameResult() {
        return players.stream()
                .collect(Collectors.groupingBy(player -> player.isWin(dealer.countCards())));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
