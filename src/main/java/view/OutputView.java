package view;

import domain.blackjack.BlackJackResult;
import domain.blackjack.WinStatus;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final OutputFormat outputFormat = new OutputFormat();

    private OutputView() {

    }

    public static void printBeginDealingInformation(Participants participants) {
        System.out.println();
        System.out.printf(outputFormat.formatParticipantNames(participants));
        System.out.println();
        System.out.println();
    }

    public static void printDealerHands(Dealer dealer) {
        Name name = dealer.getName();
        List<Card> cards = dealer.getCards();
        System.out.printf("%s카드: %s", name.getValue(), outputFormat.formatCard(cards.get(0)));
        System.out.println();
    }

    public static void printParticipantHands(Participant participant) {
        System.out.printf(outputFormat.formatHands(participant));
        System.out.println();
    }

    public static void printDealerHit(int count) {
        System.out.println();
        for(int i = 0; i < count; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            System.out.println();
        }
    }

    public static void printParticipantResult(Participant participant) {
        System.out.printf(outputFormat.formatParticipantResult(participant));
        System.out.println();
    }

    public static void printBlackJackResult(BlackJackResult blackJackResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf(outputFormat.formatDealerResult(blackJackResult));
        System.out.println();

        for (Map.Entry<Participant, WinStatus> winStatusEntry : blackJackResult.getEntry()) {
            System.out.println(outputFormat.formatBlackJackResult(winStatusEntry));
        }
    }
}
