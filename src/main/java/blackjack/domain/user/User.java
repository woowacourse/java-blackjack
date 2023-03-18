package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.Score;
import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private final String name;
    private Hand hand;

    User(final String name) {
        this.name = name.trim();
        this.hand = new Hand();
    }

    public void drawCard(final CardPack cardPack) {
        hand = hand.addCard(cardPack.pop());
    }

    public List<Card> getCards() {
        return new ArrayList<>(hand.getCards());
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return hand.getScore();
    }

    public boolean isBust() {
//        return hand.getScore().isBust();
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackjack();
    }

    public GameResult getResultByUser(final User user) {
        if ((this.isBlackJack() && user.isBlackJack()) || (!isBust() && getScore().equals(user.getScore()))) {
            return GameResult.DRAW;
        }

        if (!this.isBust() && !this.isBlackJack() && this.getScore().isMoreThen(user.getScore())
                || !this.isBust() && user.isBust()) {
            return GameResult.WIN;
        }

        if (this.isBlackJack() && !user.isBlackJack()) {
            return GameResult.BLACKJACK;
        }

        return GameResult.LOSE;
    }
}
