package exception;

public class IllegalToGetProfitRatioInNotFinishedException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 아직 게임이 끝나지 않아 수익률를 확인할 수 없습니다.";

    public IllegalToGetProfitRatioInNotFinishedException() {
        super(MESSAGE);
    }
}
