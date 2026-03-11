package view;

import domain.Outcome;
import dto.AllOutcomeDto;
import dto.FinalScoreDto;
import dto.InitialDto;
import dto.ParticipantDto;
import dto.ParticipantScoreDto;
import dto.PlayerOutcomeDto;
import java.util.List;
import java.util.Map;
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

    public void printWinner(AllOutcomeDto allOutcomeDto) {
        System.out.println();
        System.out.println(IOMessage.WINNING_STATISTICS.message());
        printDealerOutcome(allOutcomeDto.dealerResult());
        printPlayerOutcomes(allOutcomeDto.playerOutcomes());
    }

    private void printDealerOutcome(Map<Outcome, Integer> dealerOutcomes) {
        System.out.print("딜러: ");
        for (Outcome outcome : Outcome.values()) {
            System.out.print(dealerOutcomes.get(outcome) + outcome.getName() + " ");
        }
        System.out.println();
    }

    private void printPlayerOutcomes(List<PlayerOutcomeDto> playerOutcomes) {
        for (PlayerOutcomeDto outcomeDto : playerOutcomes) {
            System.out.println(outcomeDto.name() + ": " + outcomeDto.outcome());
        }
    }
}
