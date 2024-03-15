package model.dto;

import model.money.Money;
import model.participant.Name;

public record FinalOddsResult(Name name, Money money) {

    public String getParticipantNameAsString() {
        return name.getValue();
    }

    public int getParticipantMoneyAsInteger() {
        return money.getAmount();
    }
}
