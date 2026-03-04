package view;

import dto.BlackjackResultDto;
import dto.HandDto;
import java.util.List;

public class OutputView {

    public void printPlayers(List<String> names) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", String.join(", ", names));
    }

    public void printHandList(List<HandDto> handDtoList) {
        for (HandDto handDto : handDtoList) {
            printHand(handDto);
            System.out.println();
        }
        System.out.println();
    }

    public void printHand(HandDto handDto) {
        System.out.printf("%s카드: %s", handDto.name(), String.join(", ", handDto.hand()));
    }

    public void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printBlackjackResult(List<BlackjackResultDto> blackjackResultDtoList) {
        for (BlackjackResultDto resultDto : blackjackResultDtoList) {
            printHand(resultDto.handDto());
            System.out.printf(" - 결과: %d\n", resultDto.score());
        }
        System.out.println();
    }
}
