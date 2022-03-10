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

    public Player receiveOneMoreCard(Player player, String answer) {
        if (answer.equals("y")) {
            player.putCard(deck.draw());
        }
        return player;
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
}
