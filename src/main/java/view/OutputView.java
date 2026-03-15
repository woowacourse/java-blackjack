package view;

import domain.game.GameResult;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantResultInfo;

import java.util.stream.Collectors;

public class OutputView {
    public static final String DEALER_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String FINAL_PROFIT_MESSAGE = "## 최종 수익";
    private static final String COMMA = ", ";
    private static final String SCORE_MESSAGE = " - 결과: %s";
    private static final String DISTRIBUTION_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String CARD_MESSAGE = "%s카드: ";

    public void printInitialDistribution(Players players, Dealer dealer) {
        printDistributionMessage(dealer, players);
        printDealerInitialCard(dealer);
        printPlayersInitialCards(players);
    }

    public void printGameResult(GameResult gameResult) {
        printFinalProfitMessage();

        for (ParticipantResultInfo playerResultInfo : gameResult.playersResultInfos()) {
            System.out.println(playerResultInfo.name() + ": " + playerResultInfo.profit());
        }

        ParticipantResultInfo dealerResultInfo = gameResult.dealerResultInfo();
        System.out.println(dealerResultInfo.name() + ": " + dealerResultInfo.profit());
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
        String dealerName = dealer.name();
        String playerNames = String.join(COMMA, players.getPlayerNames().stream().toList());
        String distributionMessage = String.format(DISTRIBUTION_MESSAGE, dealerName, playerNames);

        System.out.println(distributionMessage);
    }

    private void printDealerInitialCard(Dealer dealer) {
        Card firstCard = dealer.card();
        String dealerCard = firstCard.name();

        System.out.println(String.format(CARD_MESSAGE, dealer.name()) + dealerCard);
    }

    private void printPlayersInitialCards(Players players) {
        for (Player player : players.getPlayers()) {
            printParticipantCards(player);
        }
    }

    private String formatParticipantCards(Participant participant) {
        String joinedCards = participant.cards()
                .stream()
                .map(Card::name)
                .collect(Collectors.joining(COMMA));

        return String.format(CARD_MESSAGE, participant.name()) + joinedCards;
    }

    private void printFinalProfitMessage() {
        System.out.println(FINAL_PROFIT_MESSAGE);
    }
}
