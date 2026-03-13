package blackjack.view;

import blackjack.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String JOIN_DELIMITER = ", ";
    private static final String INITIAL_DEAL_FORMAT = "%n딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_FIRST_CARD_FORMAT = "딜러카드: %s%n";
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s%n";
    private static final String DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PARTICIPANT_FINAL_CARDS_FORMAT = "%s카드: %s - 결과: %d%n";
    private static final String FINAL_RESULTS_HEADER = "\n## 최종 승패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %s%n";
    private static final String PLAYER_RESULT_FORMAT = "%s: %s%n";
    private static final String FINAL_PROFITS_HEADER = "\n## 최종 수익";

    public void printInitialDeal(InitialDealDto dto) {
        String playerNames = dto.playerCards().stream()
                .map(PlayerCardsDto::name)
                .collect(Collectors.joining(JOIN_DELIMITER));
        System.out.printf(INITIAL_DEAL_FORMAT, playerNames);
        System.out.printf(DEALER_FIRST_CARD_FORMAT, dto.dealerFirstCard());
        dto.playerCards().forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(PlayerCardsDto dto) {
        System.out.printf(PLAYER_CARDS_FORMAT, dto.name(), dto.cards());
    }

    public void printDealerHit() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public void printFinalCards(List<ParticipantFinalDto> participants) {
        System.out.println();
        participants.forEach(p ->
                System.out.printf(PARTICIPANT_FINAL_CARDS_FORMAT, p.name(), p.cards(), p.score())
        );
    }

    public void printFinalResults(GameResultsDto dto) {
        System.out.println(FINAL_RESULTS_HEADER);
        System.out.printf(DEALER_RESULT_FORMAT, dto.dealerResultText());
        dto.playerResults().forEach(p ->
                System.out.printf(PLAYER_RESULT_FORMAT, p.name(), p.resultText())
        );
    }

    public void printProfits(ProfitsDto dto) {
        System.out.println(FINAL_PROFITS_HEADER);
        dto.profits().forEach((name, profit) -> System.out.printf(PLAYER_RESULT_FORMAT, name, profit)
        );
    }
}
