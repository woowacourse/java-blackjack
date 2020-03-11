public class InvalidParticipantNameException extends IllegalArgumentException {
    public static final String NULL_OR_EMPTY = "이름은 빈 문자열이나 null이 될 수 없습니다.";

    public InvalidParticipantNameException(String s) {
        super(s);
    }
}
