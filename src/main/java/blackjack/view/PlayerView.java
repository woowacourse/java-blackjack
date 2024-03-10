package blackjack.view;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerView {

    public static void printPlayers(final Dealer dealer, final List<GamePlayer> gamePlayers) {
        printPlayersPreview(dealer, gamePlayers);
        printDealerCard(dealer);
        printGamePlayersCard(gamePlayers);
    }

    public static void printDealerDrawMessage() {
        System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", Dealer.RECEIVE_SIZE));
    }

    public static void printDealerNotDrawMessage() {
        System.out.println(String.format("딜러는 %d를 초과하므로 카드를 받지 않았습니다.", Dealer.RECEIVE_SIZE));
    }


    private static void printPlayersPreview(final Dealer dealer, final List<GamePlayer> gamePlayers) {
        final String result = gamePlayers.stream()
                                         .map(GamePlayer::getNameAsString)
                                         .collect(Collectors.joining(","));
        System.out.println(
                String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getNameAsString(), result));
    }

    private static void printDealerCard(final Dealer dealer) {
        final String result = CardPrinter.printCard(dealer.getFirstCard());
        System.out.println(String.format("%s: %s", dealer.getNameAsString(), result));
    }

    private static void printGamePlayersCard(final List<GamePlayer> gamePlayers) {
        gamePlayers.forEach(PlayerView::printGamePlayer);
    }

    public static void printGamePlayer(final GamePlayer gamePlayer) {
        final String result = CardPrinter.printCards(gamePlayer.getCards());
        System.out.println(String.format("%s카드: %s", gamePlayer.getNameAsString(), result));
    }


    public static void printPlayersWithScore(final Players players) {
        printPlayerWithScore(players.dealer());
        players.gamePlayers()
               .forEach(PlayerView::printPlayerWithScore);
    }

    private static void printPlayerWithScore(final Participant participant) {
        final String result = CardPrinter.printCards(participant.getCards());
        System.out.println(String.format("%s 카드: %s - 결과 : %d", participant.getNameAsString(), result,
                participant.calculateScore()));
    }

}
