package domain;

public class DealerCards extends Cards {
    public boolean isOverSixteen() {
        return getSum() > 16;
    }
}
