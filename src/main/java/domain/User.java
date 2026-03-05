package domain;

public class User {
    private String username;
    private Hand hand;

    public User(String name) {
        this.username = name;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }
}
