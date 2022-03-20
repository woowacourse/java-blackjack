package blackjack.domain.participant;

public class Player extends Participant {
    private static final String MONEY_ERROR = "[ERROR] 베팅 금액은 0원 보다 커야 됩니다.";
    private final long bettingMoney;

    public Player(String name, long bettingMoney) {
        super(name);
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateMoney(long bettingMoney) {
       if(bettingMoney <= 0) {
           throw new IllegalArgumentException(MONEY_ERROR);
       }
    }

    @Override
    public boolean canDraw() {
        return !isBust() && !isBlackjack();
    }

    public int isWin(Participant participant) {
        return Integer.compare(score().getSum(), participant.score().getSum());
    }

    public long getBettingMoney() {
        return bettingMoney;
    }
}
