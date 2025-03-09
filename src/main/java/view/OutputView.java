package view;

import java.util.List;
import java.util.Map;
import model.Deck.Card;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Participants;
import model.result.GameResult;
import model.participant.Player;
import model.result.ParticipantWinningResult;

public final class OutputView {
    private static final String JOIN_DELIMITER = ", ";

    public static void printInitialDealResult(Participants participants) {
        printCardDivisionStart(participants.getPlayers());

        Dealer dealer = participants.getDealer();
        Card firstDealerCard = dealer.getFirstHand();
        System.out.println("딜러카드: " + firstDealerCard.getCardName());

        participants.getPlayers().forEach(OutputView::printDealResultOf);
    }

    public static void printHitOrStandQuestion(final Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDealResultOf(final Participant participant) {
        List<String> cardsName = participant.getHandCards().stream()
                .map(Card::getCardName)
                .toList();
        System.out.println(getParticipantName(participant) + "카드: " + String.join(JOIN_DELIMITER, cardsName));
    }

    public static void printDealerDealResult() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalScores(final Participants participants) {
        Dealer dealer = participants.getDealer();
        printScoreOf(dealer);
        participants.getPlayers().forEach(participant -> {
            printDealResultOf(participant);
            printScoreOf(participant);
        });
    }

    public static void printDealerFinalResult(final Map<GameResult, Integer> dealerWinning) {
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + getGameResultMessage(dealerWinning));
    }

    public static void printPlayerFinalResult(final ParticipantWinningResult participantWinningResult) {
        Map<Player, GameResult> playerResults = participantWinningResult.getResult();
        for (Player player : playerResults.keySet()) {
            GameResult playerResult = participantWinningResult.getResult().get(player);
            System.out.println(player.getName() + ": " + playerResult.getResultMeaning());
        }
    }

    private static void printCardDivisionStart(final List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .toList();
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", String.join(JOIN_DELIMITER, playerNames));
    }

    private static String getGameResultMessage(final Map<GameResult, Integer> dealerWinning) {
        String message = "";
        for (GameResult gameResult : GameResult.values()) {
            if (dealerWinning.containsKey(gameResult)) {
                message += (dealerWinning.get(gameResult) + gameResult.getResultMeaning() + " ");
            }
        }
        return message;
    }

    private static void printScoreOf(Participant participant) {
        System.out.println(" - 결과: " + participant.calculateFinalScore());
    }

    private static String getParticipantName(Participant participant) {
        if (participant instanceof Dealer) {
            return "딜러";
        }
        Player player = (Player) participant;
        return player.getName();
    }

    public static void printExceptionMessage(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
