package domain.participant;

public class Player extends Participant {

    private static final String ERROR_NAME_LENGTH = "[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.";
    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_PROHIBIT_NAME = "[ERROR] 플레이어의 이름은 '딜러'일 수 없습니다.";
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    private BetAmount betAmount;

    private Player(String name) {
        super(name.trim());
        this.betAmount = BetAmount.setDefaultBetting();
    }

    public static Player from(String name) {
        validateNameLength(name);
        validateProhibitName(name);
        return new Player(name);
    }

    private static void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }

    private static void validateProhibitName(String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(ERROR_PROHIBIT_NAME);
        }
    }

    public void betPlayer(int betMoney) {
        this.betAmount = BetAmount.from(betMoney);
    }

    public int getBetAmount() {
        return this.betAmount.getMoney();
    }
}
