package domain;

public abstract class Participant {
    private final Hand hand;
    private final Name name;

    public Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public String getName() {
        return name.getName();
    }

    public Hand getHand() {
        return new Hand(hand.getCards());
    }

    public abstract boolean canDraw();
}