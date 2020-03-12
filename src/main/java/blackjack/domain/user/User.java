package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class User {

    private static final int DEFAULT_DRAW_COUNT = 1;

    private final String name;
    protected Cards cards = new Cards();

    public User(String name) {
        this.name = name;
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public abstract boolean canDrawCard();

    public List<String> getCardsInfos() {
        return cards.getInfos();
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name;
    }
}
