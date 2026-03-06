package view;

import static constant.BlackjackConstant.INIT_DRAW_COUNT;

import domain.Card;
import domain.Participant;
import domain.Participants;
import java.util.List;

public class OutputView {

    public void printInitHandCard(Participants participants) {
        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        System.out.printf("%s와 ", dealer.getName());
        StringBuilder playerNames = new StringBuilder();
        for (Participant player : players) {
            playerNames.append(player.getName() + ", ");
        }
        playerNames.delete(playerNames.length() - 2, playerNames.length());
        System.out.printf("%s에게 %d장을 나누었습니다.\n", playerNames, INIT_DRAW_COUNT);

        System.out.printf("%s카드: %s\n", dealer.getName(), dealer.getHandCards().getFirst().getCardDescription());
        for (Participant player : players) {
            printParticipantHandCard(player);
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

        System.out.println(cardDescriptions);
    }

    public void printCurrentHandCard(Participant participant) {
        printParticipantHandCard(participant);
    }
}
