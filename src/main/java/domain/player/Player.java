package domain.player;

import domain.card.HandCard;
import domain.deck.CardDeck;
import java.util.List;

public abstract class Player {

    protected final String name;
    protected final HandCard handCard;

    protected Player(String name) {
        this.name = name;
        this.handCard = new HandCard();
    }

    public String getName() {
        return name;
    }

    public void deal(CardDeck cardDeck) {
        handCard.addCard(cardDeck.deal());
    }

    public int score() {
        return handCard.score();
    }

    public boolean isBust() {
        return handCard.isBust();
    }

    public List<String> getCardInfo() {
        return handCard.cardInfos();
    }

}

