package view;

import domain.card.Card;
import domain.game.BlackjackGame;
import domain.game.Result;
import domain.game.WinLose;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
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
        System.out.println(dealerNameAndCardsText(game.getDealer()));
        for (Player player : game.getPlayers()) {
            System.out.println(participantNameAndCardsText(player));
        }
        System.out.println();
    }

    public void printAllParticipantsCardsWithScore(BlackjackGame game) {
        System.out.println();
        System.out.println(
            participantNameAndCardsText(game.getDealer()) + scoreText(game.getDealer().calculateScore()));
        for (Player player : game.getPlayers()) {
            System.out.println(participantNameAndCardsText(player) + scoreText(player.calculateScore()));
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

    private String dealerNameAndCardsText(Dealer dealer) {
        return dealer.getName() + " 카드: " + cardText(dealer.findShowingCard());
    }

    private String participantNameAndCardsText(Participant participant) {
        return participant.getName() + " 카드: " + cardsText(participant.getCards());
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
            System.out.println(player.getName() + ": " + winLoseText(result.playerWinLose(player)));
        }
    }

    private String resultText(long resultCount, String resultSuffix) {
        if (resultCount == 0) {
            return "";
        }
        return resultCount + resultSuffix;
    }

    private String winLoseText(WinLose winLose) {
        if (winLose == WinLose.WIN) {
            return "승";
        }
        if (winLose == WinLose.LOSE) {
            return "패";
        }
        return "무";
    }
}
