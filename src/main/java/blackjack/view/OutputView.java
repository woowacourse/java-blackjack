package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int INIT_DISTRIBUTE_SIZE = 2;
    private static final int ADD_CARD_CONDITION = 16;
    private static final String DELIMITER = ", ";
    private static final String EXPLAIN_SYMBOL = ": ";

    public static void printPlayersInitCardInfo(final Players players) {
        final List<Player> participants = players.getParticipants();
        final Player dealer = players.getDealer();

        final String basicDistribute = "%s와 %s에게 " + INIT_DISTRIBUTE_SIZE + "장을 나누어주었습니다.";
        System.out.println();
        System.out.printf((basicDistribute) + "%n", dealer.getName(), String.join(DELIMITER, extractNames(participants)));
        printInitDealerInfo(dealer);
        printParticipantsInfo(participants);
    }

    private static List<String> extractNames(final List<Player> participants) {
        return participants.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private static void printInitDealerInfo(final Player dealer) {
        Card dealerCard = dealer.getCards().get(0);
        System.out.println(dealer.getName() + EXPLAIN_SYMBOL + dealerCard.getScore().getSymbol() + dealerCard.getType().getName());
    }

    private static void printParticipantsInfo(final List<Player> participants) {
        participants.forEach(
                participant -> System.out.println(makePlayerCardInfo(participant))
        );
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

    public static void printDealerAcceptCard() {
        System.out.println("딜러는 " + ADD_CARD_CONDITION + "이하라 한장의 카드를 더 받았습니다.");

    }

    public static void printDealerDenyCard() {
        System.out.println("딜러는 " + ADD_CARD_CONDITION + "을 초과하여 스탠드 하였습니다.");
        System.out.println();
    }

    public static void printFinishParticipantInfo(final List<Player> participants) {
        for (Player participant : participants) {
            printPlayerFinalInfo(participant);
        }
        System.out.println();
    }

    public static void printPlayerFinalInfo(final Player player) {
        System.out.println(makePlayerCardInfo(player) + " - 결과:" + player.calculateFinalScore());
    }

    public static void printGameResult(final List<Player> participants, final int dealerProfit) {
        System.out.println("## 최종 수익");
        printDealerResult(dealerProfit);
        printParticipantsResult(participants);
    }

    private static void printDealerResult(final int dealerProfit) {
        System.out.println("딜러" + EXPLAIN_SYMBOL + dealerProfit);
    }

    private static void printParticipantsResult(final List<Player> participants) {
        participants.forEach(OutputView::printParticipantResult);
    }

    private static void printParticipantResult(final Player player) {
        if (player.isParticipant()) {
            Participant participant = (Participant) player;
            System.out.print(participant.getName() + EXPLAIN_SYMBOL);
            System.out.println(participant.betting().profit());
        }
    }
}
