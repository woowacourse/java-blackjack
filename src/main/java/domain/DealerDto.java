package domain;

import domain.user.Dealer;

public class DealerDto {
    public final String name;
    public final String visibleCard;

    private DealerDto(String name, String visibleCard) {
        this.name = name;
        this.visibleCard = visibleCard;
    }

    public static DealerDto from(Dealer dealer) {
        return new DealerDto(dealer.getName().value(), dealer.getVisibleCard().getName());
    }
}
