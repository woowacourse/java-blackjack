package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserDeck;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import java.util.List;

public abstract class User {

    private final UserDeck userDeck;
    protected State state;

    public User(UserDeck userDeck) {
        this.userDeck = userDeck;
        this.state = StateFactory.draw(userDeck);
    }

    public void draw(CardDeck cardDeck) {
        userDeck.draw(cardDeck.draw());
        state = state.draw(userDeck);
    }

    public int score() {
        return userDeck.score();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public List<Card> getCards() {
        return userDeck.getUserCards();
    }

    public int compare(User user) {
        return this.score() - user.score();
    }
}
