package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INITIAL_CARDS_INFO_PREFIX_FORMAT = "%s와 %s에게 2장씩 나눴습니다." + System.lineSeparator();
    private static final String CARDS_INFO_FORMAT = "%s카드: %s" + System.lineSeparator();
    private static final String CARDS_INFO_WITH_SCORE_FORMAT = "%s카드: %s - 결과: %d" + System.lineSeparator();
    public static final String DELIMITER = ", ";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialCardsInfo(Dealer dealer, List<Player> players) {
        printInitialCardsInfoPrefix(dealer, players);
        printDealerCardsInfo(dealer);
        for (Player player : players) {
            printParticipantCardsInfo(player);
        }
        System.out.println();
    }

    private static void printInitialCardsInfoPrefix(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.printf(INITIAL_CARDS_INFO_PREFIX_FORMAT,dealer.getName(), playerNames);
    }

    private static void printDealerCardsInfo(Dealer dealer) {
        System.out.printf(CARDS_INFO_FORMAT, dealer.getName(), dealer.getFirstCardsInfoToString());
    }

    public static void printParticipantCardsInfo(Participant participant) {
        System.out.printf(CARDS_INFO_FORMAT, participant.getName(), participant.getCurrentCardsInfo());
    }

    public static void printParticipantCardsInfoWithScore(Participant participant) {
        System.out.printf(CARDS_INFO_WITH_SCORE_FORMAT,
                participant.getName(), participant.getCurrentCardsInfo(), participant.getCardsScore());
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void printDealerAndPlayersCardsInfoWithScore(Dealer dealer, List<Player> players) {
        printParticipantCardsInfoWithScore(dealer);
        for (Player player : players) {
            printParticipantCardsInfoWithScore(player);
        }
        System.out.println();
    }
}
