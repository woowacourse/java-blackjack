package dto.hands;

import domain.participant.Dealer;
import java.util.List;
import view.message.RankView;
import view.message.ShapeView;

public record DealerHandsDto(String name, String card) {

    public static DealerHandsDto from(final Dealer dealer) {
        final List<String> cards = dealer.getCards().stream()
                .map(card -> RankView.from(card.getRank()) + ShapeView.from(card.getShape()))
                .toList();

        return new DealerHandsDto(dealer.getName(), cards.get(0));
    }

    public String getCard() {
        return card;
    }
}
