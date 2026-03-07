package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // 손에 쥔 카드 출력
    public List<String> cardsToString() {
        List<String> cardList = new ArrayList<>();

        for (Card card : cards) {
            cardList.add(card.toString());
        }

        return cardList;
    }
}
