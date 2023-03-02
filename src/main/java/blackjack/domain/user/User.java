package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.user.name.UserName;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final UserName name;
    private final List<Card> cards;

    protected User(final UserName name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void drawCard(CardPack cardPack) {
        cards.add(cardPack.pop());
    }

    public List<Card> showCards() {
        return new ArrayList<>(cards);
    }

    public UserName getName() {
        return name;
    }
}
