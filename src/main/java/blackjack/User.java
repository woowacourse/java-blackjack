package blackjack;

public interface User {

    void hit(Card card);
    String getCards();
    String getName();
    int getScore();

}
