package blackjack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private static final int BLACKJACK_NUMBER = 21;

    private final Deck deck;
    private final Players players;

    public BlackjackGame(List<String> names) {
        this.deck = Deck.create();
        this.players = new Players(new Dealer(new HoldCards(deck.draw(), deck.draw())), toPlayers(names));
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
        return players.shouldDealerHit(deck);
    }

    public boolean isHit(Player player, String command) {
        return Command.find(command) == Command.YES && player.countCards() < BLACKJACK_NUMBER;
    }
    
    public Map<Outcome, List<Player>> getGameResult() {
        return players.getGameResult();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Participant> getParticipant() {
        return players.getParticipant();
    }
}
