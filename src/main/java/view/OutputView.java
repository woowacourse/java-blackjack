package view;

import dto.CardInfoResponseDto;
import dto.InitialCardInfoResponseDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public void printInitialDeal(List<String> names) {
        System.out.println("딜러와 "
        + String.join(", ", names)
        + "에게 2장을 나누었습니다.");
    }

    public void printInitialInfo(InitialCardInfoResponseDto responseDto) {
        System.out.println(responseDto.dealerName() + "카드: " + responseDto.dealerHandInfo());

        for(Entry<String, List<String>> gamblers : responseDto.gamblersInfo().entrySet()) {
            System.out.println(gamblers.getKey() + "카드: " +
                    String.join(", ", gamblers.getValue()));
        }
    }

    public void printDealerCardIsUnder16() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printGameResult(CardInfoResponseDto responseDto) {
        int index = 0;
        for(Entry<String, List<String>> participant : responseDto.participantsInfo().entrySet()) {
            System.out.println(participant.getKey() + "카드: " +
                    String.join(", ", participant.getValue())
                    + " - 결과: " + responseDto.score().get(index)
            );
            index++;
        }
    }
}
