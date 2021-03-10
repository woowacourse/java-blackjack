package blackjack.state;

import blackjack.domain.card.Card;
import blackjack.util.BlackJackConstant;

import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards(final List<Card> cards){
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        if (this.cards.stream().mapToInt(Card::getScore).sum() > 21){
            return true;
        }
        return false;
    }

    public int score(){
        int score = this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        score += checkAce(score);

        return score;
    }

    private int checkAce(int score) {
        if (this.cards.stream()
                .anyMatch(Card::isAce) && score <= BlackJackConstant.ACE_CHECK_SCORE) {
            return BlackJackConstant.TEN_SCORE;
        }
        return 0;
    }
}
