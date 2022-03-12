package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import java.util.List;
import java.util.stream.Collectors;

public class GameMachine {

    private CardDeck cardDeck;

    public GameMachine(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Dealer createDealer() {
        return new Dealer(cardDeck.drawInitialCard());
    }

    public List<User> createUsers(List<String> users) {
        return users.stream()
                .map(user -> new User(user, cardDeck.drawInitialCard()))
                .collect(Collectors.toList());
    }

    public boolean checkPlayerReceiveCard(Player player) {
        if (player.isPossibleToPickCard()) {
            drawCardToPlayer(player);
            return true;
        }
        return false;
    }

    public void drawCardToPlayer(Player player) {
        player.pickCard(cardDeck.drawCard());
    }

    public boolean haveBlackJack(List<User> users, Dealer dealer) {
        return dealer.isBlackJack() || users.stream()
                .anyMatch(Player::isBlackJack);
    }
}
