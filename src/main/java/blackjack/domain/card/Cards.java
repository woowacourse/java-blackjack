package blackjack.domain.card;

import blackjack.domain.rule.ScoreRule;

import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards(List<Card> initialCard) {
        this.cards = initialCard;
    }

    public int sumTotalScore(ScoreRule scoreRule) {
        return scoreRule.sumTotalScore(cards);
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> splitCardsFromTo(int from, int to) {
        return cards.subList(from, to);
    }

    public List<Card> toCardList() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}
