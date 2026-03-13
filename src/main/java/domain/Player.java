package domain;

import java.util.List;

import domain.enums.MatchCase;

public class Player {
    private static final int ACE_ADDITIONAL_SCORE = 11;

    private final Cards cards;
    private final String name;
    private int bettingScore;

    public Player(String name) {
        this.name = name;
        this.bettingScore = 0;
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public void addInitializedCard(Deck deck) {
        add(deck.pop());
        add(deck.pop());
    }

    public MatchCase calculateMatchCase(int dealerTotal) {
        if (cards.getFinalScore() > dealerTotal) {
            return MatchCase.WIN;
        }
        if (cards.getFinalScore() == dealerTotal) {
            return MatchCase.DRAW;
        }
        if (cards.getFinalScore() < dealerTotal) {
            return MatchCase.LOSE;
        }
        throw new IllegalArgumentException("[ERROR] 일치하는 enum이 없습니다.");
    }

    public void calculateMoney(MatchCase matchCase, boolean isDealerBlackjack) {
        if (cards.isBlackjack() && !isDealerBlackjack) {
            bettingScore = (int) ((int) bettingScore * 1.5);
            return;
        }
        if (matchCase.equals(MatchCase.LOSE)) {
            loseMoney();
            return;
        }
        bettingScore=0;
    }

    public void loseMoney() {
        int minusScore = bettingScore * 2;
        bettingScore -= minusScore;
    }

    public void betMoney(int money) {
        bettingScore = money;
    }

    public boolean isBust() {
        return cards.getFinalScore() > Game.BLACKJACK_VALUE;
    }

    public boolean isDealerBlackjack() {
        return cards.isBlackjack();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getCardsTotalSum() {
        return cards.getFinalScore();
    }

    public int getBettingScore() {
        return bettingScore;
    }
}
