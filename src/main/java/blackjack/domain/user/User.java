package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.List;

public abstract class User {

    protected final Cards cards;
    protected final String name;

    protected User(String name) {
        cards = new Cards();
        this.name = name;
    }

    public List<Card> showCards() {
        return cards.getCards();
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    private void validateNegative(int score) {
        if (score < 0) {
            throw new RuntimeException("점수 계산에 문제가 있습니다.");
        }
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getScore() {
        int score = cards.calculateScore();
        validateNegative(score);

        return score;
    }

    public boolean isBlackJack() {
        return ((cards.getCards().size() == 2) && cards.isSameBlackJackNumber(getScore()));
    }

    public abstract boolean isDealer();

    public abstract List<Card> showInitCards();

    public abstract boolean isDrawable();

    public String getName(){
        return name;
    }
}
