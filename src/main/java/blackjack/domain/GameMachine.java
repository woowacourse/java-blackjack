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

    public Player createDealer() {
        return new Dealer(cardDeck.drawInitialCard());
    }

    public List<Player> createUsers(List<String> users) {
        return users.stream()
                .map(user -> new User(user, cardDeck.drawInitialCard()))
                .collect(Collectors.toList());
    }

    public Boolean checkDealerReceiveCard(Player dealer) {
        if (dealer.isPossibleToPickCard()) {
            dealer.pickCard(cardDeck.drawCard());
            return true;
        }
        return false;
    }

    public void drawCardToUser(Player user) {
        user.pickCard(cardDeck.drawCard());
    }
}
