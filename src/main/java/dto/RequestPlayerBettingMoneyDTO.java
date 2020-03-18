package dto;

public class RequestPlayerBettingMoneyDTO {
    private int bettingMoney;

    public RequestPlayerBettingMoneyDTO(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public int getBettingMoney() {
        return this.bettingMoney;
    }
}
