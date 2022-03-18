package blackjack.view;

import blackjack.domain.betting.BettingResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.hand.CardHand;
import blackjack.domain.participant.Participant;
import blackjack.dto.InitialDistributionDto;
import blackjack.dto.ParticipantCardsDto;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    public static final String NEW_LINE = System.lineSeparator();
    private static final String JOIN_DELIMITER = ", ";
    private static final String INITIAL_CARD_DISTRIBUTION_MESSAGE = NEW_LINE + "%s에게 2장의 카드를 나누었습니다." + NEW_LINE;
    private static final String PARTICIPANT_CARDS_FORMAT = "%s 카드: %s";
    private static final String DEALER_BLACKJACK_MESSAGE = NEW_LINE + "블랙잭! 게임을 종료합니다.";
    private static final String PARTICIPANT_CARDS_AND_SCORE_FORMAT = "%s 카드: %s - 결과: %d";
    private static final String FINISHED_DELIMITER = " - ";
    private static final String PLAYER_MAX_SCORE_MESSAGE = FINISHED_DELIMITER + "패가 확정되었습니다!" + NEW_LINE;
    private static final String PLAYER_BUST_MESSAGE = FINISHED_DELIMITER + "버스트! 21을 초과하였습니다!" + NEW_LINE;
    private static final String PLAYER_BLACKJACK_MESSAGE = FINISHED_DELIMITER + "블랙잭! 패가 확정되었습니다!" + NEW_LINE;
    private static final String EXTRA_CARD_MESSAGE = "%s는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String BETTING_RESULT_ANNOUNCEMENT_MESSAGE = "## 최종 수익" + NEW_LINE;
    private static final String BETTING_RESULT_FORMAT = "%s: %s";

    private OutputView() {
    }

    public static void printInitialDistribution(final InitialDistributionDto dto) {
        final List<String> allParticipantNames = dto.getAllParticipantNames();
        final List<ParticipantCardsDto> participantsInfo = dto.getParticipantsInfo();

        final String message = initialDistributionCompleteText(allParticipantNames)
                + getAllParticipantCardInfos(participantsInfo)
                + NEW_LINE;

        print(message);
    }

    public static void printDealerBlackjack(final InitialDistributionDto dto) {
        final List<String> allParticipantNames = dto.getAllParticipantNames();

        final String message = initialDistributionCompleteText(allParticipantNames)
                + DEALER_BLACKJACK_MESSAGE;

        print(message);
    }

    private static String initialDistributionCompleteText(final List<String> playerNames) {
        final String joinedPlayerNames = String.join(JOIN_DELIMITER, playerNames);
        return String.format(INITIAL_CARD_DISTRIBUTION_MESSAGE, joinedPlayerNames);
    }

    private static String getAllParticipantCardInfos(final List<ParticipantCardsDto> participantInfos) {
        return participantInfos.stream()
                .map(OutputView::getParticipantCardsInfo)
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String getParticipantCardsInfo(final ParticipantCardsDto dto) {
        final String cards = joinCards(dto.getCards());
        final String participantName = dto.getName();

        return String.format(PARTICIPANT_CARDS_FORMAT, participantName, cards);
    }

    public static void printHitResult(final Participant player) {
        StringBuilder builder = new StringBuilder();
        final CardHand playerHand = player.getHand();

        builder.append(currentPlayerCards(player, playerHand));
        if (playerHand.isFinished()) {
            builder.append(finishedReasonOf(playerHand));
        }

        print(builder.toString());
    }

    private static String currentPlayerCards(Participant player, CardHand playerHand) {
        final CardBundle cardBundle = playerHand.getCardBundle();
        final String playerCards = joinCards(cardBundle.getCards());
        return String.format(PARTICIPANT_CARDS_FORMAT, player.getName(), playerCards);
    }

    private static String finishedReasonOf(CardHand playerHand) {
        if (playerHand.isBust()) {
            return PLAYER_BUST_MESSAGE;
        }
        if (playerHand.isBlackjack()) {
            return PLAYER_BLACKJACK_MESSAGE;
        }
        return PLAYER_MAX_SCORE_MESSAGE;
    }

    public static void printDealerExtraCardInfo(Participant dealer) {
        final String dealerName = dealer.getName();
        print(String.format(EXTRA_CARD_MESSAGE, dealerName));
    }

    public static void printBettingResults(final List<BettingResult> bettingResults) {
        final String message = joinAllParticipantCardsAndScores(bettingResults)
                + NEW_LINE + NEW_LINE
                + getBettingResults(bettingResults);

        print(message);
    }

    private static String joinAllParticipantCardsAndScores(final List<BettingResult> bettingResults) {
        return bettingResults.stream()
                .map(BettingResult::getParticipantCardsDto)
                .map(OutputView::getParticipantCardsAndScore)
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String getParticipantCardsAndScore(final ParticipantCardsDto dto) {
        final String participantName = dto.getName();
        final String cards = joinCards(dto.getCards());
        final int score = dto.getScoreValue();

        return String.format(PARTICIPANT_CARDS_AND_SCORE_FORMAT, participantName, cards, score);
    }

    private static String getBettingResults(final List<BettingResult> bettingResults) {
        return BETTING_RESULT_ANNOUNCEMENT_MESSAGE
                + joinBettingResults(bettingResults);
    }

    private static String joinBettingResults(final List<BettingResult> bettingResults) {
        StringJoiner joiner = new StringJoiner(NEW_LINE);
        for (BettingResult result : bettingResults) {
            joiner.add(getParticipantGameResult(result));
        }
        return joiner.toString();
    }

    private static String getParticipantGameResult(final BettingResult bettingResult) {
        final String participantName = bettingResult.getParticipantName();
        final int profit = bettingResult.getProfit();

        return String.format(BETTING_RESULT_FORMAT, participantName, profit);
    }

    private static String joinCards(final List<Card> cards) {
        StringJoiner joiner = new StringJoiner(JOIN_DELIMITER);
        for (Card card : cards) {
            joiner.add(card.getName());
        }
        return joiner.toString();
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
