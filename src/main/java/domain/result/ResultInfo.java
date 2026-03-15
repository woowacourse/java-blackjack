package domain.result;

public enum ResultInfo {
    WIN(0) {
        @Override
        public int calculateProfit(int amount) {
            return amount;
        }
    },
    DRAW(1) {
        @Override
        public int calculateProfit(int amount) {
            return 0;
        }
    },
    DEFEAT(2) {
        @Override
        public int calculateProfit(int amount) {
            return -amount;
        }
    },
    BLACKJACK_WIN(3) {
        @Override
        public int calculateProfit(int amount) {
            return (int) (amount * 1.5);
        }
    };

    private final int code;

    ResultInfo(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public abstract int calculateProfit(int amount);
}
