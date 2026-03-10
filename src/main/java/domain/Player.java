package domain;

public final class Player {
    private final Name name;
    private final Hand hand;
    private Outcome outcome;

    public Player(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public Player(String name) {
        this(new Name(name));
    }

    public void drawCard(Cards cards) {
        hand.addCard(cards.draw());
    }

    public Score getScore() {
        return hand.getScore();
    }

    public int getResult() {
        return hand.getResult();
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Hand getCardList() {
        return hand;
    }

    public boolean checkBust() {
        return hand.checkBust();
    }

    public String getName() {
        return name.getName();
    }
}
