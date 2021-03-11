package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Result;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_MARK = "[Error] ";
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String DISTRIBUTE_MESSAGE = "%s와 %s에게 2장의 카드를 나누어주었습니다.";
    private static final String DEALER_CARD_STATUS_FORMAT = "%s: %s";
    private static final String PLAYER_CARD_STATUS_FORMAT = "%s카드: %s";
    private static final String CARD_RESULT_FORMAT = "%s카드: %s - 결과: %d";
    private static final String BUST_MESSAGE = "카드의 합이 21을 넘어, 게임에서 패배하였습니다.";
    private static final String DEALER_MORE_CARD_MESSAGE = "%s는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_GAME_RESULT_FORMAT = "%s: %d승 %d무 %d패";
    private static final String GAME_PROFIT_MESSAGE = "## 최종 수익";

    private OutputView() {
    }

    public static void showErrorMessage(final String message) {
        System.out.println(ERROR_MARK + message);
    }

    public static void showNewLine() {
        System.out.print(NEWLINE);
    }

    public static void showDistributionMessage(final Dealer dealer, final Players players) {
        final String playerNames = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        showNewLine();
        System.out.printf(DISTRIBUTE_MESSAGE, dealer.getName(), playerNames);
        showNewLine();
    }

    public static void showDealerCard(final Dealer dealer) {
        final String name = dealer.getName();
        final Card firstCard = dealer.getHand().getCards().get(0);
        System.out.printf(DEALER_CARD_STATUS_FORMAT, name, cardPrintFormat(firstCard));
        showNewLine();
    }

    public static void showEveryPlayerCard(final Players players) {
        players.getPlayers()
                .forEach(OutputView::showPlayerCard);
    }

    public static void showCardResult(final Participant participant) {
        final String name = participant.getName();
        final String cardStatus = cardStatusFormat(participant);
        final int result = participant.getHand().calculateScore();
        System.out.printf(CARD_RESULT_FORMAT, name, cardStatus, result);
        showNewLine();
    }

    public static void showEveryPlayerCardResult(final Players players) {
        players.getPlayers()
                .forEach(OutputView::showCardResult);
    }

    public static void showPlayerCard(final Player player) {
        final String name = player.getName();
        final String cardStatus = cardStatusFormat(player);
        System.out.printf(PLAYER_CARD_STATUS_FORMAT, name, cardStatus);
        showNewLine();
    }

    private static String cardStatusFormat(final Participant participant) {
        return participant.getHand().getCards()
                .stream()
                .map(OutputView::cardPrintFormat)
                .collect(Collectors.joining(", "));
    }

    private static String cardPrintFormat(final Card card) {
        return card.getCardLetter().getLetter() + card.getCardSuit().getType();
    }

    public static void showDealerGameResult(final Dealer dealer, final Map<Result, Integer> dealerResult) {
        showNewLine();
        System.out.println(GAME_RESULT_MESSAGE);
        System.out.printf(DEALER_GAME_RESULT_FORMAT, dealer.getName(),
                dealerResult.getOrDefault(Result.WIN, 0),
                dealerResult.getOrDefault(Result.DRAW, 0),
                dealerResult.getOrDefault(Result.LOSE, 0));
        showNewLine();
    }

    public static void showPlayerGameResult(final Map<Player, Result> playerResult) {
        for (Player player : playerResult.keySet()) {
            System.out.println(player.getName() + ": " + playerResult.get(player).getResult());
        }
    }

    public static void showBustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public static void showDealerGotMoreCard(final Dealer dealer) {
        showNewLine();
        System.out.printf(DEALER_MORE_CARD_MESSAGE, dealer.getName());
        showNewLine();
    }

    public static void showDealerProfit(final Dealer dealer) {
        showNewLine();
        System.out.println(GAME_PROFIT_MESSAGE);
        System.out.println(dealer.getName() + ": " + dealer.getDealerMoney().getMoney());
    }

    public static void showPlayerProfit(final Map<Player, Double> playerProfit) {
        for (Player player : playerProfit.keySet()) {
            System.out.println(player.getName() + ": " + playerProfit.get(player));
        }
    }
}
