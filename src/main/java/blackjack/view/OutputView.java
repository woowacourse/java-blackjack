package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gameresult.GameResult;
import blackjack.domain.gameresult.Profit;
import blackjack.domain.hands.HandsScore;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String FINAL_HANDS_AND_SCORE_FORMAT = "%s - 결과: %d";
    private static final String FINAL_RESULT_FORMAT = "%s: %.0f" + System.lineSeparator();
    private static final String FINAL_RESULT_MESSAGE = System.lineSeparator() + "## 최종 수익";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private OutputView() {
    }

    public static void printAskNameMessage() {
        System.out.println("게임에 참여할 사람 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void printDrawInitialHandsMessage(String dealerName, List<Player> players) {
        System.out.println(System.lineSeparator()
                + dealerName
                + "와 "
                + resolvePlayerNamesMessage(players)
                + "에게 2장을 나누었습니다");
    }

    public static void printParticipantsInitialHands(String dealerName, Card dealerFirstCard, List<Player> players) {
        printDealerFirstCard(dealerName, resolveCardMessage(dealerFirstCard));
        for (Player player : players) {
            printParticipantHands(player.getName(), player.getHandsCards());
        }
        System.out.println();
    }

    public static void printParticipantHands(String participantName, List<Card> participantHands) {
        System.out.println(resolveParticipantHandsMessage(participantName, participantHands));
    }

    public static void printDealerDrawMessage(Dealer dealer) {
        System.out.println();
        System.out.println(dealer.getName() + "는 "
                + DEALER_DRAW_THRESHOLD + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalHandsAndScoreMessage(Dealer dealer, Players players) {
        System.out.println(resolveFinalHandsAndScoreMessage(dealer.getName(),
                dealer.getHandsCards(), dealer.getHandsScore()));

        for (Player player : players.getPlayers()) {
            System.out.println(resolveFinalHandsAndScoreMessage(player.getName(),
                    player.getHandsCards(), player.getHandsScore()));
        }
    }

    public static void printGameResult(String dealerName, GameResult gameResult) {
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerProfitResult(dealerName, gameResult);
        printPlayerProfitResult(gameResult.getProfitResult());
    }

    private static String resolvePlayerNamesMessage(List<Player> players) {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(","));
    }

    private static void printDealerFirstCard(String dealerName, String dealerFirstCardName) {
        System.out.println(dealerName
                + ": "
                + dealerFirstCardName);
    }

    // TODO 배열 convention과 유사하게 comma를 line 마지막에 작성
    private static String resolveFinalHandsAndScoreMessage(String participantName
            , List<Card> participantHands, HandsScore handsScore) {
        return String.format(FINAL_HANDS_AND_SCORE_FORMAT
                , resolveParticipantHandsMessage(participantName, participantHands)
                , handsScore.toInt());
    }
    // TODO -1을 view에서 해준 이유 생각하기
    private static void printDealerProfitResult(String dealerName, GameResult gameResult) {
        Double dealerProfit = -1 * gameResult.getSumOfProfit();

        System.out.printf(FINAL_RESULT_FORMAT, dealerName, dealerProfit);
    }

    private static void printPlayerProfitResult(Map<Player, Profit> gameResult) {
        for (Map.Entry<Player, Profit> playerAndBatting : gameResult.entrySet()) {
            Player player = playerAndBatting.getKey();
            Profit profit = playerAndBatting.getValue();
            System.out.printf(FINAL_RESULT_FORMAT, player.getName()
                    , profit.getProfit());
        }
    }

    private static String resolveParticipantHandsMessage(String participantName, List<Card> participantHands) {
        return participantName
                + "카드: "
                + resolveHandsMessage(participantHands);
    }

    private static String resolveHandsMessage(List<Card> hands) {
        return hands.stream()
                .map(OutputView::resolveCardMessage)
                .collect(Collectors.joining(", "));
    }

    private static String resolveCardMessage(Card card) {
        return ValueView.findValueName(card.getValue())
                + KindView.findKindName(card.getKind());
    }
}
