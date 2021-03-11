package rentcompany.domain;

import java.util.Objects;

public class Fuel {
    private static final String NEGATIVE_QUANTITY_ERROR_MSG_FORMAT = "연료량은 음수일 수 없습니다. 연료량 : %f";
    private final double quantity;

    public Fuel(double quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(String.format(NEGATIVE_QUANTITY_ERROR_MSG_FORMAT, quantity));
        }
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return Double.compare(fuel.quantity, quantity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
