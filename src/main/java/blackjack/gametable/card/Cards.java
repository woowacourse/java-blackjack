package blackjack.gametable.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    public static final int MAX_SCORE = 21;
    public static final int SOFT_ACE_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 남은 카드가 없습니다.");
        }
        return cards.removeLast();
    }

    public int sumCardScores() {
        int sum = calculateScore();
        int aceCounts = countAces();
        for (int i = 0; i < aceCounts; i++) {
            sum = processAce(sum);
        }
        return sum;
    }

    public List<Card> drawInitialCards() {
        return new ArrayList<>(List.of(drawCard(), drawCard()));
    }

    public List<Card> openDealerInitialCards() {
        return new ArrayList<>(List.of(cards.getFirst()));
    }

    public List<Card> openPlayerInitialCards() {
        return new ArrayList<>(cards);
    }

    public boolean isBlackjack() {
        return sumCardScores() == MAX_SCORE && cards.size() == 2;
    }

    private int calculateScore() {
        return cards.stream().mapToInt(Card::getRankScore).sum();
    }

    private int countAces() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int processAce(int sum) {
        if (sum > MAX_SCORE) {
            sum -= SOFT_ACE_DIFFERENCE;
        }
        return sum;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

}
