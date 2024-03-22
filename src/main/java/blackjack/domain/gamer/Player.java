package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.PlayerResult;
import blackjack.domain.money.Chip;

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
        return gamer.cards().size() == ONLY_DEAL_CARD_COUNT;
    }

    public Long madeProfit(PlayerResult playerResult) {
        return gamer.madeProfit(playerResult);
    }

    public String name() {
        return name.name();
    }

    public List<Card> cards() {
        return gamer.cards();
    }

    public int score() {
        return gamer.score();
    }

    public Long chip() {
        return gamer.profit();
    }
}
