package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.List;

public record InitialDealDto(List<PlayerCardsDto> playerCards, String dealerFirstCard) {

    public InitialDealDto(Players players, Dealer dealer) {
        this(players.players().stream()
                        .map(PlayerCardsDto::new)
                        .toList(),
                dealer.getFirstCard());
    }
}
