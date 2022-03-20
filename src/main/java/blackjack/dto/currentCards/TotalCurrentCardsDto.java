package blackjack.dto.currentCards;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TotalCurrentCardsDto {
    private CurrentCardsDto currentCardsOfDealer;
    private List<CurrentCardsDto> currentCardsOfPlayers;

    public TotalCurrentCardsDto(Dealer dealer, Players players) {
        currentCardsOfDealer = CurrentCardsDto.from(dealer);
        currentCardsOfPlayers = players.getPlayers().stream()
                .map(CurrentCardsDto::from)
                .collect(Collectors.toList());
    }

    public CurrentCardsDto getCurrentCardsOfDealer() {
        return currentCardsOfDealer;
    }

    public List<CurrentCardsDto> getCurrentCardsOfPlayers() {
        return Collections.unmodifiableList(currentCardsOfPlayers);
    }
}
