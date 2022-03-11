package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameMachine {

    private CardDeck cardDeck;

    public GameMachine(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Player createDealer() {
        return new Dealer(cardDeck.drawInitialCard());
    }

    public List<Player> createUsers(List<String> users) {
        return users.stream()
                .map(user -> new User(user, cardDeck.drawInitialCard()))
                .collect(Collectors.toList());
    }

    public Boolean checkPlayerReceiveCard(Player player) {
        if (player.isPossibleToPickCard()) {
            drawCardToPlayer(player);
            return true;
        }
        return false;
    }

    public void drawCardToPlayer(Player player) {
        player.pickCard(cardDeck.drawCard());
    }

    public Boolean haveBlackJack(List<Player> users, Player player) {
        List<Player> players = new ArrayList(users);
        players.add(player);
        return players.stream()
                .anyMatch(Player::isBlackJack);
    }
}
