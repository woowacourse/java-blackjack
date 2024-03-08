package cardGame.dto;

import card.Cards;
import dealer.Dealer;
import player.Players;
import player.dto.AllPlayerStatusDto;

public record BlackJackGameStatusDto(AllPlayerStatusDto playerCardStatus, Cards dealerCards) {

    public static BlackJackGameStatusDto of(Players players, Dealer dealer) {
        AllPlayerStatusDto playersCardStatus = AllPlayerStatusDto.of(players);

        return new BlackJackGameStatusDto(playersCardStatus, dealer.getCards());
    }
}