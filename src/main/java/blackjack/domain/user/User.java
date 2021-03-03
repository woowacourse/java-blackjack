package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class User {
    private final Hand hand;
    private final String name;
    private Status status;

    public User(String name) {
        this.hand = Hand.createEmptyHand();
        this.name = name;
    }

    public void firstDraw(Card first, Card second){
        drawCard(first);
        drawCard(second);
    }

    public int handSize() {
        return hand.size();
    }

    public void drawCard(Card card) {
        hand.add(card);
        status = Status.of(hand.calculateScore()); // 게임중, 블랙잭, 버스트
    }

    public void stopUser(){
        status = Status.STOP;
    }

    public boolean isSameStatus(Status status){
        return this.status == status;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public boolean canContinueGame() {
        return status.canContinueGame();
    }
}
