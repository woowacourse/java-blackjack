package ui;

import domain.blackjackgame.GameResult;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.stream.Collectors;

public class OutputView {
    private static final int DEALER_INITIAL_CARD_PRINT_COUNT = 1;

    public void printInitialCards(Participants participants) {
        Dealer dealer = participants.getDealer();
        String playerNames = String.join(", ", participants.getPlayerNames());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), playerNames);

        System.out.println(formatCardsMessage(dealer, DEALER_INITIAL_CARD_PRINT_COUNT));
        for (Player player : participants.getPlayers()) {
            System.out.println(formatCardsMessage(player));
        }
        System.out.println();
    }

    public void printAllCards(Participant participant) {
        System.out.println(formatCardsMessage(participant));
    }

    private String formatCardsMessage(Participant participant) {
        return formatCardsMessage(participant, participant.getAllCards().size());
    }

    private String formatCardsMessage(Participant participant, int cardPrintCount) {
        String cardsMessage = participant.getAllCards().stream()
                .limit(cardPrintCount)
                .map(this::generateCardMessage)
                .collect(Collectors.joining(", "));

        return String.format("%s카드: %s", participant.getName(), cardsMessage);
    }

    private String generateCardMessage(Card card) {
        return card.getDenominationExpression() + card.getSuitName();
    }

    public void printDealerReceiveCardMessage() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printCardsWithScore(Participants participants) {
        System.out.println();
        for (Participant participant : participants.getParticipants()) {
            System.out.println(formatScoreMessage(participant));
        }
    }

    private String formatScoreMessage(Participant participant) {
        return formatCardsMessage(participant) + " - 결과: " + participant.calculateScore();
    }

    public void printGameResult(GameResult gameResult) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        System.out.println("딜러: " + gameResult.getDealerResult());
        gameResult.getPlayerResult()
                .forEach((key, value) -> System.out.printf("%s: %s%n", key.getName(), value.toPlainString()));
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
