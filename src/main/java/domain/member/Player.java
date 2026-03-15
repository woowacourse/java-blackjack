package domain.member;

import domain.card.Card;
import domain.card.Deck;
import domain.vo.RoundResult;
import java.util.List;

public class Player {

    private final Member member;

    private BettingAmount amount;

    public Player(Member member, BettingAmount bettingAmount) {
        this.member = member;
        this.amount = bettingAmount;
    }

    public void initDraw(Deck deck) {
        member.initDraw(deck);
    }

    public void draw(Card card) {
        member.receiveCard(card);
    }

    public boolean hasBust() {
        return member.hasBust();
    }

    public boolean hasBlackjack() {
        return member.hasBlackjack();
    }

    public int calculateProfit(RoundResult result) {
        return result.calculateProfit(amount.getAmount());
    }

    public void applyBlackjackBonus() {
        amount = amount.applyBonus();
    }

    public boolean isBiggerThan(Dealer dealer) {
        return this.member.handValue() > dealer.handValue();
    }

    public List<Card> handCards() {
        return member.handCards();
    }

    public int handValue() {
        return member.handValue();
    }

    public List<Card> showFirstCards() {
        return member.handCards();
    }

    public String getName() {
        return member.getName();
    }
}
