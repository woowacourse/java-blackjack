package domain.player;

import domain.card.Card;

import java.util.List;

public class Player extends User {

    public Player(String name, List<Card> userCardDeck) {
        super(userCardDeck);
        validatePlayerName(name);
        this.name = name;
    }

    @Override
    public void drawCard(Card card) {
        this.cards.add(card);
        validateDuplicateCard();
    }

    public String getName() {
        return name;
    }
}
