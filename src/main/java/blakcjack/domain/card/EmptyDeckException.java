package blakcjack.domain.card;

public class EmptyDeckException extends RuntimeException {
    public EmptyDeckException() {
        super("덱에 카드가 없습니다.");
    }
}
