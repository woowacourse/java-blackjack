package view;

import domain.BlackjackGameResult;
import domain.BlackjackScore;
import domain.Card;
import domain.Participant;
import domain.Participants;
import domain.Result;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String DEALER_NO_MORE_CARD_MESSAGE = "딜러의 카드합이 17이상이라 카드를 더 받지 않았습니다.";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final int NO_MORE_CARD_COUNT = 0;
    private static final int SKIP_COUNT = 0;

    public void printInitialCards(Participants participants) {
        String namesFormat = participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.joining(DELIMITER));

        breakLine();
        String initialCardsPrefixFormat = String.format(Format.CARD_DISTRIBUTION.format, namesFormat);
        System.out.println(initialCardsPrefixFormat);
        printParticipantsInitialCards(participants);
        breakLine();
    }

    private void printParticipantsInitialCards(Participants participants) {
        Participant dealer = participants.getDealer();
        printParticipantCards(dealer, dealer.getInitialCards());

        for (Participant participant : participants.getPlayers()) {
            printParticipantCards(participant, participant.getInitialCards());
        }
    }

    public void printParticipantCards(Participant participant) {
        printParticipantCards(participant, participant.getCards());
    }

    private void printParticipantCards(Participant participant, List<Card> cards) {
        String cardsFormat = String.format(Format.CARDS.format,
                participant.getName(), getCardsFormat(cards));
        System.out.println(cardsFormat);
    }

    private String getCardsFormat(List<Card> cards) {
        return cards.stream()
                .map(this::getCardFormat)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardFormat(Card card) {
        return card.getNumberSignature() + card.getTypeName();
    }

    public void printBusted(String name) {
        String format = String.format(Format.BUSTED.format, name);
        System.out.println(format);
    }

    public void printDealerHitCount(int hitCardCount) {
        if (hitCardCount == NO_MORE_CARD_COUNT) {
            System.out.println(DEALER_NO_MORE_CARD_MESSAGE);
            return;
        }

        breakLine();
        String format = String.format(Format.DEALER_MORE_CARDS.format, hitCardCount);
        System.out.println(format);
    }

    public void printCardsWithScore(Participants participants) {
        breakLine();
        Participant dealer = participants.getDealer();
        System.out.println(getCardsWithScoreFormat(dealer));

        for (Participant player : participants.getPlayers()) {
            System.out.println(getCardsWithScoreFormat(player));
        }
    }

    private String getCardsWithScoreFormat(Participant participant) {
        BlackjackScore participantScore = participant.calculateBlackjackScore();
        return String.format(Format.CARDS_WITH_SCORE.format,
                participant.getName(), getCardsFormat(participant.getCards()), participantScore.getValue());
    }

    public void printFinalResult(Participant dealer, BlackjackGameResult blackjackGameResult) {
        breakLine();
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerFinalResult(dealer, blackjackGameResult);

        blackjackGameResult.getDealerResultsAgainstParticipants().forEach(
                (key, value) -> printPlayerResult(key, value.convertToOpposite())
        );
    }

    private void printDealerFinalResult(Participant dealer, BlackjackGameResult blackjackGameResult) {
        StringBuilder dealerResultsFormat = new StringBuilder();
        for (Result result : Result.values()) {
            dealerResultsFormat.append(getResultFormat(result, blackjackGameResult.getResultCount(result)));
        }

        String format = String.format(Format.RESULT.format, dealer.getName(), dealerResultsFormat);
        System.out.println(format);
    }

    private String getResultFormat(Result result, int resultCount) {
        if (resultCount == SKIP_COUNT) {
            return "";
        }

        return resultCount + result.getValue();
    }

    private void printPlayerResult(Participant player, Result result) {
        String format = String.format(Format.RESULT.format,
                player.getName(), result.getValue());
        System.out.println(format);
    }

    public void printErrorMessage(String message) {
        String format = String.format(Format.ERROR.format, message);
        System.out.println(format);
    }

    private void breakLine() {
        System.out.print(System.lineSeparator());
    }

    public enum Format {
        CARD_DISTRIBUTION("딜러와 %s에게 2장을 나누었습니다."),
        CARDS("%s카드: %s"),
        CARDS_WITH_SCORE(CARDS.format + " - 결과: %d"),
        BUSTED("%s는 버스트 되었습니다."),
        RESULT("%s: %s"),
        DEALER_MORE_CARDS("딜러는 16이하라 %d장의 카드를 더 받았습니다."),
        ERROR("[Error] %s");

        private final String format;

        Format(String format) {
            this.format = format;
        }
    }
}
