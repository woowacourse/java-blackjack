package domain.vo;

public class Cost {
    private static final int COST_UNIT = 100;
    private static final int MAX_COST = 10_000_000;
    private static final int MIN_COST = 100;
    private final int cost;


    public Cost(int cost) {
        validateRange(cost);
        validateUnit(cost);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    private void validateRange(int cost) {
        if (cost > MAX_COST || cost < MIN_COST) {
            throw new IllegalArgumentException("[ERROR] 배팅은 " + MIN_COST + " ~ " + MAX_COST + " 사이만 가능합니다.");
        }
    }

    private void validateUnit(int cost) {
        if ((cost % COST_UNIT) != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 최소 단위는 " + COST_UNIT + " 입니다.");
        }
    }
}
