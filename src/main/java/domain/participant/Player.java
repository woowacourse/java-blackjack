package domain.participant;

import domain.card.Card;
import domain.Hand;
import java.util.List;

public class Player {
    private final String name;
    private final Hand hand = new Hand();
    // TODO: 베팅 기능 추가 시 베팅 금액 필드 또는 일급 객체 필요


    public Player(String name) {
        this.name = name;
        // TODO: 베팅 기능 추가 시 생성자에서 베팅 금액도 함께 받아야 할 수 있음
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public boolean canDraw() {
        return !(isBust()|| hand.isBlackjack());
    }

    public List<String> getHandToString() {
        return hand.toStringList();
    }

    public int getScore(){
        return hand.calculateScore();
    }

    public String getName() {
        return name;
    }

}
