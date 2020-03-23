package dto;

public class RequestPlayerDTO {
    private String name;
    private int bettingMoney;

    public RequestPlayerDTO(String name, int bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return this.name;
    }

    public int getBettingMoney() {
        return this.bettingMoney;
    }
}
