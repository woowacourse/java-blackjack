package blackjack.dto;

import blackjack.domain.Participant;
import java.util.List;

public record DistributedCardDto(String name, List<String> cardInfos) {

    public static DistributedCardDto from(final Participant participant) {
        String name = participant.getName().trim();
        List<String> cardInfos = participant.getCardDeck().stream()
                .map(card -> card.getRank().getName() + card.getSuit().getName())
                .toList();

        return new DistributedCardDto(name, cardInfos);
    }
}
