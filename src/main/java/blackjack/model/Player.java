package blackjack.model;

import java.util.List;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public void addCards(List<Card> cardToAdd) {
        cards.addCard(cardToAdd);
    }

    public boolean checkDrawCardState() {
        return !cards.isGreaterThanWinningScore();
    }

    public ResultStatus compareScore(Cards otherCards) {
        if (cards.isGreaterThanWinningScore()) {
            return ResultStatus.LOSE;
        }
        if (otherCards.isGreaterThanWinningScore()) {
            return ResultStatus.WIN;
        }
        int calculatedScore = cards.calculateScore();
        int otherScore = otherCards.calculateScore();
        if (calculatedScore > otherScore) {
            return ResultStatus.WIN;
        }
        if (calculatedScore < otherScore) {
            return ResultStatus.LOSE;
        }
        return ResultStatus.PUSH;
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
