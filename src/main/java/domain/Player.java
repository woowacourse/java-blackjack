package domain;

import java.util.List;

public class Player implements Participant {

    public static final int WINNING_STANDARD = 21;

    private List<Card> cards;

    public Player(List<Card> initialCards) {
        validateInitialCardsSize(initialCards);
        this.cards = initialCards;
    }

    public WinDrawLose compareTo(int dealerScore) {
        int sum = sumCardNumbers();

        if ((sum > WINNING_STANDARD && dealerScore > WINNING_STANDARD) || sum == dealerScore) {
            return WinDrawLose.DRAW;
        }
        if (sum > WINNING_STANDARD) {
            return WinDrawLose.LOSE;
        }
        if (dealerScore > WINNING_STANDARD) {
            return WinDrawLose.WIN;
        }
        if (sum > dealerScore) {
            return WinDrawLose.WIN;
        }
        return WinDrawLose.LOSE;
    }

    @Override
    public Card addOneCard(Card card) {
        cards.add(card);
        return card;
    }

    @Override
    public int sumCardNumbers() {
        int aceCount = (int) cards.stream()
                .filter(card -> card.getNumber() == TrumpNumber.ACE)
                .count();

        int sum = cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();

        for (int i = 0; i < aceCount; i++) {
            sum = processAce(sum);
        }
        return sum;
    }

    private static int processAce(int sum) {
        if (sum > WINNING_STANDARD) {
            sum -= 10;
        }
        return sum;
    }

    private void validateInitialCardsSize(List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 두 장을 받아야 합니다.");
        }
    }

    public int getSize() {
        return cards.size();
    }
}
