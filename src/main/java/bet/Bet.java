package bet;

public class Bet {
    private int amount;

    public Bet(int amount) {
        validateBetAmount(amount);
        this.amount = amount;
    }

    public void calculateBettingResult(boolean isBlackjack, boolean isWinner, boolean isDraw) {
        if (isBlackjack) {
            amount = amount + (int) (amount * 1.5);
            return;
        }
        if (isWinner) {
            amount = amount + amount;
            return;
        }
        if (isDraw) {
            return;
        }
        amount = amount - amount;
    }
    // TODO: amount를 final로 만들고 새로운 Bet을 만들 것인가, 아니면 amount만 바꿔줄 것인가

    private void validateBetAmount(int amount) {
        if (amount > 100_000_000 || amount <= 0) {
            throw new IllegalArgumentException("베팅 금액에 문제가 있음");
        }
    }
}
