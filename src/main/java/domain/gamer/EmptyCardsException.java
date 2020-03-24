package domain.gamer;

public class EmptyCardsException extends RuntimeException {
    public EmptyCardsException() {
        super("카드가 한 장 이상 있어야 합니다.");
    }
}
