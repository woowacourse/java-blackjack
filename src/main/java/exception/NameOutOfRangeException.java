package exception;

public class NameOutOfRangeException extends BlackjackException{
    public NameOutOfRangeException(int min, int max) {
        super(String.format("이름은 %d ~ %d자 내여야 합니다.", min, max));
    }
}
