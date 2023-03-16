package blackjack.view;

import blackjack.domain.betting.Betting;
import blackjack.domain.participant.Participant;
import blackjack.dto.BettingResultDto;
import blackjack.dto.DrawParticipantsDto;
import blackjack.dto.initialParticipantsDto;

import java.util.List;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String COMMA = ", ";
    private static final String COLON = ": ";
    private static final String GIVE_TWO_CARD_MESSAGE = "에게 두 장을 나누었습니다.";
    private static final String NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String RESULT_DELIMITER = " - 결과 : ";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String ERROR_PREFIX_MESSAGE = "[ERROR] ";

    public void printInitialCards(final initialParticipantsDto participants) {
        System.out.println(getParticipantsList(participants.names()) + GIVE_TWO_CARD_MESSAGE);

        printDealerNameAndCard(participants.dealerName(), participants.dealerCardName());
        participants.players().forEach(this::printParticipantNameAndCards);
    }

    private String getParticipantsList(final List<String> participantNames) {
        return String.join(COMMA, participantNames);
    }

    private void printDealerNameAndCard(final String dealerName, final String cardName) {
        System.out.println(NEW_LINE + dealerName + COLON + cardName);
    }

    public void printParticipantNameAndCards(final Participant participant) {
        System.out.println(getNameAndCards(participant.getName(), participant.getCardNames()));
    }

    private String getNameAndCards(final String name, final List<String> cardNames) {
        return name + COLON + String.join(COMMA, cardNames);
    }

    public void printCardsAndScoreForAllParticipants(final DrawParticipantsDto participants) {
        System.out.printf(NEW_LINE);
        participants.all().forEach(this::printCardsAndScore);
    }

    private void printCardsAndScore(final Participant participant) {
        final String name = participant.getName();
        final List<String> cardNames = participant.getCardNames();

        System.out.println(getNameAndCards(name, cardNames) + RESULT_DELIMITER + getScore(participant));
    }

    private String getScore(final Participant participant) {
        return String.valueOf(participant.getScore().getValue());
    }

    public void printDealerDrawAdditionalCard() {
        System.out.println(NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE);
    }

    public void printBettingResult(final BettingResultDto bettingResult) {
        System.out.println(NEW_LINE + FINAL_RESULT_MESSAGE);
        printDealerResult(bettingResult.dealerName(), bettingResult.dealerProfit());
        for (final Participant player : bettingResult.players()) {
            printPlayerResult(player.getName(), bettingResult.getPlayerResult(player));
        }
    }

    private void printDealerResult(final String dealerName, final Betting dealerProfit) {
        System.out.println(dealerName + COLON + dealerProfit.getValue());
    }

    private void printPlayerResult(final String playerName, final Betting money) {
        System.out.println(playerName + COLON + money.getValue());
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX_MESSAGE + e.getMessage());
    }
}
