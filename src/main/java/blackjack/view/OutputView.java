package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.GameResult;
import blackjack.domain.card.HoldingCard;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ScoreResultDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA_DELIMITER = ", ";
    private static final String COLON = ": ";
    private static final String DASH = " - ";
    private static final String CARD = "카드";

    private OutputView() {
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printInitialCardStatus(List<ParticipantDto> participantsDto) {
        System.out.println(participantsDto.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(COMMA_DELIMITER)) + "에게 2장을 나누었습니다.");

        for (ParticipantDto participantDto : participantsDto) {
            printEachParticipantCard(participantDto);
        }
    }

    private static void printEachParticipantCard(ParticipantDto participantDto) {
        System.out.println(
                participantDto.getName() + CARD + COLON + showCards(participantDto.getCards().getHoldingCard()));
    }

    public static void printPlayerCards(String currentPlayerName, HoldingCard holdingCard) {
        System.out.println(currentPlayerName + CARD + COLON + showCards(holdingCard.getHoldingCard()));
    }

    public static void printPlayerFinalCards(List<ParticipantDto> playerFinalCardsAndScore) {
        for (ParticipantDto participantDto : playerFinalCardsAndScore) {
            printEachFinalCards(participantDto);
        }
    }

    private static void printEachFinalCards(ParticipantDto participantDto) {
        System.out.println(
                participantDto.getName() + CARD + COLON
                        + showCards(participantDto.getCards().getHoldingCard()) + DASH + "결과"
                        + COLON + participantDto.getSum());
    }

    public static void printFinalScore(ScoreResultDto finalScore) {
        System.out.println();
        System.out.println("## 최종승패");
        System.out.println("딜러" + COLON + finalScore.getDealerResult().keySet().stream()
                .map(gameResult -> finalScore.getDealerResult().get(gameResult).toString() + gameResult)
                .collect(Collectors.joining()));

        Map<String, GameResult> playersResult = finalScore.getPlayersResult();
        for (String playerName : playersResult.keySet()) {
            System.out.println(playerName + COLON + playersResult.get(playerName));
        }
    }

    private static String showCards(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_DELIMITER));
    }
}
