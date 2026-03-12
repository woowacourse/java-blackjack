package team.blackjack.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class Hand {

    private final Set<Card> cards = new LinkedHashSet<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.stream().toList();
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .toList();
    }

    public int getScore(){
        return DefaultBlackjackRule.calculateBestScore(this.getCards());
    }

    public boolean isBust() {
        return DefaultBlackjackRule.isBust(getScore());
    }

    public boolean isBlackjack() {
        return DefaultBlackjackRule.isBlackjack(getScore(), cards.size());
    }

}
