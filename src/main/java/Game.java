import domain.card.Card;
import domain.user.Users;

import java.util.List;

public class Game {
    private final TotalDeck totalDeck;
    private final Users users;

    public Game(TotalDeck totalDeck, Users users) {
        this.totalDeck = totalDeck;
        this.users = users;

        users.stream()
                .forEach(user -> {
                    user.addCard(totalDeck.getNewCard());
                    user.addCard(totalDeck.getNewCard());
                });
        //TODO
    }

    public void doOrDie(String command) {
        if ("y".equals(command)) {
            users.addCardOfCurrentUser(totalDeck.getNewCard());
        }
        if ("n".equals(command)) {
            users.nextUser();
        }
    }

    public List<Card> showCurrentUserDeck() {
        return users.showCurrentUserDeck();
    }

    public boolean continueInput() {
        return users.isCurrentUserPlayer();
    }
}
