package view.output;

import domain.player.ParticipantGameInfo;
import dto.response.DealerResultDto;
import dto.response.ParticipantHandResponseDto;
import dto.response.ParticipantsGameInfoDto;
import dto.response.ParticipantsHandResponseDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConsoleOutputView implements OutputView{

    @Override
    public void printInitialDeal(List<String> names) {
        System.out.println();
        System.out.println("딜러와 "
        + String.join(", ", names)
        + "에게 2장을 나누었습니다.");
    }

    @Override
    public void printParticipantsInfo(ParticipantsHandResponseDto responseDto) {
        for(Entry<String, List<String>> gamblers : responseDto.gamblersInfo().entrySet()) {
            System.out.println(gamblers.getKey() + "카드: " +
                    String.join(", ", gamblers.getValue()));
        }
        System.out.println();
    }

    @Override
    public void printDealerCardIsUnder16() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    @Override
    public void printParticipantInfo(ParticipantHandResponseDto responseDto) {
        System.out.println(responseDto.name() + "카드: " +
                String.join(", ", responseDto.cards()));
    }

    @Override
    public void printParticipantsGameInfo(ParticipantsGameInfoDto responseDto) {
        System.out.println();
        for (ParticipantGameInfo participant : responseDto.participants()) {
            System.out.println(participant.name() + "카드: " +
                    String.join(", ", participant.cards()) + " - 결과: " + participant.score());
        }
        System.out.println();
    }

    @Override
    public void printDealerResult(DealerResultDto resultDto) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        if (resultDto.dealerWinCount() > 0) {
            System.out.print(resultDto.dealerWinCount() + "승 ");
        }
        if (resultDto.dealerLoseCount() > 0) {
            System.out.print(resultDto.dealerLoseCount() + "패 ");
        }
        if (resultDto.dealerDrawCount() > 0) {
            System.out.print(resultDto.dealerLoseCount() + "무 ");
        }
        System.out.println();
    }

    @Override
    public void printGamblerResult(Map<String, String> gamblersResult) {
        for (Entry<String, String> result : gamblersResult.entrySet()) {
            System.out.println(result.getKey() + ": " + result.getValue());
        }
    }
}
