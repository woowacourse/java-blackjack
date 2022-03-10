package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.GameResponse;
import blackjack.domain.Match;
import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.domain.Results;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void announceStartGame(List<String> playerNames) {
        System.out.println(String.join(", ", playerNames) + "에게 2장의 카드를 각각 나누었습니다.");
    }

    public static void announcePresentCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            StringBuilder sb = new StringBuilder();
            sb.append(gameResponse.getName()).append(" 카드: ");
            if (gameResponse.getName().equals("딜러")) {
                for (Card card : gameResponse.getDeck().getCards()) {
                    sb.append(card.getRank().getValue()).append(card.getSuit().getName()).append(", ");
                    break;
                }
                sb.deleteCharAt(sb.length() - 2);
                System.out.println(sb);
                continue;
            }
            for (Card card : gameResponse.getDeck().getCards()) {
                sb.append(card.getRank().getValue()).append(card.getSuit().getName()).append(", ");
            }
            sb.deleteCharAt(sb.length() - 2);
            System.out.println(sb);
        }
    }

    public static void announceDealerGetMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void announceDealerStopMoreCard() {
        System.out.println("딜러는 17이상이라 카드를 더 받지 않습니다.");
    }

    public static void announceResultCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            StringBuilder sb = new StringBuilder();
            sb.append(gameResponse.getName()).append(" 카드: ");

            for (Card card : gameResponse.getDeck().getCards()) {
                sb.append(card.getRank().getValue()).append(card.getSuit().getName()).append(", ");
            }
            sb.deleteCharAt(sb.length() - 2);

            sb.append(" - 결과: " + gameResponse.getDeck().sumPoints());
            System.out.println(sb);
        }
    }

    public static void announceResultWinner(Results results) {
        for (Player player : results.getPlayers()) {
            MatchResult result = results.getResult(player);
            String matchResult = toMatchResultFormat(result.getMatch());
            System.out.println(player.getName() + ": " + matchResult);
        }
    }

    private static String toMatchResultFormat(Map<Match, Integer> matchResult) {
        StringBuilder sb = new StringBuilder();
        for (Match match : matchResult.keySet()) {
            sb.append(matchResult.get(match)).append(match.getResult());
        }
        return sb.toString();
    }
}
