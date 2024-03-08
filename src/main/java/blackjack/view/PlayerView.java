package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerView {

    public static void printPlayers(Dealer dealer, List<GamePlayer> gamePlayers) {
        printPlayersPreview(dealer, gamePlayers);
        printDealerCard(dealer);
        printGamePlayersCard(gamePlayers);
    }

    private static void printPlayersPreview(Dealer dealer, List<GamePlayer> gamePlayers) {
        String result = gamePlayers.stream()
                                   .map(GamePlayer::getName)
                                   .collect(Collectors.joining(","));
        System.out.println(
                String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), result));
    }

    private static void printDealerCard(Dealer dealer) {
        String result = CardPrinter.printCard(dealer.getFirstCard());
        System.out.println(String.format("%s: %s", dealer.getName(), result));
    }

    private static void printGamePlayersCard(List<GamePlayer> gamePlayers) {
        gamePlayers.forEach(PlayerView::printGamePlayer);
    }

    public static void printGamePlayer(GamePlayer gamePlayer) {
        String result = CardPrinter.printCards(gamePlayer.getCards());
        System.out.println(String.format("%s카드: %s", gamePlayer.getName(), result));
    }


    public static void printPlayersWithScore(Players players) {
        printPlayerWithScore(players.getDealer());
        players.getGamePlayers()
               .forEach(PlayerView::printPlayerWithScore);
    }

    private static void printPlayerWithScore(Player player) {
        String result = CardPrinter.printCards(player.getCards());
        System.out.println(String.format("%s 카드: %s - 결과 : %d", player.getName(), result,
                player.calculateScore()));
    }

}
