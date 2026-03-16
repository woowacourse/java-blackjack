package dto.response;

import domain.Money;

public record PayoutResponse(String name, long amount) {
    public static PayoutResponse from(String name, Money money) {
        return new PayoutResponse(name, money.amount());
    }
}
