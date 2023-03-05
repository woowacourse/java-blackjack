package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;

import java.util.List;

public class DealerStatusWithPointDto extends DealerStatusDto {

    private final int point;

    private DealerStatusWithPointDto(String name, List<String> cards, int point) {
        super(name, cards);
        this.point = point;
    }

    public static DealerStatusWithPointDto from(Player dealer) {
        String name = dealer.getName();
        List<Card> holdingCards = dealer.getHoldingCards().getCards();
        List<String> cardInfos = DealerStatusDto.extractCardInfo(holdingCards);
        int point = dealer.getTotalPoint();
        return new DealerStatusWithPointDto(name, cardInfos, point);
    }

    public int getPoint() {
        return point;
    }
}
