package domain.player;

import domain.card.Card;
import domain.vo.Name;

import java.util.List;

public class Player {
    private final Name name;
    private final HandCards handCards = new HandCards();

    public Player(Name name) {
        this.name = name;
    }

    public void addCard(Card card) {
        handCards.addCard(card);
    }

    public int getCardSum(){
        return handCards.getCardScoreSum();
    }

    public boolean isBust() {
        return handCards.getCardScoreSum() > 21;
    }

    public WinStatus matchResult(int score) {
        int myScore = handCards.getCardScoreSum();
        if (myScore > score) {
            return WinStatus.WIN;
        }

        if (myScore < score) {
            return WinStatus.LOSE;
        }

        return WinStatus.DRAW;
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCards() {
        return handCards.cardsToString();
    }
}
