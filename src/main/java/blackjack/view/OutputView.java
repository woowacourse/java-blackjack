package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResultDto;
import blackjack.domain.result.PlayerResultDto;
import blackjack.domain.result.MatchResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printPlayersHandStatus(Dealer dealer, List<Player> players) {
        printNotice(String.format("딜러와 %s에게 2장의 카드를 나누었습니다.", getPlayerNames(players)));

        printDealerBaseHandStatus(dealer);
        for (Player player : players) {
            printHandStatus(player);
        }
        System.out.println();
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(GameResultDto result) {
        printNotice(String.format("딜러 카드: %s - 결과 %d",
                cardsToString(result.getDealerCards()),
                result.getDealerSum()));

        for (PlayerResultDto playerResult : result.getPlayersResults()) {
            printPlayerCardResult(playerResult);
        }

        printNotice("## 최종 승패");
        printDealerResult(result.getDealerResult());
        for (PlayerResultDto playersResult : result.getPlayersResults()) {
            System.out.printf("%s: %s\n", playersResult.getName(), playersResult.getWinOrLose());
        }
    }

    private static void printDealerResult(List<MatchResult> dealerResult) {
        int win = 0;
        int lose = 0;
        int tie = 0;
        for (MatchResult winOrLose : dealerResult) {
            if (MatchResult.WIN.equals(winOrLose)) {
                win++;
            }
            if (MatchResult.LOSE.equals(winOrLose)) {
                lose++;
            }
            if (MatchResult.TIE.equals(winOrLose)) {
                tie++;
            }
        }

        System.out.printf("딜러: %d승 %d패 %d무\n", win, lose, tie);
    }

    public static void printHandStatus(Participant participant) {
        System.out.printf("%s 카드: %s\n",
                participant.getName(),
                cardsToString(participant.getCards()));
    }

    public static void printDealerBaseHandStatus(Dealer dealer) {
        System.out.printf("%s 카드: %s\n",
                dealer.getName(),
                cardToString(dealer.getBaseCard()));
    }

    private static void printPlayerCardResult(PlayerResultDto playerResult) {
        System.out.printf("%s 카드: %s - 결과: %d\n",
                playerResult.getName(),
                cardsToString(playerResult.getCards()),
                playerResult.getSum());
    }

    public static String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(OutputView::cardToString)
                .collect(Collectors.joining(", "));
    }

    public static String cardToString(Card card) {
        return card.getRankInitial() + card.getSuitName();
    }

    private static void printNotice(String notice) {
        System.out.println("\n" + notice);
    }
}
