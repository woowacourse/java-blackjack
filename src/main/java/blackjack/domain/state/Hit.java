package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.BlackJackScoreRule;

import java.util.List;

public class Hit implements State {
    private Cards cards;

    public Hit(List<Card> initialCard) {
        cards = new Cards(initialCard);
    }

    @Override
    public boolean isEndState() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public double profit(State enemyState) {
        return 0;
    }

    @Override
    public void draw(Card card) {
        cards.receiveCard(card);
    }

    @Override
    public State changeState() {
        int sum = cards.getTotalScore(new BlackJackScoreRule());
        System.out.println(sum);
        if (sum == 21 && isInitialState()) {
            return new BlackJack(cards.toCardList());
        }

        if (sum > 21) {
            return new Bust(cards.toCardList());
        }

        return this;
    }

    @Override
    public State stay() {
        return new Stay(cards.toCardList());
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    private boolean isInitialState() {
        return cards.size() == 2;
    }
}
