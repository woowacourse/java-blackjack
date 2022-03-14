package domain.player;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final String name;
    protected final Cards cards;

    protected Player(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public abstract boolean isDealer();

    public abstract boolean canGetMoreCard();

    public abstract List<Card> getOpenCards();

    public abstract MatchResult match(Player another);

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    protected MatchResult getMatchResultAfterBustCheck(Player another) {
        if (hasHigherScoreIgnoreBust(another) || winByBlackJack(another)) {
            return MatchResult.WIN;
        }

        if (another.hasHigherScoreIgnoreBust(this) || another.winByBlackJack(this)) {
            return MatchResult.LOSE;
        }

        return MatchResult.DRAW;
    }

    protected boolean hasHigherScoreIgnoreBust(Player another) {
        return this.getScore() > another.getScore();
    }

    protected boolean winByBlackJack(Player another) {
        return this.isBlackJack() && !another.isBlackJack();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards.getCards());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", cards=").append(cards);
        sb.append('}');
        return sb.toString();
    }
}
