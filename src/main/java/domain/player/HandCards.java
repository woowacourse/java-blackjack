package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private final List<Card> cards;

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

    // 손에 쥔 카드 점수 계산해 반환
    public int getCardScoreSum() {
        int sum = 0;
        int aceCount = 0;


        for(Card card : cards) {
            if (card.isAce()) {
                aceCount++;
                continue;
            }

            sum += card.getValue();
        }

        while(aceCount > 0) {
            sum += calculateAce(sum);
            aceCount--;
        }

        return sum;
    }

    // ACE 처리 로직
    private int calculateAce(int currentSum){
        int aceCase11 = Math.max(21 - (currentSum + 11), 0);
        int aceCase1 = Math.max(21 - (currentSum + 1), 0);

        if (aceCase11 < aceCase1) {
            return 11;
        }

        return 1;
    }
}
