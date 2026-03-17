package domain;

import java.util.Collections;
import java.util.List;

import domain.enums.MatchCase;

public class Player {

    private final Cards cards;
    private final String name;
    private final Betting betting;

    public Player(String name, Betting betting) {
        this.name = name;
        this.betting = betting;
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
            betting.gainMoney();
            return;
        }
        if (matchCase.equals(MatchCase.LOSE)) {
            betting.loseMoney();
            return;
        }
        if (matchCase.equals(MatchCase.WIN)) {
            return;
        }
        betting.resetMoneyZero();
    }

    public boolean isBust() {
        return cards.getFinalScore() > Game.BLACKJACK_VALUE;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public int getBettingScore() {
        return betting.getBettingScore();
    }

    public int getCardsTotalSum() {
        return cards.getFinalScore();
    }

}
