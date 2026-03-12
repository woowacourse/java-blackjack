package domain.participant;

import domain.PlayerStatus;
import domain.card.Card;
import domain.Hand;
import domain.constant.Result;
import java.util.List;

public class Player {
    private final String name;
    private final Hand hand = new Hand();
    private PlayerStatus status;


    public Player(String name, int bettingMoney) {
        this.name = name;
        this.status = new PlayerStatus(bettingMoney);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackjack();
    }

    public Result getResult() {
        return status.getResult();
    }

    public void renewedWithBlackJack() {
        status.renewedWithBlackJack();
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public boolean canDraw() {
        return !(isBust()|| hand.isBlackjack());
    }

    public int handSize() {
        return hand.size();
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
