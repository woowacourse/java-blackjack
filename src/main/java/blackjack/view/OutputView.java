package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.ScoreResult;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printInitialCardStatus(Participants participants) {
        System.out.println();
        List<Participant> participantsGroup = participants.getParticipants();
        for (Participant participant : participantsGroup) {
            System.out.println(participant.getName() + ": " + printCards(participant.showInitCards()));
        }
        System.out.println();
    }

    private static String printCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::printCard)
                .collect(Collectors.joining(", "));
    }

    private static String printCard(Card card) {
        return card.getCardValue().getValue() + "" + card.getCardType().getValue();
    }

    public static void printParticipantCards(Participant participant) {
        System.out.println(participant.getName() + ": " + printCards(participant.showCards()));
    }

    public static void printAllParticipantsCards(Participants participants) {
        System.out.println();
        List<Participant> participantsGroup = participants.getParticipants();
        for (Participant participant : participantsGroup) {
            System.out.println(participant.getName() + ": "
                    + printCards(participant.showCards()) + " - 결과:" + participant.sumTotalScore());
        }
        System.out.println();
    }

    public static void printAllEarnings(DealerResult dealerResult, List<ScoreResult> scoreResults) {
        System.out.println("## 최종 수익");
        System.out.println(dealerResult.getName() + ": " + (int) dealerResult.calculateEarnings());
        for (ScoreResult scoreResult : scoreResults) {
            System.out.println(scoreResult.getPlayerName() + ": " + (int) scoreResult.calculateEarnings());
        }
    }
}
