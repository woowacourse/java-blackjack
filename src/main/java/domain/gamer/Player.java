package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;

import java.util.List;

import static domain.GameManager.LIMIT;

public class Player {

    protected final CardGroup cardGroup;
    protected final CardGenerator cardGenerator;
    private final String name;

    public Player(String name, CardGroup cardGroup, CardGenerator cardGenerator) {
        this.name = name;
        this.cardGroup = cardGroup;
        this.cardGenerator = cardGenerator;
    }

    public void receiveCard() {
        cardGroup.addCard(cardGenerator.generate());
    }

    public void receiveCard(int count) {
        while (count-- > 0) {
            receiveCard();
        }
    }

    public boolean isBust() {
        return this.cardGroup.calculateScore() > LIMIT;
    }

    public GameResult calculateGameResult(final int compareScore) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        return GameResult.findByScores(cardGroup.calculateScore(), compareScore);
    }

    public int calculateScore() {
        return cardGroup.calculateScore();
    }

    public List<Card> getCards() {
        return cardGroup.getCards();
    }

    public String getName() {
        return name;
    }
}
