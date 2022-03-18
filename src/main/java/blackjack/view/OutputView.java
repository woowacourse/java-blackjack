package blackjack.view;

import blackjack.domain.bet.Money;
import blackjack.domain.participant.Participant;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.PlayersDto;
import blackjack.view.dto.ResultCounterDto;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public static void printInitCardHandStatus(ParticipantDto dealerDto, PlayersDto playersDto) {
        System.out.println();
        System.out.println("딜러와 " + playersDto.showNames() + "에게 2장을 나누었습니다.");

        System.out.println("딜러: " + dealerDto.showOneCard());
        System.out.println(playersDto.showEachCards());
        System.out.println();
    }

    public static String showCardHandStatus(ParticipantDto participantDto) {
        return String.format("%s 카드: %s",
                participantDto.getName(),
                participantDto.showCards());
    }

    public static void printDealerStatus() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardHandStatus(ParticipantDto participantDto) {
        System.out.println(showCardHandStatus(participantDto));
    }

    public static void printFinalStatus(ParticipantDto dealerDto, PlayersDto playersDto) {
        System.out.println(showCardHandStatus(dealerDto) + " 결과 - " + dealerDto.getScore());
        System.out.println(playersDto.showEachResult());
    }

    public static void printFinalResult(ResultCounterDto resultCounterDto) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println(resultCounterDto.showDealerResult());
        System.out.println(resultCounterDto.showEachPlayersResult());
    }

    public static void printFinalResult(Map<Participant, Money> calculateHitProfit) {
        System.out.println();
        System.out.println("## 최종 수익");
        for (Entry<Participant, Money> participantMoneyEntry : calculateHitProfit.entrySet()) {
            System.out.printf("%s: %d%n", participantMoneyEntry.getKey().getName().getValue(), participantMoneyEntry.getValue().getValue());
        }
    }
}
