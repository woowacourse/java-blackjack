package dto;

import domain.participant.Dealer;
import java.util.List;

public class DealerDto extends ParticipantDto{
    public DealerDto(String name, List<String> cardNames, int totalSum) {
        super(name, cardNames, totalSum);
    }

    public static DealerDto from(Dealer dealer) {
        return new DealerDto(
                dealer.getName(),
                dealer.getCardNames(),
                dealer.getTotalSum()
        );
    }

    public String getFirstCard() {
        return getCards().getFirst();
    }
}
