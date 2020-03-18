package domain.gamer;

public class EmptyNameException extends IllegalArgumentException {
    public EmptyNameException() {
        super("값을 올바르게 입력해주세요.");
    }
}
