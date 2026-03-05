package domain.player;

import domain.card.HandCard;
import domain.deck.CardDeck;
import dto.CardInfos;

public abstract class Player {
    protected final HandCard handCard;

    protected Player() {
        this.handCard = new HandCard();
    }

    public void deal(CardDeck cardDeck){
        handCard.addCard(cardDeck.deal());
    }

    public CardInfos showAllCards(){
        return new CardInfos(handCard.getCardInfos());
    }

    public int score(){
       return handCard.cardCalculator();
    }
}

