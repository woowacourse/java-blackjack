package view;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;
import static domain.Shape.CLUB;
import static domain.Shape.DIAMOND;
import static domain.Shape.HEART;
import static domain.Shape.SPADE;

import domain.Card;
import domain.GameResult;
import domain.Participant;
import domain.ParticipantsResult;
import domain.PlayerResult;
import domain.Rank;
import domain.Shape;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String SPREAD_NAME_RESULT = "%s카드: ";
    private static final String SPREAD_CARD_RESULT = "%s%s";
    private static final String SPREAD_PLAYER_MESSAGE = "%s에게 2장을 나누었습니다.";
    private static final String RESULT_MESSAGE = "## 최종 승패";

    public void printInitialParticipantHands(List<Participant> participants) {
        System.out.println();
        System.out.print("딜러와 ");
        String result = participants.stream().skip(1).map(Participant::getName)
            .collect(Collectors.joining(", "));
        System.out.print(SPREAD_PLAYER_MESSAGE.formatted(result));
        System.out.println();

        for (Participant participant : participants) {
            System.out.print(SPREAD_NAME_RESULT.formatted(participant.getName()));

            List<Card> shownCard = participant.getShownCard();
            String cardMessage = shownCard.stream().map(this::formatCard)
                .collect(Collectors.joining(", "));
            System.out.println(cardMessage);
        }
        System.out.println();
    }

    private String formatCard(Card card) {
        return String.format(SPREAD_CARD_RESULT, formatCardRank(card.getRank()),
            formatCardShape(card.getShape()));
    }

    public void printDealerPickMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
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
        System.out.print(SPREAD_NAME_RESULT.formatted(participant.getName()));

        List<Card> shownCard = participant.getShownCard();
        String cardMessage = formatCards(shownCard);
        System.out.println(cardMessage);
    }

    public void printFullParticipantInfo(Participant participant) {
        System.out.println(formatFullParticipantInfo(participant));
    }

    private String formatFullParticipantInfo(Participant participant) {
        String name = participant.getName();
        String cardsMessage = formatCards(participant.getCards());
        int totalValue = participant.getTotalValue();
        return String.format("%s카드: %s - 결과: %d", name, cardsMessage, totalValue);
    }

    private String formatCards(List<Card> cards) {
        return cards.stream().map(this::formatCard).collect(Collectors.joining(", "));
    }

    public void printGameResult(ParticipantsResult participantsResult) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        Map<GameResult, Integer> dealerResult = participantsResult.dealerResult().getDealerResult();
        String dealerName = "딜러: ";
        System.out.print(dealerName);
        String dealerWinMessage = formatDealerWinMessage(dealerResult.get(WIN));
        System.out.print(dealerWinMessage);
        String dealerLoseMessage = formatDealerLoseMessage(dealerResult.get(LOSE));
        System.out.print(dealerLoseMessage);
        String dealerDrawMessage = formatDealerDrawMessage(dealerResult.get(DRAW));
        System.out.print(dealerDrawMessage);
        System.out.println();

        List<PlayerResult> playerResults = participantsResult.playerResults();
        for (PlayerResult playerResult : playerResults) {
            System.out.printf("%s: %s%n", playerResult.getPlayerName(),
                formatGameResult(playerResult.getGameResult()));
        }
    }

    private String formatDealerWinMessage(Integer integer) {
        if (integer == null) {
            return "";
        }
        return String.format("%d승 ", integer);
    }

    private String formatDealerLoseMessage(Integer integer) {
        if (integer == null) {
            return "";
        }
        return String.format("%d패 ", integer);
    }

    private String formatDealerDrawMessage(Integer integer) {
        if (integer == null) {
            return "";
        }
        return String.format("%d무 ", integer);
    }

    private String formatGameResult(GameResult gameResult) {
        if (gameResult == WIN) {
            return "승";
        }
        if (gameResult == DRAW) {
            return "무";
        }
        if (gameResult == LOSE) {
            return "패";
        }
        return "";
    }

    public void printBlankLine() {
        System.out.println();
    }
}
