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
    private static final String CONNECT = ": ";
    public static final String SPACING = " ";
    public static final String DELIMITER = ",";


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
                      .collect(Collectors.joining(DELIMITER + SPACING));
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
        System.out.printf("%s" + CONNECT + "%s - 결과" + CONNECT + "%d" + System.lineSeparator(),
                participantDto.name(),
                cards.parseCards(),
                participantDto.score()
        );
    }

    private void printGameResults(final ParticipantDto dealer, final GameResultDto gameResultDto) {
        Map<String, Integer> dealerResult = gameResultDto.dealerResult();
        Map<String, String> playerResults = gameResultDto.gameResult();
        System.out.println("## 최종 승패");
        System.out.print(dealer.name() + CONNECT + parseDealerResult(dealerResult) + System.lineSeparator());
        System.out.println(parsePlayerResult(playerResults));
    }

    private String parsePlayerResult(final Map<String, String> playerResults) {
        return playerResults.keySet()
                            .stream()
                            .map(player -> player + CONNECT + playerResults.get(player))
                            .collect(Collectors.joining(System.lineSeparator()));
    }

    private String parseDealerResult(final Map<String, Integer> dealerResult) {
        return dealerResult.entrySet()
                           .stream()
                           .map(entry -> entry.getValue() + entry.getKey())
                           .collect(Collectors.joining(SPACING));
    }


}
