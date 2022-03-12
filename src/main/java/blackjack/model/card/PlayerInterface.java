package blackjack.model.card;

public interface PlayerInterface {
    void receive(Card card);

    boolean canReceive();

    String getName();

}
