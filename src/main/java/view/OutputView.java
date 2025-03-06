package view;

import static domain.Shape.CLUB;
import static domain.Shape.DIAMOND;
import static domain.Shape.HEART;
import static domain.Shape.SPADE;

import domain.Card;
import domain.Participant;
import domain.Rank;
import domain.Shape;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String SPREAD_NAME_RESULT = "%s카드: ";
    private static final String SPREAD_CARD_RESULT = "%s%s";
    private static final String SPREAD_PLAYER_MESSAGE = "%s에게 2장을 나누었습니다.";

    public void printInitialParticipantHands(List<Participant> participants) {
        System.out.print("딜러와 ");
        String result = participants.stream()
            .skip(1)
            .map(Participant::getName)
            .collect(Collectors.joining(", "));
        System.out.print(SPREAD_PLAYER_MESSAGE.formatted(result));
        System.out.println();

        for (Participant participant : participants) {
            System.out.print(SPREAD_NAME_RESULT.formatted(
                participant.getName()));

            List<Card> shownCard = participant.getShownCard();
            String cardMessage = shownCard.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
            System.out.println(cardMessage);
        }
    }

    private String formatCard(Card card) {
        return String.format(SPREAD_CARD_RESULT,
            formatCardRank(card.getRank()),
            formatCardShape(card.getShape())
        );
    }


    private String formatCardRank(Rank rank) {
        if (rank == Rank.A) {
            return "A";
        }
        if (rank == Rank.KING) {
            return "K";
        }
        if (rank == Rank.QUEEN) {
            return "Q";
        }
        if (rank == Rank.JACK) {
            return "J";
        }
        return rank.getValue() + "";
    }

    private String formatCardShape(Shape shape) {
        if (shape == DIAMOND) {
            return "다이아몬드";
        }
        if (shape == HEART) {
            return "하트";
        }
        if (shape == CLUB) {
            return "클로버";
        }
        if (shape == SPADE) {
            return "스페이드";
        }
        return "";
    }

    public void printParticipantHand(Participant participant) {
        System.out.print(SPREAD_NAME_RESULT.formatted(
                participant.getName()));

        List<Card> shownCard = participant.getShownCard();
        String cardMessage = shownCard.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
        System.out.println(cardMessage);
    }
}
