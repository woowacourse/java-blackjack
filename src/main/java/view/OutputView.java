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

        System.out.printf("\n%s와 ", dealer.getName());
        StringBuilder playerNames = new StringBuilder();
        for (Participant player : players) {
            playerNames.append(player.getName() + ", ");
        }
        playerNames.delete(playerNames.length() - 2, playerNames.length());
        System.out.printf("%s에게 %d장을 나누었습니다.\n", playerNames, INIT_DRAW_COUNT);

        System.out.printf("%s카드: %s\n", dealer.getName(), dealer.getHandCards().getFirst().getCardDescription());

        for (Participant player : players) {
            printParticipantHandCard(player);
            System.out.println();
        }
        System.out.println();
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

        printParticipantHandCard(dealer);
        printScore(dealer);

        for (Participant player : players) {
            printParticipantHandCard(player);
            printScore(player);
        }

        System.out.println();
    }

    private static void printScore(Participant dealer) {
        System.out.printf(" - 결과: %d\n", dealer.getScore());
    }

    public void printWhiteLine() {
        System.out.println();
    }

    public void printFinalResults(List<FinalResult> finalResults) {
        System.out.println("\n## 최종 승패");

        for (FinalResult finalResult : finalResults) {
            if (finalResult.isDealer()) {
                System.out.printf("%s: ", finalResult.name());

                printDealerResultDetail(finalResult);
                break;
            }
        }

        for (FinalResult finalResult : finalResults) {
            if (!finalResult.isDealer()) {
                System.out.printf("%s: ", finalResult.name());

                printPlayerResultDetail(finalResult);
            }

        }
    }

    private static void printDealerResultDetail(FinalResult finalResult) {
        if (finalResult.win() != 0) {
            System.out.printf("%d승 ", finalResult.win());
        }
        if (finalResult.draw() != 0) {
            System.out.printf("%d무 ", finalResult.draw());
        }
        if (finalResult.lose() != 0) {
            System.out.printf("%d패", finalResult.lose());
        }
        System.out.println();
    }

    private static void printPlayerResultDetail(FinalResult finalResult) {
        if (finalResult.win() != 0) {
            System.out.println("승");
        }
        if (finalResult.draw() != 0) {
            System.out.println("무");
        }
        if (finalResult.lose() != 0) {
            System.out.println("패");
        }
    }
}
