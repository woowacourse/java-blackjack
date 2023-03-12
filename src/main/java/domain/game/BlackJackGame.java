package domain.game;

import domain.card.GameDeck;
import domain.user.Dealer;
import domain.state.DealerStatus;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final GameDeck gameDeck;

    public BlackJackGame(List<Player> players, Dealer dealer, GameDeck gameDeck) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
        this.gameDeck = gameDeck;
    }

    public void drawOneMoreCardForPlayer(Player player) {
        player.receiveCard(gameDeck.drawCard());
    }

    public void drawCardUntilOverSixteen() {
        while (dealer.isUserStatus(DealerStatus.UNDER_SEVENTEEN)) {
            dealer.receiveCard(gameDeck.drawCard());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
