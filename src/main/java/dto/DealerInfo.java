package dto;

import domain.card.Card;
import domain.participant.Dealer;

import java.util.ArrayList;
import java.util.List;

public record DealerInfo(String name, List<CardInfo> cardInfos) {
    public static DealerInfo from(final Dealer dealer) {
        final List<CardInfo> cardInfos = new ArrayList<>();

        for (final Card card : dealer.hand()) {
            cardInfos.add(new CardInfo(card.denomination().name(), card.suit().name()));
        }

        return new DealerInfo(dealer.name(), cardInfos);
    }
}
