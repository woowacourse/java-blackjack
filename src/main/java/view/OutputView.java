package view;

import domain.player.ParticipantGameInfo;
import dto.ParticipantHandResponseDto;
import dto.ParticipantsGameInfoDto;
import dto.ParticipantsHandResponseDto;
import java.util.List;
import java.util.Map.Entry;

public class OutputView {

    public void printInitialDeal(List<String> names) {
        System.out.println("딜러와 "
        + String.join(", ", names)
        + "에게 2장을 나누었습니다.");
    }

    public void printParticipantsInfo(ParticipantsHandResponseDto responseDto) {
        for(Entry<String, List<String>> gamblers : responseDto.gamblersInfo().entrySet()) {
            System.out.println(gamblers.getKey() + "카드: " +
                    String.join(", ", gamblers.getValue()));
        }
    }

    public void printDealerCardIsUnder16() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printParticipantInfo(ParticipantHandResponseDto responseDto) {
        System.out.println(responseDto.name() + "카드: " +
                String.join(", ", responseDto.cards()));
    }

    public void printParticipantsGameInfo(ParticipantsGameInfoDto responseDto) {
        for (ParticipantGameInfo participant : responseDto.participants()) {
            System.out.println(participant.name() + "카드: " +
                    String.join(", ", participant.cards()) + "- 결과: " + participant.score());
        }
    }
}
