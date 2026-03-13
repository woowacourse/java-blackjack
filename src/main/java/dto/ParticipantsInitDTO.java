package dto;

import vo.Money;

public class ParticipantsInitDTO {
    private final String userName;
    private final Money bettingMoney;

    public ParticipantsInitDTO(String userName, Money bettingMoney) {
        this.userName = userName;
        this.bettingMoney = bettingMoney;
    }

    public String getUserName() {
        return userName;
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }
}
