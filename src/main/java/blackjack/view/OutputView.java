package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerGameResultDto;
import java.util.List;

public class OutputView {

    public void printInitDraw(ParticipantDto dealerDto, List<ParticipantDto> playerDtos) {
        List<String> playerNames = playerDtos.stream().map(ParticipantDto::name).toList();

        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n",
                String.join(", ", playerNames));

        String dealerFirstCard = dealerDto.cardNames().getFirst();

        System.out.printf("딜러카드: %s%n", dealerFirstCard);

        //플레이어 카드 출력
        for (ParticipantDto playerDto : playerDtos) {
            System.out.printf("%s카드: %s%n", playerDto.name(), String.join(", ", playerDto.cardNames()));
        }

    }

    public void printCard(ParticipantDto playerDto) {
        System.out.printf("%s카드: %s%n", playerDto.name(), String.join(", ", playerDto.cardNames()));
    }

    public void printDealerDraw() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardResult(ParticipantDto dealerDto, List<ParticipantDto> playerDtos) {
        System.out.printf("%n딜러카드: %s - 결과: %d%n",
                String.join(", ", String.join(", ", dealerDto.cardNames())), dealerDto.point());
        for (ParticipantDto playerDto : playerDtos) {
            System.out.printf("%s카드: %s - 결과: %d%n", playerDto.name(), String.join(", ", playerDto.cardNames()),
                    playerDto.point());
        }
    }

    public void printFinalGameResult(List<PlayerGameResultDto> playerGameResultDtos) {
        System.out.println("\n## 최종 승패");

        int winCount = 0;
        int tieCount = 0;
        int loseCount = 0;
        for (PlayerGameResultDto dto : playerGameResultDtos) {
            if (dto.gameResult().equals(GameResult.WIN.getName())) {
                loseCount++;
            }
            if (dto.gameResult().equals(GameResult.LOSE.getName())) {
                winCount++;
            }
            if (dto.gameResult().equals(GameResult.TIE.getName())) {
                tieCount++;
            }
        }
        System.out.printf("딜러: %d승 %d무 %d패%n", winCount, tieCount, loseCount);
        for (PlayerGameResultDto dto : playerGameResultDtos) {
            System.out.printf("%s: %s%n", dto.name(), dto.gameResult());
        }
    }

}
