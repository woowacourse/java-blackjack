package domain.card;

public interface Card {
    String getSymbol();

    Score getScore();

    boolean isAce();
}
