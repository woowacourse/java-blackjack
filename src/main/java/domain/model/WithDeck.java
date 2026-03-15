package domain.model;

public interface WithDeck {

    // 덱의 총합 조회
    int getDeckSum();

    int getDeckSize();

    void appendCard(Card card);
}
