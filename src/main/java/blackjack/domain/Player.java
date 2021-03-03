package blackjack.domain;

import java.util.List;

public class Player implements Participant {
    private String name;
    private List<Card> cards;
    private ScoreRule scoreRule;

    public Player(String name, List<Card> cards, ScoreRule scoreRule) {
        this.name = name;
        this.cards = cards;
        this.scoreRule = scoreRule;
    }

    @Override
    public void receiveCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showCards() {
        return cards;
    }
}
