package view;

import static domain.BlackjackGame.DEALER_DRAW_BOUND;
import static domain.BlackjackGame.INIT_DRAW_COUNT;
import static domain.participant.Participants.DEALER_NAME;

import domain.FinalResult;
import domain.card.Card;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;

public class OutputView {

    public void printInitHandCard(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        printInitHandCardInfo(dealer, players);
        printDealerInitHandCard(dealer);
        printPlayerInitHandCard(players);
    }


    public void printCurrentHandCard(final Participant participant) {
        printParticipantHandCard(participant);
        System.out.println();
    }

    public void printDealerAdditionalDraw() {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.\n", DEALER_NAME, DEALER_DRAW_BOUND);
    }

    public void printHandResults(final Participants participants) {
        System.out.println();
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        printCardResult(dealer);
        printPlayerCardResult(players);

        System.out.println();
    }

    public void printWhiteLine() {
        System.out.println();
    }

    public void printGameResults(final List<FinalResult> finalResults) {
        System.out.println("\n## 최종 승패");

        printDealerFinalResult(finalResults);
        printPlayerFinalResult(finalResults);
    }


    private static void printInitHandCardInfo(final Participant dealer, final List<Participant> players) {
        final StringBuilder playerNames = new StringBuilder();
        System.out.printf("\n%s와 ", dealer.getName());
        for (final Participant player : players) {
            playerNames.append(player.getName()).append(", ");
        }
        playerNames.delete(playerNames.length() - 2, playerNames.length());
        System.out.printf("%s에게 %d장을 나누었습니다.\n", playerNames, INIT_DRAW_COUNT);
    }

    private static void printDealerInitHandCard(final Participant dealer) {
        System.out.printf("%s카드: %s\n", dealer.getName(), dealer.getHand().getFirst().getCardDescription());
    }

    private static void printPlayerInitHandCard(final List<Participant> players) {
        for (final Participant player : players) {
            printParticipantHandCard(player);
            System.out.println();
        }
        System.out.println();
    }

    private static void printParticipantHandCard(final Participant player) {
        System.out.printf("%s카드: ", player.getName());

        final StringBuilder cardDescriptions = new StringBuilder();
        final List<Card> handCards = player.getHand();
        for (final Card handCard : handCards) {
            cardDescriptions.append(handCard.getCardDescription()).append(", ");
        }
        cardDescriptions.delete(cardDescriptions.length() - 2, cardDescriptions.length());
        System.out.print(cardDescriptions);
    }

    private static void printCardResult(final Participant dealer) {
        printParticipantHandCard(dealer);
        printScore(dealer);
    }

    private static void printPlayerCardResult(final List<Participant> players) {
        for (final Participant player : players) {
            printCardResult(player);
        }
    }

    private static void printScore(final Participant dealer) {
        System.out.printf(" - 결과: %d\n", dealer.getScore());
    }

    private static void printDealerFinalResult(final List<FinalResult> finalResults) {
        for (final FinalResult finalResult : finalResults) {
            if (finalResult.isDealer()) {
                System.out.printf("%s: ", finalResult.name());

                printDealerResultDetail(finalResult);
                break;
            }
        }
    }

    private static void printPlayerFinalResult(final List<FinalResult> finalResults) {
        for (final FinalResult finalResult : finalResults) {
            if (!finalResult.isDealer()) {
                System.out.printf("%s: ", finalResult.name());
                printPlayerResultDetail(finalResult);
            }
        }
    }

    private static void printDealerResultDetail(final FinalResult finalResult) {
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

    private static void printPlayerResultDetail(final FinalResult finalResult) {
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
}
