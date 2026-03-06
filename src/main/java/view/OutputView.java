package view;

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
    private static final String RESULT = " - 결과: ";

    private static final String DISTRIBUTION_PREFIX = "딜러와 ";
    private static final String DISTRIBUTION_SUFFIX = "에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_PREFIX = "딜러카드: ";
    private static final String CARD_PREFIX = "카드: ";
    private static final String DEALER_RESULT = "딜러: ";
    private static final String WIN = "승 ";
    private static final String TIE = "무 ";
    private static final String LOSE = "패 ";

    public void printInitialDistribution(Players players, Dealer dealer) {
        printDistributionMessage(players);
        printDealerInitialCard(dealer);
        printPlayersInitialCards(players);
    }

    public void printParticipantCards(Participant participant) {
        System.out.println(formatParticipantCards(participant));
    }

    public void printDealerReceiveMessage() {
        System.out.println(DEALER_ONE_MORE_CARD_MESSAGE);
    }

    public void printFinalResult(Participant participant) {
        String cardFormat = formatParticipantCards(participant);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cardFormat).append(RESULT).append(participant.score());

        System.out.println(stringBuilder);
    }

    private void printDistributionMessage(Players players) {
        List<String> playerNames = players.getPlayerNames();
        StringBuilder stringBuilder = new StringBuilder();

        String names = String.join(COMMA, playerNames);
        stringBuilder.append(DISTRIBUTION_PREFIX).append(names).append(DISTRIBUTION_SUFFIX);

        System.out.println(stringBuilder);
    }

    private void printDealerInitialCard(Dealer dealer) {
        Card firstCard = dealer.getFirstCard();
        String dealerCard = firstCard.name();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DEALER_CARD_PREFIX).append(dealerCard);

        System.out.println(stringBuilder);
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

        StringBuilder stringBuilder = new StringBuilder();
        String joinedCards = String.join(COMMA, cards);
        stringBuilder.append(participant.name()).append(CARD_PREFIX).append(joinedCards);

        return stringBuilder.toString();
    }

    public void printWinOrLoseMessage() {
        System.out.println(FINAL_WIN_OR_LOSE_MESSAGE);
    }

    public void printDealerResult(DealerResultInfo dealerResult) {
        StringBuilder stringBuilder = new StringBuilder(DEALER_RESULT);

        if (dealerResult.winCount() > 0) {
            stringBuilder.append(dealerResult.winCount()).append(WIN);
        }
        if (dealerResult.tieCount() > 0) {
            stringBuilder.append(dealerResult.tieCount()).append(TIE);
        }
        if (dealerResult.loseCount() > 0) {
            stringBuilder.append(dealerResult.loseCount()).append(LOSE);
        }

        System.out.println(stringBuilder.toString().trim());
    }

    public void printPlayersResult(List<PlayerResultInfo> playersResult) {
        for (PlayerResultInfo playerResult : playersResult) {
            System.out.println(playerResult.name() + COLON + playerResult.winningStatus().getSymbol());
        }
    }
}
