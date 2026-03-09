package domain.card;

import java.util.*;

public class CardDeck {
    private List<Card> cardDeck = new ArrayList<>();
    private int index = 0;

    public CardDeck() {
        initCardDeck();
    }

    private void initCardDeck() {
        for (TrumpSuit suit : TrumpSuit.values()) {
            for (TrumpNumber number : TrumpNumber.values()){
                cardDeck.add(new Card(suit, number));
            }
        }

        shuffleCards();
    }

    private void shuffleCards(){
        Collections.shuffle(cardDeck);
    }

    public Card getCard() {
        if (index > cardDeck.size() - 1) { // 카드 덱을 한 번 순환하면 셔플해 초기화한다
            shuffleCards();
            index = 0;
        }

        return cardDeck.get(index++);
    }
}
