package blackjack.domain;

public interface Player {

    void hit(Card card);

    boolean isBust();

    boolean isBLACKJACK();

    Name getName();

    Cards getCards();

    int getScore();

    boolean isValidRange();

    int compareWinning(Player o);
}
