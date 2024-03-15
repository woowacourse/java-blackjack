package view;

import domain.card.Card;
import domain.dto.ParticipantDto;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;

public class OutputView {

    private static final OutputFormat outputFormat = new OutputFormat();

    private OutputView() {

    }

    public static void printBeginDealingInformation(List<Name> playerNames) {
        System.out.println();
        System.out.printf(outputFormat.formatParticipantNames(playerNames));
        System.out.println();
        System.out.println();
    }

    public static void printDealerHands(Name name, List<Card> cards) {
        System.out.printf("%s카드: %s", name.getValue(), outputFormat.formatCard(cards.get(0)));
        System.out.println();
    }

    public static void printParticipantHands(ParticipantDto participantDto) {
        System.out.printf(outputFormat.formatHands(participantDto));
        System.out.println();
    }

    public static void printDealerHit(int count) {
        System.out.println();
        for (int i = 0; i < count; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            System.out.println();
        }
    }

    public static void printParticipantResult(ParticipantDto participantDto) {
        System.out.printf(outputFormat.formatParticipantResult(participantDto));
        System.out.println();
    }

    public static void printBlackJackResult(Players players, Dealer dealer) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.printf(outputFormat.formatDealerResult(players, dealer));
        System.out.println();

        for (Player player : players.getValue()) {
            System.out.println(outputFormat.formatPlayerResult(player, player.getPayout(dealer)));
        }
    }
}
