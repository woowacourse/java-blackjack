package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.BettingCashier;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";

    public void printDistributionMessage(BlackjackGame game) {
        String playerNames = game.getPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.printf(
                "%n딜러와 %s에게 %d장을 나누었습니다.%n", playerNames, BlackjackGame.STARTING_CARDS_AMOUNT);
    }

    public void printAllParticipantsCards(BlackjackGame game) {
        for (Participant participant : game.getParticipants()) {
            System.out.println(participantNameAndCardsText(participant));
        }
        System.out.println();
    }

    public void printAllParticipantsCardsWithScore(BlackjackGame game) {
        System.out.println();
        for (Participant participant : game.getParticipants()) {
            System.out.println(participantNameAndCardsText(participant) + scoreText(participant.calculateScore()));
        }
        System.out.println();
    }

    public void printParticipantCards(Participant participant) {
        System.out.println(participantNameAndCardsText(participant));
    }

    public void printBustMessage(Participant participant) {
        System.out.println(participant.getName() + "님은 버스트되었습니다.");
    }

    private String scoreText(int score) {
        return " - 결과: " + score;
    }

    private String participantNameAndCardsText(Participant participant) {
        return participant.getName() + " 카드: " + cardsText(participant.findShowingCards());
    }

    private String cardsText(List<Card> cards) {
        return cards.stream()
                .map(this::cardText)
                .collect(Collectors.joining(DELIMITER));
    }

    private String cardText(Card card) {
        return card.getRank().getDisplayName() + card.getSymbol().getDisplayName();
    }

    public void printDealerDrawMessage() {
        System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.THRESHOLD_SCORE);
    }

    public void printResult(BlackjackGame game, Result result) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %s%s%s%n",
                resultText(result.countDealerWins(), "승 "),
                resultText(result.countDealerTies(), "무 "),
                resultText(result.countDealerLoses(), "패")
        );
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + ": " + playerStateText(result, player));
        }
    }

    private String resultText(long resultCount, String resultSuffix) {
        if (resultCount == 0) {
            return "";
        }
        return resultCount + resultSuffix;
    }

    private String playerStateText(Result result, Player player) {
        return result.getPlayerState(player).getDescription();
    }

    public void printProfit(BlackjackGame game, BettingCashier cashier) {
        System.out.println("## 최종 수익");
        printProfit(game.getDealer(), cashier.findProfitOfDealer().getAmount());
        for (Player player : game.getPlayers()) {
            printProfit(player, cashier.findProfitOf(player).getAmount());
        }
    }

    public void printProfit(Participant participant, int profit) {
        System.out.printf("%s: %d%n", participant.getName(), profit);
    }

    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }
}
