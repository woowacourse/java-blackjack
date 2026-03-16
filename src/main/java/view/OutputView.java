package view;

import domain.game.Profit;
import domain.player.ParticipantGameInfo;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.responsedto.DealerResultDto;
import view.responsedto.ParticipantHandResponseDto;
import view.responsedto.ParticipantsGameInfoDto;
import view.responsedto.ParticipantsHandResponseDto;

public class OutputView {

    public void printInitialDeal(List<String> names) {
        System.out.println();
        System.out.println("딜러와 "
                + String.join(", ", names)
                + "에게 2장을 나누었습니다.");
    }

    public void printParticipantsInfo(ParticipantsHandResponseDto responseDto) {
        for (Entry<String, List<String>> gamblers : responseDto.gamblersInfo().entrySet()) {
            System.out.println(gamblers.getKey() + "카드: " +
                    String.join(", ", gamblers.getValue()));
        }
        System.out.println();
    }

    public void printDealerCardIsBelowDrawThreshold() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void printParticipantInfo(ParticipantHandResponseDto responseDto) {
        System.out.println(responseDto.name() + "카드: " +
                String.join(", ", responseDto.cards()));
    }

    public void printParticipantsGameInfo(ParticipantsGameInfoDto responseDto) {
        System.out.println();
        for (ParticipantGameInfo participant : responseDto.participants()) {
            System.out.println(participant.name() + "카드: " +
                    String.join(", ", participant.cards()) + " - 결과: " + participant.score());
        }
        System.out.println();
    }

    public void printDealerResult(DealerResultDto resultDto) {
        System.out.println("## 최종 수익");
        System.out.print("딜러: " + resultDto.dealerProfit());
        System.out.println();
    }

    public void printGamblerResult(Map<String, Profit> gamblersResult) {
        for (Entry<String, Profit> result : gamblersResult.entrySet()) {
            System.out.println(result.getKey() + ": " + result.getValue().getProfit());
        }
    }

    public void printBlackJackMessage(String name) {
        System.out.println(name + " 블랙잭입니다!");
    }
}
