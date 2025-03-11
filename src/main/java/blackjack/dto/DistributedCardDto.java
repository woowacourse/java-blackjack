package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public record DistributedCardDto(String name, List<String> cardInfos) {

    public static DistributedCardDto from(final Participant participant) {
        String name = participant.getName().trim();
        List<String> cardInfos = participant.showStartCards().stream()
                .map(card -> card.getRank().getName() + card.getSuit().getName())
                .toList();

        return new DistributedCardDto(name, cardInfos);
    }

    public static List<DistributedCardDto> fromPlayers(final List<Player> scores) {
        return scores.stream().map(DistributedCardDto::from).toList();
    }
}
