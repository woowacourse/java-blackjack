package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.Set;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.player.BetMoney;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResponse;
import blackjack.domain.result.Match;
import blackjack.domain.result.PlayersBetMoney;
import blackjack.domain.result.Profits;

public class OutputView {

    private static final String RESULT_START_DELIMITER = ": ";
    private static final String CARD_FORMAT = " 카드";
    private static final String RESULT_DELIMITER = ", ";
    private static final String DEALER = "딜러";
    private static final String SHARE_TWO_CARDS_GUIDE_MESSAGE = "에게 2장의 카드를 각각 나누었습니다.";
    private static final String DEALER_MORE_CARD_GUIDE_MESSAGE = "딜러는 16이하라 카드를 더 받았습니다.";
    private static final String FINAL_WINNER_PROFIT_GUIDE_MESSAGE = "## 최종 수익";
    private static final String FINAL_POINT_GUIDE_MESSAGE = " - 결과";
    private static final char NEW_LINE = '\n';
    private static final char FINAL_MATCH_DELIMITER = ' ';
    private static final int DELETE_FINAL_DELIMITER = 2;

    private OutputView() {
    }

    public static void announceStartGame(List<String> playerNames) {
        System.out.println(NEW_LINE + String.join(RESULT_DELIMITER, playerNames) + SHARE_TWO_CARDS_GUIDE_MESSAGE);
    }

    public static void announcePresentCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            StringBuilder sb = new StringBuilder();
            sb.append(gameResponse.getName())
                    .append(CARD_FORMAT)
                    .append(RESULT_START_DELIMITER);
            separatePlayerForPresentCards(gameResponse, sb);
        }
        System.out.println();
    }

    private static void separatePlayerForPresentCards(GameResponse gameResponse, StringBuilder sb) {
        if (gameResponse.isDealer(DEALER)) {
            announcePresentDealerCards(gameResponse, sb);
            return;
        }
        announcePresentGuestCards(gameResponse, sb);
    }

    private static void announcePresentGuestCards(GameResponse gameResponse, StringBuilder sb) {
        for (PlayingCard playingCard : gameResponse.getDeck().getCards()) {
            sb.append(playingCard.getDenomination().getAlias())
                    .append(playingCard.getSuit().getName())
                    .append(RESULT_DELIMITER);
        }
        sb.deleteCharAt(sb.length() - DELETE_FINAL_DELIMITER);
        System.out.println(sb);
    }

    private static void announcePresentDealerCards(GameResponse gameResponse, StringBuilder sb) {
        for (PlayingCard playingCard : gameResponse.getDeck().getCards()) {
            sb.append(playingCard.getDenomination().getAlias())
                    .append(playingCard.getSuit().getName())
                    .append(RESULT_DELIMITER);
            break;
        }
        sb.deleteCharAt(sb.length() - DELETE_FINAL_DELIMITER);
        System.out.println(sb);
    }

    public static void announceHit() {
        System.out.println();
        System.out.println(DEALER_MORE_CARD_GUIDE_MESSAGE);
    }

    public static void announceResultCards(List<GameResponse> gameResponses) {
        System.out.println();
        for (GameResponse gameResponse : gameResponses) {
            StringBuilder sb = new StringBuilder();
            accumulateCardResults(gameResponse, sb);
            sb.append(FINAL_POINT_GUIDE_MESSAGE + RESULT_START_DELIMITER)
                    .append(gameResponse.getDeck().calculatePoints());
            System.out.println(sb);
        }
    }

    private static void accumulateCardResults(GameResponse gameResponse, StringBuilder sb) {
        sb.append(gameResponse.getName())
                .append(CARD_FORMAT)
                .append(RESULT_START_DELIMITER);
        for (PlayingCard playingCard : gameResponse.getDeck().getCards()) {
            sb.append(playingCard.getDenomination().getAlias())
                    .append(playingCard.getSuit().getName())
                    .append(RESULT_DELIMITER);
        }
        sb.deleteCharAt(sb.length() - DELETE_FINAL_DELIMITER);
    }

    public static void announceResultProfit(Profits profits) {
        System.out.println(NEW_LINE + FINAL_WINNER_PROFIT_GUIDE_MESSAGE);
        PlayersBetMoney playersMoney = profits.getPlayersBetMoney();
        Map<Player, BetMoney> profit = playersMoney.getPlayersMoney();
        Set<Player> profitKeys = profit.keySet();
        for (Player player : profitKeys) {
            System.out.println(player.getName() + RESULT_START_DELIMITER + profit.get(player).getMoney());
        }
    }

    private static String accumulateMatchResult(Map<Match, Integer> matchResult) {
        StringBuilder sb = new StringBuilder();
        for (Match match : matchResult.keySet()) {
            sb.append(matchResult.get(match))
                    .append(match.getResult())
                    .append(FINAL_MATCH_DELIMITER);
        }
        return sb.toString();
    }
}
