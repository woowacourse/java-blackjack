package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;

public class OutputView {
    public static void nameInstruction() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
    }

    public static void shareFirstPair(Participants participants) {
        String names = participants.getNames();
        System.out.println(
            String.format(System.lineSeparator() + "%s에게 %d장을 나누었습니다.",
                names, Participants.FIRST_CARDS_COUNT
            )
        );
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
        for (int i = 0; i < dealer.addedCardCount(); i++) {
            System.out.println(
                String.format("%s는 %d 미만이라 한 장의 카드를 더 받았습니다.",
                    dealer.getName(), Dealer.DEALER_DRAW_CRITERIA)
            );
        }
        System.out.println();
    }

    public static void resultStatus(Participants participants) {
        for (Participant participant : participants) {
            System.out.println(statusToString(participant) + " - 결과: " + participant.score());
        }
        System.out.println();
    }

    public static void result(Participants participants) {
        System.out.println("## 최종 승패");
        for (Participant participant : participants) {
            System.out.println(participant.getName() + " : " + participant.gameResult());
        }
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static String statusToString(Participant participant) {
        return participant.getName() + ": " + participant.handStatus();
    }

}
