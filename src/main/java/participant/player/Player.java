package participant.player;

import card.Card;
import card.Cards;
import java.util.List;

public class Player {

    private final Name name;
    private final GameElements gameElements;

    public Player(List<Card> cardDeck, Name name, BetMoney betMoney) {
        this.name = name;
        this.gameElements = new GameElements(cardDeck, betMoney);
    }

    public BetMoney getBetMoney() {
        return gameElements.getBetMoney();
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return gameElements.getCards();
    }

    public boolean isBust() {
        return gameElements.isBustCard();
    }

    public void hit(Card card) {
        gameElements.hit(card);
    }

    public int getCardScore() {
        return gameElements.getCardsScore();
    }

    public boolean isBlackJack() {
        return gameElements.isBlackJackCard();
    }
}
