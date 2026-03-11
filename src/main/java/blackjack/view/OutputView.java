package blackjack.view;

import blackjack.domain.Participants;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;
import java.util.Map;

public final class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();

    public static void showCardNames(Participants participants) {
        showIntroMessage(participants);
        showCards(participants);
    }

    private static void showIntroMessage(Participants participants) {
        System.out.println(NEW_LINE + participants.getDealerName() + "와 "
                + (String.join(DELIMITER, participants.getPlayerNames())
                + "에게 2장을 나누었습니다."));
    }

    private static void showCards(Participants participants) {
        participants.getParticipants().stream()
                .map(OutputView::getCardNames)
                .forEach(System.out::println);
    }

    private static String getCardNames(Participant participant) {
        List<String> playerCardNames = createCardNames(participant);
        return participant.getName() + "카드: " + String.join(DELIMITER, playerCardNames);
    }

    public static void showCardName(Participant participant) {
        System.out.println(getCardNames(participant));
    }

    private static List<String> createCardNames(Participant participant) {
        return participant.getCards().stream()
                .map(Card::getCardName)
                .toList();
    }

    public static void showDealerMessage() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showResult(Participants participants) {
        System.out.println();
        participants.getParticipants()
                .forEach(participant -> showCardAndScore(participant, participant.getScore().getScore()));
    }

    private static void showCardAndScore(Participant participant, int score) {
        System.out.println(getCardNames(participant) + " - 결과: " + score);
    }

    public static void showProfitRate(int dealerProfitRate, Map<Participant, Integer> statistics) {
        System.out.println(NEW_LINE + "## 최종 수익");
        showDealerResult(dealerProfitRate);
        showPlayerResult(statistics);
    }

    private static void showDealerResult(int dealerResult) {
        System.out.println("딜러: " + dealerResult);
    }

    private static void showPlayerResult(Map<Participant, Integer> playerResult) {
        for (Map.Entry<Participant, Integer> entry : playerResult.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }
}
