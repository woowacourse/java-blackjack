package domain.gamer;

public class NameDuplicationException extends IllegalArgumentException {
    public NameDuplicationException() {
        super("중복되는 이름이 존재합니다.");
    }
}
