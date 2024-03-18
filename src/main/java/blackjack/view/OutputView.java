package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gameresult.GameResult;
import blackjack.domain.gameresult.Profit;
import blackjack.domain.hands.HandsScore;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String FINAL_HANDS_AND_SCORE_FORMAT = "%s - 결과: %d";
    private static final String FINAL_RESULT_FORMAT = "%s: %.0f" + System.lineSeparator();
    private static final String FINAL_RESULT_MESSAGE = System.lineSeparator() + "## 최종 수익";

    private OutputView() {
    }

    public static void printAskNameMessage() {
        System.out.println("게임에 참여할 사람 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void printDrawInitialHandsMessage(List<Player> players) {
        System.out.println(System.lineSeparator()
                + "딜러와 "
                + resolvePlayerNamesMessage(players)
                + "에게 2장을 나누었습니다");
    }

    public static void printParticipantsInitialHands(Card dealerFirstCard, List<Player> players) {
        printDealerFirstCard(resolveCardMessage(dealerFirstCard));
        for (Player player : players) {
            printParticipantHands(player.getName(), player.getHandsCards());
        }
        System.out.println();
    }

    public static void printParticipantHands(String participantName, List<Card> participantHands) {
        System.out.println(resolveParticipantHandsMessage(participantName, participantHands));
    }

    public static void printDealerDrawMessage() {
        System.out.println();
        System.out.println("딜러는 "
                + Dealer.getDrawThresHoldScore() + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalHandsAndScoreMessage(Dealer dealer, Players players) {
        System.out.println(resolveFinalHandsAndScoreMessage("딜러",
                dealer.getHandsCards(), dealer.getHandsScore()));

        for (Player player : players.getPlayers()) {
            System.out.println(resolveFinalHandsAndScoreMessage(player.getName(),
                    player.getHandsCards(), player.getHandsScore()));
        }
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerProfitResult(gameResult);
        printPlayerProfitResult(gameResult.getProfitResult());
    }

    private static String resolvePlayerNamesMessage(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
    }

    private static void printDealerFirstCard(String dealerFirstCardName) {
        System.out.println("딜러: " + dealerFirstCardName);
    }

    private static String resolveFinalHandsAndScoreMessage(String participantName
            , List<Card> participantHands, HandsScore handsScore) {
        return String.format(FINAL_HANDS_AND_SCORE_FORMAT,
                resolveParticipantHandsMessage(participantName, participantHands),
                handsScore.toInt());
    }

    private static void printDealerProfitResult(GameResult gameResult) {
        System.out.printf(FINAL_RESULT_FORMAT, "딜러", gameResult.getDealerProfit());
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
        return CardNumberView.findValueName(card.getValue())
                + CardKindView.findKindName(card.getKind());
    }
}
