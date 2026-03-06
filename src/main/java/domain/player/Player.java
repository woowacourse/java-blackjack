package domain.player;

import domain.card.HandCard;
import domain.deck.CardDeck;
import dto.CardInfo;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;

public abstract class Player {
    protected final String name;
    protected final HandCard handCard;

    protected static final int BLACKJACK_MAX_LIMIT = 21;

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
        return handCard.cardCalculator();
    }

    public boolean isBust() {
        return score() > BLACKJACK_MAX_LIMIT;
    }

    public CardInfo getCardInfo() {
        return new CardInfo(name, handCard.getCardInfos(), score());
    }

}

