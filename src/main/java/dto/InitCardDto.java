package dto;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.ArrayList;
import java.util.List;

public record InitCardDto(List<String> playerNames, List<String> playersCard, List<String> dealerCard) {

    public static InitCardDto makeInitCard(Players players, Dealer dealer) {
        List<String> playerNames = new ArrayList<>();
        List<String> playerCard = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playerNames.add(player.getName().getValue());
            String playerCards = changeFormat(player.getCardStatus());
            playerCard.add(playerCards);
        }

        return new InitCardDto(playerNames, playerCard, dealer.getCardStatus());
    }

    private static String changeFormat(List<String> cards) {
        return String.join(",", cards);
    }

}
