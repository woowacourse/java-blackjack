package blackjack.domain.player;

public class Information {

    private final String name;
    private final int batMoney;

    public Information(String name, int batMoney) {
        this.name = name;
        this.batMoney = batMoney;
    }

    public String name() {
        return name;
    }

    public int batMoney() {
        return batMoney;
    }
}
