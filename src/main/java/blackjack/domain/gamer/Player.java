package blackjack.domain.gamer;

import blackjack.domain.supplies.Card;
import blackjack.domain.supplies.Chip;
import blackjack.domain.supplies.Hand;

import java.util.List;

public class Player {
    private static final int ONLY_DEAL_CARD_COUNT = 2;

    private final Name name;
    private final Gamer gamer;

    public Player(Name name, Chip chip) {
        this.name = name;
        this.gamer = new Gamer(new Hand(), chip);
    }

    public void draw(List<Card> cards) {
        gamer.draw(cards);
    }

    public void draw(Card card) {
        gamer.draw(card);
    }

    public boolean isBust() {
        return gamer.isBust();
    }

    public boolean isBlackjack() {
        return gamer.isBlackjack();
    }

    public boolean isMaxScore() {
        return gamer.isMaxScore();
    }

    public boolean isOnlyDeal() {
        return gamer.getCards().size() == ONLY_DEAL_CARD_COUNT;
    }

    public String getName() {
        return name.name();
    }

    public List<Card> getCards() {
        return gamer.getCards();
    }

    public int getScore() {
        return gamer.getScore();
    }
}
