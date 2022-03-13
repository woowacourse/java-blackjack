package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.dto.WinDrawLoseDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class OutputView {

    public static final String DRAW_CARD_MESSAGE = "%s와 %s에게 2장을 나누었습니다.\n";
    private static final String CARD_INFORMATION_MESSAGE = "%s카드: %s";
    private static final String TOTAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_DRAW_LOSE_STATUS_MESSAGE = "%s: %s";
    private static final String DRAW_RESULT_MESSAGE = "%s - 결과: %d";

    public static void printInitCard(Dealer dealer, Players players) {
        System.out.printf(DRAW_CARD_MESSAGE, dealer.getName(), printNames(players));
        System.out.println(printDealerCard(dealer));
        System.out.println(printPlayersCard(players));
        System.out.println();
    }

    private static String printNames(Players players) {
        return players.getPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.joining(","));
    }

    private static String printDealerCard(Dealer dealer) {
        return String.format(CARD_INFORMATION_MESSAGE, dealer.getName(),
            cardToString(dealer.getCards().getFirstCard()));
    }

    private static String printPlayersCard(Players players) {
        return players.getPlayers().stream()
            .map(OutputView::printCard)
            .collect(Collectors.joining("\n"));
    }

    private static String cardsToString(Cards cards) {
        return cards.getCards().stream()
            .map(OutputView::cardToString)
            .collect(Collectors.joining(", "));
    }

    private static String cardToString(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static String printCard(Gamer gamer) {
        return String.format(CARD_INFORMATION_MESSAGE, gamer.getName(), cardsToString(gamer.getCards()));
    }

    public static void printDealerDrawCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDrawResult(Dealer dealer, Players players) {
        System.out.println();
        System.out.println(printDrawCard(dealer));
        System.out.println(printPlayersDrawCard(players));
    }

    private static String printPlayersDrawCard(Players players) {
        return players.getPlayers().stream()
            .map(OutputView::printDrawCard)
            .collect(Collectors.joining("\n"));
    }

    private static String printDrawCard(Gamer gamer) {
        return String.format(DRAW_RESULT_MESSAGE, printCard(gamer), gamer.calculateScore());
    }

    public static void printResult(List<WinDrawLoseDto> winDrawLoseDtos) {
        System.out.println(TOTAL_RESULT_MESSAGE);
        winDrawLoseDtos.forEach(
            winDrawLoseDto -> System.out.printf(WIN_DRAW_LOSE_STATUS_MESSAGE, winDrawLoseDto.getName(),
                winDrawLoseDto.getWinDrawLoseString()));
    }
}
