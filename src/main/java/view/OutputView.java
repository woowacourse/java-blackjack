package view;

import domain.GameResult;
import domain.WinningStatus;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerResultInfo;
import dto.PlayerResultInfo;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static final String DEALER_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_WIN_OR_LOSE_MESSAGE = "## 최종 승패";
    private static final String COMMA = ", ";
    private static final String COLON = ": ";
    private static final String SCORE_MESSAGE = " - 결과: %s";
    private static final String DISTRIBUTION_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_MESSAGE = "%s카드: ";
    private static final String WIN = "승";
    private static final String TIE = "무";
    private static final String LOSE = "패";

    public void printInitialDistribution(Players players, Dealer dealer) {
        printDistributionMessage(dealer, players);
        printDealerInitialCard(dealer);
        printPlayersInitialCards(players);
    }

    public void printGameResult(GameResult gameResult, Dealer dealer) {
        printWinOrLoseMessage();
        printDealerResult(dealer.name(), gameResult.getDealerResult());
        printPlayersResult(gameResult.getPlayersResult());
    }

    public void printParticipantCards(Participant participant) {
        System.out.println(formatParticipantCards(participant));
    }

    public void printDealerReceiveMessage() {
        System.out.println(DEALER_ONE_MORE_CARD_MESSAGE);
    }

    public void printFinalResult(Participant participant) {
        String cardFormat = formatParticipantCards(participant);
        String scoreMessage = String.format(SCORE_MESSAGE, participant.score());

        System.out.println(cardFormat + scoreMessage);
    }

    private void printDistributionMessage(Dealer dealer, Players players) {
        List<String> names = players.getPlayerNames();

        String dealerName = dealer.name();
        String playerNames = String.join(COMMA, names);
        String distributionMessage = String.format(DISTRIBUTION_MESSAGE, dealerName, playerNames);

        System.out.println(distributionMessage);
    }

    private void printDealerInitialCard(Dealer dealer) {
        Card firstCard = dealer.getFirstCard();
        String dealerCard = firstCard.name();

        System.out.println(String.format(CARD_MESSAGE, dealer.name()) + dealerCard);
    }

    private void printPlayersInitialCards(Players players) {
        for (Player player : players.getPlayers()) {
            printParticipantCards(player);
        }
    }

    private String formatParticipantCards(Participant participant) {
        List<String> cards = new ArrayList<>();

        for (Card card : participant.getAllCards()) {
            cards.add(card.name());
        }

        String joinedCards = String.join(COMMA, cards);

        return String.format(CARD_MESSAGE, participant.name()) + joinedCards;
    }

    private void printWinOrLoseMessage() {
        System.out.println(FINAL_WIN_OR_LOSE_MESSAGE);
    }

    private void printDealerResult(String dealerName, DealerResultInfo dealerResult) {
        List<String> result = new ArrayList<>();

        if (dealerResult.winCount() > 0) {
            result.add(dealerResult.winCount() + WIN);
        }
        if (dealerResult.tieCount() > 0) {
            result.add(dealerResult.winCount() + TIE);
        }
        if (dealerResult.loseCount() > 0) {
            result.add(dealerResult.loseCount() + LOSE);
        }

        System.out.println(dealerName + ": " + String.join(" ", result));
    }

    private void printPlayersResult(List<PlayerResultInfo> playersResult) {
        for (PlayerResultInfo playerResult : playersResult) {
            printPlayerResult(playerResult);
        }
    }

    private void printPlayerResult(PlayerResultInfo playerResult) {
        if (playerResult.winningStatus() == WinningStatus.WIN) {
            System.out.println(playerResult.name() + COLON + WIN);
        }

        if (playerResult.winningStatus() == WinningStatus.TIE) {
            System.out.println(playerResult.name() + COLON + TIE);
        }

        if (playerResult.winningStatus() == WinningStatus.LOSE) {
            System.out.println(playerResult.name() + COLON + LOSE);
        }
    }
}
