package blackjack.view.output;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerView {
    private PlayerView() {
    }

    public static void printPlayers(final Dealer dealer, final List<GamePlayer> gamePlayers) {
        printPlayersPreview(dealer, gamePlayers);
        printDealerCard(dealer);
        printGamePlayersCard(gamePlayers);
    }

    public static void printDealerDrawMessage() {
        System.out.printf("딜러는 %d 이하라 한장의 카드를 더 받았습니다.%n", Dealer.RECEIVE_SIZE);
    }

    public static void printDealerNotDrawMessage() {
        System.out.printf("딜러는 %d를 초과하므로 카드를 받지 않았습니다.%n", Dealer.RECEIVE_SIZE);
    }


    private static void printPlayersPreview(final Dealer dealer, final List<GamePlayer> gamePlayers) {
        final String result = gamePlayers.stream()
                                         .map(GamePlayer::getNameAsString)
                                         .collect(Collectors.joining(","));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getNameAsString(), result);
    }

    private static void printDealerCard(final Dealer dealer) {
        final String result = CardPrinter.printCard(dealer.getFirstCard());
        System.out.printf("%s: %s%n", dealer.getNameAsString(), result);
    }

    private static void printGamePlayersCard(final List<GamePlayer> gamePlayers) {
        gamePlayers.forEach(PlayerView::printGamePlayer);
    }

    public static void printGamePlayer(final GamePlayer gamePlayer) {
        final String result = CardPrinter.printCards(gamePlayer.getCards());
        System.out.printf("%s카드: %s%n", gamePlayer.getNameAsString(), result);
    }


    public static void printPlayersWithScore(final Dealer dealer, final List<GamePlayer> gamePlayers) {
        printPlayerWithScore(dealer);
        gamePlayers.forEach(PlayerView::printPlayerWithScore);
    }

    private static void printPlayerWithScore(final Participant participant) {
        final String result = CardPrinter.printCards(participant.getCards());
        System.out.printf("%s 카드: %s - 결과 : %d%n", participant.getNameAsString(), result,
                participant.calculateScore());
    }

}
