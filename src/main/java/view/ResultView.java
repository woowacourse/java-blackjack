package view;

import view.dto.card.CardsDto;
import view.dto.participant.ParticipantDto;
import view.dto.result.GameResultDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.BlackjackGame.DEALER_HIT_THRESHOLD;
import static domain.BlackjackGame.INITIAL_CARD_COUNT;

public class ResultView {
    public static final String DELIMITER = ", ";
    public static final String EMPTY = "";

    public void printInitialCards(final ParticipantDto dealer, final List<ParticipantDto> players) {
        printInitialDealMessage(dealer, players);
        printTotalParticipant(dealer, players);
        System.out.println();
    }

    private void printInitialDealMessage(final ParticipantDto dealer, final List<ParticipantDto> players) {
        System.out.printf(System.lineSeparator() + "%s와 %s에게 %d장을 나누었습니다.",
                dealer.name(),
                parsePlayerNames(players),
                INITIAL_CARD_COUNT
        );
    }

    private void printTotalParticipant(final ParticipantDto dealer, final List<ParticipantDto> players) {
        printParticipantHand(dealer);
        players.forEach(this::printParticipantHand);
    }

    public void printParticipantHand(final ParticipantDto participantDto) {
        CardsDto cards = participantDto.cards();
        System.out.printf(System.lineSeparator() + "%s: %s", participantDto.name(), cards.parseCards());
    }


    private String parsePlayerNames(final List<ParticipantDto> players) {
        return players.stream()
                      .map(ParticipantDto::name)
                      .reduce((player1, player2) -> player1 + DELIMITER + player2)
                      .orElse(EMPTY);
    }

    public void printDealerCardMessage(final ParticipantDto dealer) {
        System.out.printf(System.lineSeparator() + "%s는 %s이하라 한장의 카드를 더 받습니다." + System.lineSeparator(),
                dealer.name(),
                DEALER_HIT_THRESHOLD
        );
    }

    public void printResults(final ParticipantDto dealer, final List<ParticipantDto> players, final GameResultDto gameResultDto) {
        System.out.println();
        printCardAndSum(dealer);
        players.forEach(this::printCardAndSum);
        System.out.println();
        printGameResults(dealer, gameResultDto);
        System.out.println();
    }

    private void printCardAndSum(final ParticipantDto participantDto) {
        CardsDto cards = participantDto.cards();
        System.out.printf("%s: %s - 결과: %d" + System.lineSeparator(),
                participantDto.name(),
                cards.parseCards(),
                participantDto.score()
        );
    }

    private void printGameResults(final ParticipantDto dealer, final GameResultDto gameResultDto) {
        Map<String, Integer> dealerResult = gameResultDto.dealerResult();
        Map<String, String> playerResults = gameResultDto.gameResult();
        System.out.println("## 최종 승패");
        System.out.print(dealer.name() + ": " + parseDealerResult(dealerResult));
        System.out.println(parsePlayerResult(playerResults));
    }

    public String parsePlayerResult(Map<String, String> playerResults) {
        return playerResults.keySet()
                            .stream()
                            .map(player -> player + ": " + playerResults.get(player) + System.lineSeparator())
                            .reduce((a, b) -> a + b)
                            .orElse("");
    }

    public String parseDealerResult(Map<String, Integer> dealerResult) {
        return dealerResult.entrySet()
                           .stream()
                           .map(entry -> entry.getValue() + entry.getKey())
                           .collect(Collectors.joining(" "));
    }


}
