package blackjack.domain;

public interface GameRule {
    boolean receivable();
    int receivableCardSize();
}
