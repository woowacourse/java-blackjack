package domain;

public interface CardPossessor {
    void drawCard(CardProvider cardProvider);
    int calculateScore();
}
