package domain.member;

import domain.card.Card;
import domain.card.Deck;
import domain.vo.RoundResult;
import java.util.List;

public class Member {

    private final Hand hand;
    private final Name name;
    private final MemberRole role;

    public Member(Name name, MemberRole role) {
        this.name = name;
        this.hand = new Hand();
        this.role = role;
    }

    public int calculateProfit(RoundResult result) {
        return result.calculateProfit(role.getBettingAmount());
    }

    public void applyBlackjackBonus() {
        role.applyBonus();
    }

    public int handValue() {
        return hand.calculateTotalValue();
    }

    public List<Card> handCards() {
        return hand.getCards();
    }

    public void receiveCard(Card card) {
        hand.appendCard(card);
    }

    public boolean hasBust() {
        return hand.isBust();
    }

    public boolean hasBlackjack() {
        return hand.isBlackjack();
    }

    public List<Card> showFirstCards() {
        return role.showFirstCards(hand.getCards());
    }

    public void initDraw(Deck deck) {
        receiveCard(deck.draw());
        receiveCard(deck.draw());
    }

    public String getName() {
        return name.getValue();
    }
}
