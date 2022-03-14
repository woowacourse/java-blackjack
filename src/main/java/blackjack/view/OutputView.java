package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.MatchResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String JOIN_DELIMITER = ", ";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    private OutputView() {

    }

    public static void printInitialCards(Dealer dealer, Players players) {
        System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), getPlayerNames(players));
        System.out.printf("%s: %s%n", dealer.getName(), getCardName(dealer.getCards().getValue().get(0)));
        for (Player player : players.getPlayers()) {
            printCards(player);
        }
        System.out.println();
    }

    private static String getPlayerNames(Players players) {
        return players.getPlayers()
                .stream()
                .map(player -> player.getName())
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardNames(Cards cards) {
        return cards.getValue().stream()
                .map(card -> getCardName(card))
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardName(Card card) {
        return card.getDenominationName() + card.getSuitName();
    }

    public static void printCards(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), getCardNames(player.getCards()));
    }

    public static void printDealerDrawInfo() {
        System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DRAW_STANDARD);
    }

    public static void printCardsResult(Dealer dealer, Players players) {
        System.out.println();
        printCardResult(dealer);
        for (Player player : players.getPlayers()) {
            printCardResult(player);
        }
    }

    private static PrintStream printCardResult(Participant participant) {
        return System.out.printf("%s 카드: %s - 결과: %d%n",
                participant.getName(), getCardNames(participant.getCards()), participant.getCards().sum());
    }

    public static void printGameResult(GameResult gameResult) {
        Map<Player, MatchResult> map = gameResult.getGameResult();

        System.out.printf("%n%s: %d승 %d무 %d패%n",
                Dealer.NAME, gameResult.getDealerWinCount(),
                gameResult.getDealerDrawCount(), gameResult.getDealerLoseCount());

        for (Player player : map.keySet()) {
            System.out.printf("%s: %s%n", player.getName(), gameResult.getMatchResult(player).getValue());
        }
    }

    public static void printException(Exception e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());
    }
}
