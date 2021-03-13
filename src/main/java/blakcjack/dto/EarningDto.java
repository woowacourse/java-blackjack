package blakcjack.dto;

import blakcjack.domain.participant.Participant;

public class EarningDto {
    private final String name;
    private final int money;

    private EarningDto(final String name, final int money) {
        this.name = name;
        this.money = money;
    }

    public static EarningDto of(final Participant participant) {
        return new EarningDto(participant.getNameValue(), participant.getMoneyValue());
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
