package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResultDto;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.PlayerResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printPlayersHandStatus(Dealer dealer, List<Player> players) {
        System.out.printf("\n딜러와 %s에게 2장의 카드를 나누었습니다.", getPlayerNames(players));

        printOpenCard(dealer);
        for (Player player : players) {
            printHand(player);
        }
        System.out.println();
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private static void printOpenCard(Dealer dealer) {
        printHand(dealer.getName(), cardToString(dealer.getOpenCard()));
    }

    public static void printHand(Participant participant) {
        printHand(participant.getName(), cardsToString(participant.getCards()));
    }

    private static void printHand(String name, String cards) {
        System.out.printf("%s 카드: %s\n", name, cards);
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printGameResult(GameResultDto result) {
        printCardResult("딜러", cardsToString(result.getDealerCards()), result.getDealerSum());

        for (PlayerResultDto playerResult : result.getPlayersResults()) {
            printPlayerCardResult(playerResult);
        }

        System.out.println("\n## 최종 승패");
        printDealerResult(result.getDealerResult());
        for (PlayerResultDto playersResult : result.getPlayersResults()) {
            System.out.printf("%s: %s\n", playersResult.getName(), playersResult.getWinOrLose());
        }
    }

    private static void printPlayerCardResult(PlayerResultDto playerResult) {
        printCardResult(
                playerResult.getName(),
                cardsToString(playerResult.getCards()),
                playerResult.getSum());
    }

    private static void printCardResult(String name, String cards, int result) {
        System.out.printf("%s 카드: %s - 결과: %d\n", name, cards, result);
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
    }

    public static String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(OutputView::cardToString)
                .collect(Collectors.joining(", "));
    }

    public static String cardToString(Card card) {
        return card.getRankInitial() + card.getSuitName();
    }
}
