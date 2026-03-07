package view;

import domain.GameResult;
import domain.GameStatistics;
import domain.Players;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();

    public static void showIntroMessage(Players players) {
        System.out.println(NEW_LINE + "딜러와 "
                + String.join(DELIMITER, String.join(DELIMITER, players.getParticipantNames())
                + "에게 2장을 나누었습니다."));
    }

    public static void showDealerCardName(Dealer dealer) {
        List<String> cardNames = createCardNames(dealer);
        List<String> dealerCard = new ArrayList<>(cardNames);
        dealerCard.removeFirst();
        System.out.println(getCardNames(dealer, dealerCard));
    }

    public static void showPlayerCardName(Players players) {
        players.getPlayers()
                .forEach(OutputView::showCardName);
    }

    public static void showCardName(Participant participant) {
        List<String> playerCardNames = createCardNames(participant);
        System.out.println(getCardNames(participant, playerCardNames));
    }

    private static String getCardNames(Participant participant, List<String> playerCardNames) {
        return participant.getName() + "카드: " + String.join(DELIMITER, playerCardNames);
    }

    private static List<String> createCardNames(Participant participant) {
        return participant.getCards().stream()
                .map(Card::getCardName)
                .toList();
    }

    public static void showDealerMessage() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showResult(Dealer dealer, Players players) {
        System.out.println();

        showCardAndScore(dealer, dealer.getScore());
        players.getPlayers()
                .forEach(participant -> showCardAndScore(participant, participant.getScore()));
    }

    private static void showCardAndScore(Participant participant, int score) {
        List<String> dealerCardNames = createCardNames(participant);
        System.out.println(getCardNames(participant, dealerCardNames) + " - 결과: " + score);
    }

    public static void showGameResult(GameStatistics statistics) {
        System.out.println(NEW_LINE + "## 최종 승패");
        showDealerResult(statistics);
        System.out.println();

        showPlayerResult(statistics);
    }

    private static void showDealerResult(GameStatistics statistics) {
        System.out.print("딜러: ");
        for (Map.Entry<GameResult, Integer> entry : statistics.getDealerResult().entrySet()) {
            System.out.print(entry.getValue() + entry.getKey().getDescription() + " ");
        }
    }

    private static void showPlayerResult(GameStatistics statistics) {
        Map<Participant, GameResult> playerResult = statistics.getPlayerResult();
        for (Map.Entry<Participant, GameResult> entry : playerResult.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue().getDescription());
        }
    }
}
