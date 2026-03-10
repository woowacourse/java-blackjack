package domain.participant;

public class ParticipantInfo {
    private final String name;
    private final Hand hand;

    public ParticipantInfo(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public int handSize() {
        return hand.getHandSize();
    }

    public int getTotalCardScore() {
        return hand.calculateTotalScore();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
