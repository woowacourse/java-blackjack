package blakcjack.domain.card;

public class EmptyCardsException extends RuntimeException {
    public EmptyCardsException() {
        super("카드가 하나도 없습니다.");
    }
}
