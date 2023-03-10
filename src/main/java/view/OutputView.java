package view;

import domain.BlackjackGame;
import domain.BlackjackScore;
import domain.Card;
import domain.Cards;
import domain.DealerResult;
import domain.Participant;
import domain.Players;
import domain.Result;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final int NO_MORE_CARD_COUNT = 0;
    private static final int SKIP_COUNT = 0;
    private static final String DEALER_NO_MORE_CARD_MESSAGE = "딜러의 카드합이 17이상이라 카드를 더 받지 않았습니다.";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String CARD_DISTRIBUTION_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String CARDS_FORMAT = "%s카드: %s";
    private static final String CARDS_WITH_SCORE_FORMAT = CARDS_FORMAT + " - 결과: %d";
    private static final String BUSTED_FORMAT = "%s는 버스트 되었습니다.";
    private static final String RESULT_FORMAT = "%s: %s";
    private static final String DEALER_MORE_CARDS_FORMAT = "딜러는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String ERROR_FORMAT = "[Error] %s";

    public void printInitialCards(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        String playerNames = players.getPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.joining(DELIMITER));

        breakLine();
        String initialCardsPrefixFormat = String.format(CARD_DISTRIBUTION_FORMAT, playerNames);
        System.out.println(initialCardsPrefixFormat);
        printParticipantsInitialCards(blackjackGame);
        breakLine();
    }

    private void printParticipantsInitialCards(BlackjackGame blackjackGame) {
        Participant dealer = blackjackGame.getDealer();
        printParticipantCards(dealer, dealer.getInitialOpeningCards());

        Players players = blackjackGame.getPlayers();
        for (Participant participant : players.getPlayers()) {
            printParticipantCards(participant, participant.getInitialOpeningCards());
        }
    }

    public void printParticipantCards(Participant participant) {
        Cards cards = participant.getCards();
        printParticipantCards(participant, cards);
    }

    private void printParticipantCards(Participant participant, Cards cards) {
        String cardsFormat = String.format(CARDS_FORMAT,
                participant.getName(), getCardsFormat(cards));
        System.out.println(cardsFormat);
    }

    private String getCardsFormat(Cards cards) {
        return cards.getCards().stream()
                .map(this::getCardFormat)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardFormat(Card card) {
        return card.getNumberSignature() + card.getTypeName();
    }

    public void printBusted(String name) {
        String format = String.format(BUSTED_FORMAT, name);
        System.out.println(format);
    }

    public void printDealerHitCount(int hitCardCount) {
        if (hitCardCount == NO_MORE_CARD_COUNT) {
            System.out.println(DEALER_NO_MORE_CARD_MESSAGE);
            return;
        }

        breakLine();
        String format = String.format(DEALER_MORE_CARDS_FORMAT, hitCardCount);
        System.out.println(format);
    }

    public void printCardsWithScore(BlackjackGame blackjackGame) {
        breakLine();
        Participant dealer = blackjackGame.getDealer();
        System.out.println(getCardsWithScoreFormat(dealer));

        Players players = blackjackGame.getPlayers();
        for (Participant player : players.getPlayers()) {
            System.out.println(getCardsWithScoreFormat(player));
        }
    }

    private String getCardsWithScoreFormat(Participant participant) {
        BlackjackScore participantScore = participant.calculateBlackjackScore();
        return String.format(CARDS_WITH_SCORE_FORMAT,
                participant.getName(), getCardsFormat(participant.getCards()), participantScore.getValue());
    }

    public void printFinalResult(Participant dealer, DealerResult dealerResult) {
        breakLine();
        System.out.println(FINAL_RESULT_MESSAGE);
        printDealerFinalResult(dealer, dealerResult);

        dealerResult.getResultsAgainstParticipants().forEach(
                (key, value) -> printPlayerResult(key, value.convertToOpposite())
        );
    }

    private void printDealerFinalResult(Participant dealer, DealerResult dealerResult) {
        StringBuilder dealerResultsFormat = new StringBuilder();
        for (Result result : Result.values()) {
            dealerResultsFormat.append(getResultFormat(result, dealerResult.getResultCount(result)));
        }

        String format = String.format(RESULT_FORMAT, dealer.getName(), dealerResultsFormat);
        System.out.println(format);
    }

    private String getResultFormat(Result result, int resultCount) {
        if (resultCount == SKIP_COUNT) {
            return "";
        }

        return resultCount + result.getValue();
    }

    private void printPlayerResult(Participant player, Result result) {
        String format = String.format(RESULT_FORMAT,
                player.getName(), result.getValue());
        System.out.println(format);
    }

    public void printErrorMessage(String message) {
        String format = String.format(ERROR_FORMAT, message);
        System.out.println(format);
    }

    private void breakLine() {
        System.out.print(System.lineSeparator());
    }
}
