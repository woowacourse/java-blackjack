package view;

import dto.FinalScoreDto;
import dto.InitialDto;
import dto.MoneyDTO;
import dto.ParticipantDto;
import dto.ParticipantScoreDto;
import dto.PlayerOutcomeDto;
import java.util.List;
import message.IOMessage;

public class ResultView {
    public void printDealerMoreCard() {
        System.out.println(IOMessage.DEALER_ONE_CARD.message());
    }

    public void printParticipantMoreCard(ParticipantDto participantDto) {
        System.out.printf("%s카드: %s%n",
                participantDto.name(),
                String.join(", ", participantDto.cardNames()));
    }

    public void printGameStart(InitialDto initialDto) {
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", initialDto.joinedNames());
        System.out.printf("딜러카드: %s%n", initialDto.dealer().cardNames().getFirst());
        for (ParticipantDto player : initialDto.players()) {
            System.out.printf("%s카드: %s%n", player.name(), String.join(", ", player.cardNames()));
        }
    }

    public void printResult(FinalScoreDto finalScoreDto) {
        ParticipantScoreDto dealer = finalScoreDto.dealer();
        System.out.printf("%s카드: %s - 결과: %d%n",
                dealer.name(),
                String.join(", ", dealer.cardNames()),
                dealer.score());
        for (ParticipantScoreDto player : finalScoreDto.players()) {
            System.out.printf("%s카드: %s - 결과: %d%n",
                    player.name(),
                    String.join(", ", player.cardNames()),
                    player.score());
        }
    }

    public void printScore(MoneyDTO moneyDTO) {
        System.out.println();
        System.out.println(IOMessage.FINAL_MONEY_STATISTICS.message());
        printDealerOutcome(moneyDTO.dealerMoney());
        printPlayerOutcomes(moneyDTO.playerOutcomes());
    }

    private void printDealerOutcome(String dealerOutcomes) {
        System.out.print("딜러: " + dealerOutcomes);
        System.out.println();
    }

    private void printPlayerOutcomes(List<PlayerOutcomeDto> playerOutcomes) {
        for (PlayerOutcomeDto outcomeDto : playerOutcomes) {
            System.out.println(outcomeDto.name() + ": " + outcomeDto.profit());
        }
    }
}
