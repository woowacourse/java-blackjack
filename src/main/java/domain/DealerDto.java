package domain;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Name;

public class DealerDto {
    public final Name name;
    public final Card visibleCard;

    private DealerDto(Name name, Card visibleCard) {
        this.name = name;
        this.visibleCard = visibleCard;
    }

    public static DealerDto from(Dealer dealer) {
        return new DealerDto(dealer.getName(), dealer.getVisibleCard());
    }
}
