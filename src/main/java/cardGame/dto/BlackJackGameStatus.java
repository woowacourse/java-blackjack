package cardGame.dto;

import card.Cards;
import dealer.Dealer;
import player.Players;
import player.dto.PlayersCardStatusDto;

public record BlackJackGameStatus(PlayersCardStatusDto playerCardStatus, Cards dealerCards) {

    public static BlackJackGameStatus of(Players players, Dealer dealer) {
        PlayersCardStatusDto playersCardStatus = PlayersCardStatusDto.of(players);

        return new BlackJackGameStatus(playersCardStatus, dealer.getCards());
    }
}