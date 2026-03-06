package view;

import domain.Player;
import dto.BlackjackResultDto;
import dto.DealerResultDto;
import dto.HandDto;
import dto.PlayerResultDto;
import dto.PlayersDto;
import java.util.List;

public class OutputView {

    public void printPlayers(PlayersDto playersDto) {
        List<String> names = playersDto.players().stream()
            .map(Player::getName)
            .toList();
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

    public void printBlackjackStatistics(DealerResultDto dealerResultDto,
        List<PlayerResultDto> playerResultDtoList) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러:%s%s%s\n", printWin(dealerResultDto.win()),
            printDraw(dealerResultDto.draw()), printLose(dealerResultDto.lose()));
        for (PlayerResultDto playerResultDto : playerResultDtoList) {
            System.out.printf("%s: %s\n", playerResultDto.name(),
                playerResultDto.result().getResult());
        }
    }

    private String printLose(int lose) {
        if (lose == 0) {
            return "";
        }
        return " " + lose + "패";
    }

    private String printDraw(int draw) {
        if (draw == 0) {
            return "";
        }
        return " " + draw + "무";
    }

    private String printWin(int win) {
        if (win == 0) {
            return "";
        }
        return " " + win + "승";
    }
}
