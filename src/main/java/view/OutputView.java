package view;

import domain.card.Card;
import domain.participant.GameResult;
import domain.participant.Participant;
import domain.participant.ParticipantsResult;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String SPREAD_NAME_RESULT = "%s카드: ";
    private static final String SPREAD_CARD_RESULT = "%s%s";
    private static final String SPREAD_PLAYER_MESSAGE = "%s에게 2장을 나누었습니다.";
    private static final String RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_EXTRA_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_MESSAGE = "%s카드: %s - 결과: %d";


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
        return String.format(SPREAD_CARD_RESULT, card.rank().formatCardRank(),
            card.shape().formatCardShape());
    }

    public void printDealerPickMessageBy(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(DEALER_EXTRA_CARD_MESSAGE);
        }
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

    public void printCardMessageTest(Participant participant) {
        String cardResult = formatCards(participant.getCards());
        System.out.print(SPREAD_NAME_RESULT.formatted(participant.getName()));
        System.out.println(cardResult);
    }

    private String formatFullParticipantInfo(Participant participant) {
        String name = participant.getName();
        String cardsMessage = formatCards(participant.getCards());
        int totalValue = participant.getTotalValue();
        return String.format(GAME_RESULT_MESSAGE, name, cardsMessage, totalValue);
    }

    private String formatCards(List<Card> cards) {
        return cards.stream().map(this::formatCard).collect(Collectors.joining(", "));
    }

    public void printGameResult(ParticipantsResult participantsResult) {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
        Map<GameResult, Integer> dealerResult = participantsResult.getDealerResult();
        Map<Player, GameResult> playersResult = participantsResult.getPlayersResult();
        printDealerResult(dealerResult);
        printPlayersResult(playersResult);
    }

    private void printDealerResult(Map<GameResult, Integer> dealerResult) {
        System.out.print("딜러: ");
        System.out.println(dealerFormatter(dealerResult));
    }

    private void printPlayersResult(Map<Player, GameResult> playersResult) {
        for (Player player : playersResult.keySet()) {
            System.out.println(player.getName() + ": " + playersResult.get(player).getKoreanName());
        }
    }

    public void printBlankLine() {
        System.out.println();
    }

    private String dealerFormatter(Map<GameResult, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (GameResult gameResult : GameResult.values()) {
            String koreanName = gameResult.getKoreanName();
            if (dealerResult.get(gameResult) == null) {
                continue;
            }
            stringBuilder.append(dealerResult.get(gameResult));
            stringBuilder.append(koreanName + " ");
        }
        return stringBuilder.toString();
    }
}
