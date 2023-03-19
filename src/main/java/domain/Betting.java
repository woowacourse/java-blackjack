package domain;

public class Betting {

    private static final String BETTING_ERROR_GUIDE_MESSAGE = "[ERROR] 배팅 금액은 숫자만 입력 가능합니다.";

    private final int betting;

    public Betting(String betting) {
        validateBetting(betting);
        this.betting = Integer.parseInt(betting);
    }

    private void validateBetting(String betting) {
        try {
            Integer.parseInt(betting);
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException(BETTING_ERROR_GUIDE_MESSAGE);
        }
    }

    public int getBetting() {
        return betting;
    }
}
