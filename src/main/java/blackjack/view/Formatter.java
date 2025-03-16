package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Player;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.ParticipantResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Formatter {

    private static final int BUSTED_STANDARD_VALUE = 21;

    private Formatter() {
    }

    public static String formatPlayerCardResult(ParticipantResult participantResult) {
        String message = formatPlayerCardStatus(participantResult.getParticipant()) + " - 결과: ";
        return message + formatCardResultValue(participantResult);
    }

    public static String formatPlayerCardStatus(Participant participant) {
        if (participant.doesHaveName()) {
            Player player = (Player) participant;
            return player.getName() + "카드: " + formatStartingCardStatus(player);
        }
        throw new IllegalArgumentException("해당 참가자는 이름이 없습니다.");
    }

    public static String formatMultipleCardStatusWithName(Participant participant) {
        if (participant.doesHaveName()) {
            Player player = (Player) participant;
            return player.getName() + "카드: " + formatStartingCardStatus(player);
        }
        throw new IllegalArgumentException("이름이 없는 참가자의 상태를 출력할 수 없습니다.");
    }

    public static String formatSingleCardStatus(Participant participant) {
        List<Card> cards = participant.getCards();
        return "딜러카드: " + Formatter.formatCard(cards.getFirst());
    }

    public static List<String> formatDefenderCardResult(List<ParticipantResult> participantResults) {
        return participantResults.stream()
                .map(result -> formatDefendersCardStatus(result.getParticipant()) + " - 결과: " + formatCardResultValue(
                        result))
                .toList();
    }

    private static String formatDefendersCardStatus(Participant participant) {
        return "딜러카드: " + formatStartingCardStatus(participant);
    }

    private static String formatCardResultValue(ParticipantResult participantResult) {
        int value = participantResult.getValue();

        if (value > BUSTED_STANDARD_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String formatStartingCardStatus(Participant participant) {
        return participant.getCards().stream()
                .map(Formatter::formatCard)
                .collect(Collectors.joining(", "));
    }

    public static String formatDefenderGameResult(ParticipantResult participantResult) {
        Map<GameResultType, Integer> countsOfResultTypes = participantResult.getCountsOfResultTypes();

        return Arrays.stream(GameResultType.values())
                .map(gameResultType -> formatResultCount(countsOfResultTypes, gameResultType))
                .collect(Collectors.joining(" "));
    }

    public static String formatChallengerGameResult(List<ParticipantResult> participantResults) {
        List<String> result = new ArrayList<>();

        for (ParticipantResult participantResult : participantResults) {
            Player player = (Player) participantResult.getParticipant();

            String nameOfPlayer = player.getName();

            Map<GameResultType, Integer> countsOfResultTypes = participantResult.getCountsOfResultTypes();
            for (GameResultType gameResultType : countsOfResultTypes.keySet()) {
                result.add(nameOfPlayer + ": " + formatGameResult(gameResultType));
            }
        }

        return result.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatResultCount(Map<GameResultType, Integer> results, GameResultType gameResultType) {
        Integer count = results.getOrDefault(gameResultType, 0);
        if (count == 0) {
            return "";
        }

        return count + formatGameResult(gameResultType);
    }

    private static String formatGameResult(GameResultType gameResultType) {
        if (gameResultType.equals(GameResultType.WIN)) {
            return "승";
        }

        if (gameResultType.equals(GameResultType.LOSE)) {
            return "패";
        }

        return "무";
    }

    public static String formatCard(Card card) {
        CardRank rank = card.getRank();
        CardSuit suit = card.getSuit();

        String formatCardRank = formatCardRank(rank);
        String formatCardSuit = formatCardSuit(suit);

        return formatCardRank + formatCardSuit;
    }

    private static String formatCardRank(CardRank cardRank) {
        if (cardRank == CardRank.ACE) {
            return "A";
        }

        if (cardRank == CardRank.KING) {
            return "K";
        }

        if (cardRank == CardRank.QUEEN) {
            return "Q";
        }

        if (cardRank == CardRank.JACK) {
            return "J";
        }

        List<Integer> values = cardRank.getValues();
        return String.valueOf(values.getFirst());
    }

    private static String formatCardSuit(CardSuit cardSuit) {
        if (cardSuit == CardSuit.SPADE) {
            return "스페이드";
        }

        if (cardSuit == CardSuit.CLUB) {
            return "클로버";
        }
        if (cardSuit == CardSuit.DIAMOND) {
            return "다이아몬드";
        }
        return "하트";
    }
}
