package exception;

public class EmptyDeckException extends BlackjackException{
    public EmptyDeckException() {
        super("뽑을 수 있는 카드가 존재하지 않습니다.");
    }
}
