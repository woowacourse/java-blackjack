package second.domain;

public interface IPlayer {
    void drawCard(ICardDeck CardDeck);

    boolean isBust();

    boolean canDrawMore();
}
