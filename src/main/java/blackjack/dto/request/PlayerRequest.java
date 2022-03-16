package blackjack.dto.request;

public class PlayerRequest {

    private final String name;
    private final int bettingMoney;

    public PlayerRequest(String name, int bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
