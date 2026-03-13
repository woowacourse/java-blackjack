package mapper;

import domain.participant.Dealer;
import dto.DealerDto;

public class DealerMapper {

    public static DealerDto toDto(Dealer dealer) {
        return new DealerDto(
                dealer.getFirstCard(),
                dealer.getCards(),
                dealer.getScore()
        );
    }
}