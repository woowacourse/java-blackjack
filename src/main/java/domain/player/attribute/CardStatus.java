package domain.player.attribute;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardStatus {

    private final Score score;

    private List<Card> cards;

    public CardStatus() {
        this.score = new Score();
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
        this.score.addValue(card);
    }

    public int getCardsSize() {
        return cards.size();
    }

    public boolean isBust() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum() > 21;
    }

    public int getTotalValue() {
        return score.getScore();
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .collect(Collectors.toList());
    }

    public String getFirstCardInfo() {
        return cards.get(0).getCardInfo();
    }
}
