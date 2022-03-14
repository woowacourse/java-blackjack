package blackjack.domain;

public interface Player {

    void addCard(Card card);

    boolean isBlackJack();

    boolean isOverMoreCardLimit();

    boolean isOverPointLimit();

    boolean isDealer();

    boolean isWin(Player guest, Player dealer);

    boolean isLose(int point);

    boolean isDraw(Player competitor);

    String getName();

    Deck getDeck();
}
