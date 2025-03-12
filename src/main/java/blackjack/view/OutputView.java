package blackjack.view;

import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import java.util.List;

public final class OutputView {

    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printStartingCardsStatuses(Participants participants) {
        String names = String.join(DELIMITER, participants.getNamesOfParticipants());

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");

        for (Participant participant : participants.getParticipants()) {
            if (participant.shouldRevealSingleCard()) {
                System.out.println(Formatter.formatSingleCardStatus(participant));
                continue;
            }
            System.out.println(Formatter.formatMultipleCardStatusWithName(participant));
        }
    }


    public static void printMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(List<PlayerResult> playerResults, DealerResult dealerResult,
                                       Participant participant) {
        System.out.println(Formatter.formatDealerCardResult(participant, dealerResult));

        for (PlayerResult playerResult : playerResults) {
            System.out.println(Formatter.formatPlayerCardResult(playerResult));
        }
    }

    public static void printCardResult(Participant participant) {
        System.out.println(Formatter.formatMultipleCardStatusWithName(participant));
    }

    public static void printBustedParticipantWithName(Participant participant) {
        if (participant.doesHaveName()) {
            Player player = (Player) participant;
            System.out.println(player.getName() + "는 버스트되어 더 이상 카드를 뽑을 수 없습니다!");
        }
        throw new IllegalArgumentException("해당 참가자는 이름이 존재하지 않습니다.");
    }

    public static void printGameResult(List<PlayerResult> playersResult,
                                       DealerResult dealerResult) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %s%n", Formatter.formatDealerGameResult(dealerResult));
        System.out.println(Formatter.formatPlayerGameResult(playersResult));
    }

}
