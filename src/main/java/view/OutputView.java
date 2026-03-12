package view;

import static constant.BlackjackConstant.DEALER_DRAW_BOUND;
import static constant.BlackjackConstant.DEALER_NAME;
import static constant.BlackjackConstant.INIT_DRAW_COUNT;

import domain.card.Card;

import java.util.List;
import java.util.Map;

import domain.pariticipant.*;
import domain.result.*;

public class OutputView {

    public void printInitHandCard(Participants participants) {
        Dealer dealer = participants.getDealer();
        Players players = participants.getPlayers();
        printInitHandCardInfo(dealer, players);
        printDealerInitHandCard(dealer);
        printPlayerInitHandCard(players);
    }

    private static void printInitHandCardInfo(Participant dealer, Players players) {
        StringBuilder playerNames = new StringBuilder();
        System.out.printf("\n%s와 ", dealer.getName());
        for (Participant player : players.getPlayers()) {
            playerNames.append(player.getName() + ", ");
        }
        playerNames.delete(playerNames.length() - 2, playerNames.length());
        System.out.printf("%s에게 %d장을 나누었습니다.\n", playerNames, INIT_DRAW_COUNT);
    }

    private static void printPlayerInitHandCard(Players players) {
        for (Participant player : players.getPlayers()) {
            printParticipantHandCard(player);
            System.out.println();
        }
        System.out.println();
    }

    private static void printDealerInitHandCard(Participant dealer) {
        System.out.printf("%s카드: %s\n", dealer.getName(), dealer.getHandCards().getFirst().getCardDescription());
    }

    private static void printParticipantHandCard(Participant participant) {
        System.out.printf("%s카드: ", participant.getName());

        StringBuilder cardDescriptions = new StringBuilder();
        List<Card> handCards = participant.getHandCards();
        for (Card handCard : handCards) {
            cardDescriptions.append(handCard.getCardDescription() + ", ");
        }
        cardDescriptions.delete(cardDescriptions.length() - 2, cardDescriptions.length());
        System.out.print(cardDescriptions);
    }


    public void printCurrentHandCard(Participant participant) {
        printParticipantHandCard(participant);
        System.out.println();
    }

    public void printDealerAdditionalDraw() {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.\n", DEALER_NAME, DEALER_DRAW_BOUND);
    }

    public void printCardResults(Participants participants) {
        System.out.println();
        Participant dealer = participants.getDealer();
        Players players = participants.getPlayers();

        printDealerCardResult(dealer);
        printPlayerCardResult(players);

    }

    private static void printDealerCardResult(Participant dealer) {
        printParticipantHandCard(dealer);
        printScore(dealer);
    }

    private static void printPlayerCardResult(Players players) {
        for (Participant player : players.getPlayers()) {
            printDealerCardResult(player);
        }
    }

    private static void printScore(Participant participant) {
        System.out.printf(" - 결과: %d\n", participant.getScore());
    }

    public void printFinalResults(MatchResult matchResult) {
        System.out.println("\n## 최종 승패");
        printDealerResult(matchResult.dealerMatchResult());
        printPlayerResult(matchResult.playersMatchResult());
    }


    private static void printDealerResult(DealerMatchResult dealerMatchResult) {
        System.out.printf("%s: ", DEALER_NAME);
        if (dealerMatchResult.winCount() != 0) {
            System.out.printf("%d승 ", dealerMatchResult.winCount());
        }
        if (dealerMatchResult.drawCount() != 0) {
            System.out.printf("%d무 ", dealerMatchResult.drawCount());
        }
        if (dealerMatchResult.loseCount() != 0) {
            System.out.printf("%d패", dealerMatchResult.loseCount());
        }
        System.out.println();
    }

    private static void printPlayerResult(PlayersMatchResult playersMatchResult) {
        Map<Player, MatchCase> playerMatchCaseMap = playersMatchResult.playerMatchResult();
        for (Player player : playerMatchCaseMap.keySet()) {
            System.out.printf("%s: %s\n", player.getName(), playerMatchCaseMap.get(player).getDescription());
        }
    }

    public void printBettingProfit(PlayersBettingProfit playersBettingProfit) {
        System.out.println("\n## 최종 수익");
        System.out.printf("딜러: %d\n", playersBettingProfit.calculateDealerProfit());
        Map<Player, Long> profitMap = playersBettingProfit.playersBettingProfit();
        for (Player player : profitMap.keySet()) {
            System.out.printf("%s: %d\n", player.getName(), profitMap.get(player));
        }
    }

    public void printWhiteLine() {
        System.out.println();
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
