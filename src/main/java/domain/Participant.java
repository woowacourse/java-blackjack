package domain;

public abstract class Participant {
    private String name;
    private Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }
}
