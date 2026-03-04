package view;

import dto.HandDto;
import java.util.List;

public class OutputView {

    public void printPlayers(List<String> names) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", String.join(", ", names));
    }

    public void printHandList(List<HandDto> handDtoList) {
        for (HandDto handDto : handDtoList) {
            printHand(handDto);
        }
        System.out.println();
    }

    public void printHand(HandDto handDto) {
        System.out.printf("%s카드: %s\n", handDto.name(), String.join(", ", handDto.hand()));
    }
}
