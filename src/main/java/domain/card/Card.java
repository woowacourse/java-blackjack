package domain.card;

public interface Card {
    Score getScore();

    String getSymbol();

    boolean isAce();
}
