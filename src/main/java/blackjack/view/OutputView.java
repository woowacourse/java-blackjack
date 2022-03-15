package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {

    private static final int INIT_DISTRIBUTE_SIZE = 2;
    private static final int ADD_CARD_CONDITION = 16;
    private static final String DELIMITER = ", ";

    public static void printPlayersInitCardInfo(final Players players) {
        final List<Participant> participants = players.getParticipants();
        final Dealer dealer = players.getDealer();

        final String basicDistribute = "딜러와 %s에게 " + INIT_DISTRIBUTE_SIZE + "장을 나누어주었습니다.";
        System.out.println();
        System.out.printf((basicDistribute) + "%n", String.join(DELIMITER, extractNames(participants)));
        printInitDealerInfo(dealer);
        printParticipantsInfo(participants);
    }

    private static List<String> extractNames(final List<Participant> participants) {
        return participants.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private static void printInitDealerInfo(final Player dealer) {
        Card dealerCard = ((Dealer) dealer).getCardFirstOne();
        System.out.println("딜러: " + dealerCard.getScore().getSymbol() + dealerCard.getType().getName());
    }

    private static void printParticipantsInfo(final List<Participant> participants) {
        for (Participant participant : participants) {
            System.out.println(makePlayerCardInfo(participant));
        }
        System.out.println();
    }

    public static void printPlayerCardInfo(final Player player) {
        System.out.println(makePlayerCardInfo(player));
    }

    private static String makePlayerCardInfo(final Player player) {
        return player.getName() + "카드: " + String.join(DELIMITER, convertToText(player.getCards()));
    }

    private static List<String> convertToText(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getScore().getSymbol() + card.getType().getName())
                .collect(Collectors.toList());
    }

    public static void printParticipantOverMaxScore(final String name) {
        System.out.println(name + "은 최고점수를 초과하여 카드를 더 이상 받을 수 없습니다.");
    }

    public static void printDealerAcceptCard() {
        System.out.println("딜러는 " + ADD_CARD_CONDITION + "이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printDealerDenyCard() {
        System.out.println("딜러는 " + ADD_CARD_CONDITION + "을 초과하여 스탠드 하였습니다.");
        System.out.println();
    }

    public static void printPlayerFinalInfo(final Player player) {
        System.out.println(makePlayerCardInfo(player) + " - 결과:" + player.calculateFinalScore());
    }

    public static void printFinishParticipantInfo(final List<Participant> participants) {
        participants.forEach(OutputView::printPlayerFinalInfo);
        System.out.println();
    }

    public static void printResult(final Players players) {
        System.out.println("## 최종 수익");
        printPlayerResult(players.getDealer());
        for (Participant participant : players.getParticipants()) {
            printPlayerResult(participant);
        }
    }

    private static void printPlayerResult(final Player player) {
        System.out.println(player.getName() + ": " + player.getBetProfit());
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
