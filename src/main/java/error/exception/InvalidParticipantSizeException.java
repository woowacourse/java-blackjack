package error.exception;

public class InvalidParticipantSizeException extends RuntimeException {

    private static final String message = "게임의 최대 참여자는 %d명을 넘을 수 없습니다.";

    public InvalidParticipantSizeException(int size) {
        super(message.formatted(size));
    }
}
