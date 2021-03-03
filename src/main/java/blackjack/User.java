package blackjack;

public interface User {

    void hit(Card card);
    String showCards();
    String getName();
    int getScore();

}
