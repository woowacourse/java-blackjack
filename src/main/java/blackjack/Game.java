package blackjack;

import blackjack.card.Deck;
import blackjack.participant.Dealer;
import blackjack.participant.Participant;
import blackjack.participant.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final Dealer dealer;
    private final List<Player> players;

    public Game(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public static Game of(List<String> playerNames) {
        return new Game(playerNames.stream()
                                   .map(Player::new)
                                   .collect(Collectors.toList()));
    }

    public void setUpTwoCards() {
        addTwoCard(dealer);
        for (Player player : players) {
            addTwoCard(player);
        }
    }

    private void addTwoCard(Participant participant) {
        participant.addCard(Deck.draw());
        participant.addCard(Deck.draw());
    }

    public void giveCard(Participant participant) {
        participant.addCard(Deck.draw());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
