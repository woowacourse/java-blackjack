package domain.result;

public enum ResultInfo {
    WIN("승", 0) {
        @Override
        public int calculateProfit(int amount) {
            return amount;
        }
    },
    DRAW("무", 1) {
        @Override
        public int calculateProfit(int amount) {
            return 0;
        }
    },
    DEFEAT("패", 2) {
        @Override
        public int calculateProfit(int amount) {
            return -amount;
        }
    },
    BLACKJACK_WIN("블랙잭", 3) {
        @Override
        public int calculateProfit(int amount) {
            return (int) ((amount * 1.5) - amount);
        }
    };

    private final String info;
    private final int code;

    ResultInfo(String info, int code) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public int getCode() {
        return code;
    }

    public abstract int calculateProfit(int amount);
}
