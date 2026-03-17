package blackjack.view;

import blackjack.domain.Participants;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
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
        participants.forEachParticipant(participant ->
                System.out.println(getCardNames(participant.getName(), participant.getInitialCards()))
        );
    }

    private static String getCardNames(String name, List<Card> cards) {
        List<String> playerCardNames = cards.stream()
                .map(Card::getCardName)
                .toList();
        return name + "카드: " + String.join(DELIMITER, playerCardNames);
    }

    public static void showCardName(Participant participant) {
        System.out.println(getCardNames(participant.getName(), participant.getCards()));
    }

    public static void showDealerMessage() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showResult(Participants participants) {
        System.out.println();
        participants.forEachParticipant(participant ->
                showCardAndScore(participant, participant.getScore().getScore()));
    }

    private static void showCardAndScore(Participant participant, int score) {
        System.out.println(getCardNames(participant.getName(), participant.getCards()) + " - 결과: " + score);
    }

    public static void showProfitRate(long dealerProfitRate, Map<Player, Long> statistics) {
        System.out.println(NEW_LINE + "## 최종 수익");
        showDealerResult(dealerProfitRate);
        showPlayerResult(statistics);
    }

    private static void showDealerResult(long dealerResult) {
        System.out.println("딜러: " + dealerResult);
    }

    private static void showPlayerResult(Map<Player, Long> playerResult) {
        for (Map.Entry<Player, Long> entry : playerResult.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }
}
