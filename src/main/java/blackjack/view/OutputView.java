package blackjack.view;

import blackjack.domain.participants.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static final String JOIN_DELIMITER = ", ";

    public static void nameInstruction() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void shareFirstPair(Participants participants) {
        String names = readParticipantsName(participants);
        System.out.println(
                String.format(System.lineSeparator() + "%s에게 %d장을 나누었습니다.",
                        names, Participants.FIRST_CARDS_COUNT
                )
        );
    }

    public static String readParticipantsName(Participants participants) {
        return participants.getParticipants().stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }


    public static void participantsStatus(Participants participants) {
        for (Participant participant : participants) {
            participantStatus(participant);
        }
        System.out.println();
    }

    public static void moreCardInstruction(Player player) {
        System.out.println(player.getName() + "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
    }

    public static void participantStatus(Participant participant) {
        System.out.println(statusToString(participant));
    }

    public static void moreCardInstruction(Dealer dealer) {
        System.out.println();
        for (int i = 0; i < dealer.countAddedCard(); i++) {
            System.out.println(
                    String.format("%s는 %d 미만이라 한 장의 카드를 더 받았습니다.",
                            dealer.getName(), Dealer.DEALER_DRAW_CRITERIA)
            );
        }
        System.out.println();
    }

    public static void result(Participants participants) {
        for (Participant participant : participants) {
            System.out.println(statusToString(participant) + " - 결과: " + participant.score());
        }
        System.out.println();
    }

    public static String statusToString(Participant participant) {
        return participant.getName() + ": " + participant.handStatus();
    }

    public static void statistics(Participants participants) {
        System.out.println("## 최종 승패");
        System.out.println(dealerResult(participants.getDealer()));
        System.out.println(playerResult(participants.getPlayers()));
    }

    public static String dealerResult(Dealer dealer) {
        return dealer.getName()
                + " : "
                + Arrays.stream(Result.values())
                .filter(result -> dealer.getResult(result) != 0)
                .map(result -> dealer.getResult(result) + result.getValue())
                .collect(Collectors.joining(" "));
    }

    public static String playerResult(List<Player> players) {
        return players.stream()
                .map(player -> player.getName() + " : " + player.getResult().getValue())
                .collect(Collectors.joining("\n"));
    }

    public static void printError(String message) {
        System.out.println(message);
    }

}
