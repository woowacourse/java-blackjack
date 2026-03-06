package domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandCard {
    private static final int BLACKJACK_MAX_LIMIT = 21;
    private static final int ACE_MAX_VALUE = 11;
    private static final int ACE_MIN_VALUE = 1;
    private final List<Card> cards;

    public HandCard() {
        this.cards = new ArrayList<>();
    }

    public int cardCalculator() {
        int nonAceTotal = cards.stream()
                .map(Card::getRankScore)
                .filter(e -> e != 1)
                .mapToInt(Integer::intValue)
                .sum();
        int aceCnt = (int) cards.stream()
                .map(Card::getRankScore)
                .filter(e -> e == 1)
                .count();

        return nonAceTotal + aceCalculator(nonAceTotal, aceCnt);
    }

    private int aceCalculator(int nonAceTotal, int aceCnt){
        return Math.min(aceCnt, Math.max(0, (BLACKJACK_MAX_LIMIT - nonAceTotal) / (ACE_MAX_VALUE - ACE_MIN_VALUE)));
    }


    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> getCardInfos() {
        return cards.stream().map(Card::getCardInfo).toList();
    }

    public String getFirstCardInfo() {
        return cards.getFirst().getCardInfo();
    }
}
