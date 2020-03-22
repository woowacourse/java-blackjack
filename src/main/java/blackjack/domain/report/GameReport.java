package blackjack.domain.report;

public class GameReport {
    private final String name;
    private final double money;

    public GameReport(String name, double earnMoney) {
        validate(name);
        this.name = name;
        this.money = earnMoney;
    }

    private void validate(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public Double getMoney() {
        return this.money;
    }

}
