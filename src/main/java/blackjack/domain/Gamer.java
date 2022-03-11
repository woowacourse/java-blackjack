package blackjack.domain;

public interface Gamer {

    void hit(Card card);

    boolean isBust();

    boolean isBLACKJACK();

    Name getName();

    Cards getCards();

    int getScore();

    boolean isValidRange();

    int compareWinning(Gamer o);
}
