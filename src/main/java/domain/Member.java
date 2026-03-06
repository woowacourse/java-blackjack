package domain;

import java.util.List;

public class Member {

    private final String name;
    private final Hand hand;
    private final Role role;

    public Member(String name, Role role) {
        this.name = name;
        this.role = role;
        this.hand = new Hand();
    }

    public String name() {
        return name;
    }

    public int currentValue() {
        return hand.calculateTotalValue();
    }

    public List<Card> currentCards() {
        return hand.cards();
    }

    public boolean isDealer() {
        return role.equals(Role.DEALER);
    }

    public void receiveCard(Card card) {
        hand.appendCard(card);
    }

    public Member decideWinner(Member member) {
        if (this.hand.calculateTotalValue() > member.hand.calculateTotalValue()) {
            return this;
        }
        return member;
    }
}
