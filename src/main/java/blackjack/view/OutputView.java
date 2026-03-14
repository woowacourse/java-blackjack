package blackjack.view;

import blackjack.dto.InitialStatusDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.ProfitsDto;

import java.util.List;

public class OutputView {
    public void printFirstCardStatus(InitialStatusDto dto) {
        String playerNames = String.join(", ", dto.getPlayerNames());
        System.out.println("\n" + dto.getDealerName() + "와 " + playerNames + "에게 2장을 나누었습니다.");
        System.out.println(dto.getDealerName() + "카드: " + dto.getDealerFirstCardName());
        for (ParticipantResultDto playerDto : dto.getPlayerDtos()) {
            printPlayerCardStatus(playerDto);
        }
        System.out.println();
    }

    public void printPlayerCardStatus(ParticipantResultDto dto) {
        String cardNames = String.join(", ", dto.getCardNames());
        System.out.println(dto.getName() + "카드: " + cardNames);
    }

    public void printDealerReceiveCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScoreResult(ParticipantResultDto dealerDto, List<ParticipantResultDto> playerDtos) {
        System.out.println();
        printParticipantGameResult(dealerDto);
        for (ParticipantResultDto playerDto : playerDtos) {
            printParticipantGameResult(playerDto);
        }
    }

    private void printParticipantGameResult(ParticipantResultDto dto) {
        String cards = String.join(", ", dto.getCardNames());
        System.out.println(dto.getName() + "카드: " + cards + " - 결과: " + dto.getScore());
    }

    public void printGameResult(ProfitsDto profitsDto) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러 : " + (int) profitsDto.getDealerProfit());
        for (String playerName : profitsDto.getPlayerProfits().keySet()) {
            System.out.println(playerName + ": " + profitsDto.getPlayerProfits().get(playerName).intValue());
        }
    }
}
