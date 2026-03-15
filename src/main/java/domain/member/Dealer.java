package domain.member;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Dealer {

    private static final int DEALER_DRAW_CONDITION = 16;

    private final Member member;

    public Dealer(Member member) {
        this.member = member;
    }

    public void initDraw(Deck deck) {
        member.initDraw(deck);
    }

    public void draw(Card card) {
        member.receiveCard(card);
    }

    public boolean canDealerDraw() {
        return member.handValue() <= DEALER_DRAW_CONDITION;
    }

    public boolean isBiggerThan(Player player) {
        return this.member.handValue() > player.handValue();
    }

    public boolean hasBust() {
        return member.hasBust();
    }

    public int handValue() {
        return member.handValue();
    }

    public List<Card> handCards() {
        return member.handCards();
    }

    public List<Card> showFirstCards() {
        return List.of(member.handCards().getFirst());
    }

    public String getName() {
        return member.getName();
    }
}
