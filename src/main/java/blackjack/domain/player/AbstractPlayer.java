package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.PlayerCards;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    protected static final int BLACKJACK = 21;

    private final PlayerCards cards;
    private final Name name;

    protected AbstractPlayer(String name) {
        cards = new PlayerCards();
        this.name = new Name(name);
    }

    @Override
    public void drawCard(final Card card) {
        cards.add(card);
    }

    @Override
    abstract public boolean isCanDraw();

    @Override
    public int getScore() {
        return cards.score();
    }

    @Override
    public void drawRandomOneCard() {
        Cards cards = Cards.getInstance();
        drawCard(cards.draw());
    }

    @Override
    public void drawRandomTwoCards() {
        Cards cards = Cards.getInstance();
        drawCard(cards.draw());
        drawCard(cards.draw());
    }

    protected boolean isBlackJack() {
        return cards.isBlackjack();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }
}