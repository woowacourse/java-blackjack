package model.cards;

import model.card.Card;
import model.name.Name;

import java.util.ArrayList;
import java.util.List;

import static util.Rule.GOAL_SCORE;

public class Cards {

    private final List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards from(final List<Card> cards) {
        return new Cards(cards);
    }

    public static Cards createPlayerCards() {
        return new Cards(new ArrayList<>());
    }

    public void addCards(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public void downInitialScoreIfExceedLimit() {
        if (this.calculateScore() > GOAL_SCORE.getValue()) {
            firstCardDownScore();
        }
    }

    private void firstCardDownScore() {
        this.getCardList()
                .stream()
                .limit(1)
                .forEach(Card::downScore);
    }

    public void changeScoreIfHaveAce() {
        cards.stream()
                .filter(card -> Name.isAce(card.getName()))
                .findFirst()
                .ifPresent(Card::downScore);
    }

    public Card useCard(int index) {
        if (index < cards.size()) {
            Card card = cards.get(index);
            cards.remove(index);
            return card;
        }
        return null;
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCardList() {
        return cards;
    }

    public int getCardSize() {
        return cards.size();
    }
}
