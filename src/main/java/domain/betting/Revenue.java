package domain.betting;

import java.util.Objects;

public class Revenue {
    private final int money;

    public Revenue(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Revenue revenue = (Revenue) o;
        return money == revenue.money;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(money);
    }
}
