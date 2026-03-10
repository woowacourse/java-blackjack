package view;

import static constant.BlackjackConstant.DEALER_DRAW_BOUND;
import static constant.BlackjackConstant.DEALER_NAME;
import static constant.BlackjackConstant.INIT_DRAW_COUNT;

import domain.Card;
import domain.Participant;
import domain.Participants;
import java.util.List;
import service.FinalResult;

public class OutputView {

    public void printInitHandCard(Participants participants) {
        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        printInitHandCardInfo(dealer, players);
        printDealerInitHandCard(dealer);
        printPlayerInitHandCard(players);
    }

    private static void printInitHandCardInfo(Participant dealer, List<Participant> players) {
        StringBuilder playerNames = new StringBuilder();
        System.out.printf("\n%s와 ", dealer.getName());
        for (Participant player : players) {
            playerNames.append(player.getName() + ", ");
        }
        playerNames.delete(playerNames.length() - 2, playerNames.length());
        System.out.printf("%s에게 %d장을 나누었습니다.\n", playerNames, INIT_DRAW_COUNT);
    }

    private static void printPlayerInitHandCard(List<Participant> players) {
        for (Participant player : players) {
            printParticipantHandCard(player);
            System.out.println();
        }
        System.out.println();
    }

    private static void printDealerInitHandCard(Participant dealer) {
        System.out.printf("%s카드: %s\n", dealer.getName(), dealer.getHandCards().getFirst().getCardDescription());
    }

    private static void printParticipantHandCard(Participant player) {
        System.out.printf("%s카드: ", player.getName());

        StringBuilder cardDescriptions = new StringBuilder();
        List<Card> handCards = player.getHandCards();
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
        List<Participant> players = participants.getPlayers();

        printDealerCardResult(dealer);
        printPlayerCardResult(players);

        System.out.println();
    }

    private static void printDealerCardResult(Participant dealer) {
        printParticipantHandCard(dealer);
        printScore(dealer);
    }

    private static void printPlayerCardResult(List<Participant> players) {
        for (Participant player : players) {
            printDealerCardResult(player);
        }
    }

    private static void printScore(Participant dealer) {
        System.out.printf(" - 결과: %d\n", dealer.getScore());
    }

    public void printFinalResults(List<FinalResult> finalResults) {
        System.out.println("\n## 최종 승패");

        printDealerFinalResult(finalResults);
        printPlayerFinalResult(finalResults);
    }

    private static void printDealerFinalResult(List<FinalResult> finalResults) {
        for (FinalResult finalResult : finalResults) {
            if (finalResult.isDealer()) {
                System.out.printf("%s: ", finalResult.name());

                printDealerResultDetail(finalResult);
                break;
            }
        }
    }

    private static void printPlayerFinalResult(List<FinalResult> finalResults) {
        for (FinalResult finalResult : finalResults) {
            if (!finalResult.isDealer()) {
                System.out.printf("%s: ", finalResult.name());
                printPlayerResultDetail(finalResult);
            }
        }
    }

    private static void printDealerResultDetail(FinalResult finalResult) {
        if (finalResult.winCount() != 0) {
            System.out.printf("%d승 ", finalResult.winCount());
        }
        if (finalResult.drawCount() != 0) {
            System.out.printf("%d무 ", finalResult.drawCount());
        }
        if (finalResult.loseCount() != 0) {
            System.out.printf("%d패", finalResult.loseCount());
        }
        System.out.println();
    }

    private static void printPlayerResultDetail(FinalResult finalResult) {
        if (finalResult.winCount() != 0) {
            System.out.println("승");
        }
        if (finalResult.drawCount() != 0) {
            System.out.println("무");
        }
        if (finalResult.loseCount() != 0) {
            System.out.println("패");
        }
    }

    public void printWhiteLine() {
        System.out.println();
    }
}
