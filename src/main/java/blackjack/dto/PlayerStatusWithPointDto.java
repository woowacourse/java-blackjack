package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;

import java.util.List;

public class PlayerStatusWithPointDto extends PlayerStatusDto {

    private final int point;

    private PlayerStatusWithPointDto(final String name, final List<String> cards, final int point) {
        super(name, cards);
        this.point = point;
    }

    public static PlayerStatusWithPointDto from(final Player player) {
        String name = player.getName();
        List<Card> holdingCards = player.getHoldingCards().getCards();
        List<String> cardInfos = PlayerStatusDto.extractCardInfo(holdingCards);
        int point = player.getTotalPoint().getValue();
        return new PlayerStatusWithPointDto(name, cardInfos, point);
    }

    public int getPoint() {
        return point;
    }
}
