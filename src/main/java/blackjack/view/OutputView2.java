package blackjack.view;

import blackjack.domain.betting2.BettingResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.hand.CardHand;
import blackjack.domain.participant2.Participant;
import blackjack.dto2.InitialDistributionDto;
import blackjack.dto2.ParticipantCardsDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView2 {

    public static final String NEW_LINE = System.lineSeparator();
    private static final String JOIN_DELIMITER = ", ";
    private static final String INITIAL_CARD_DISTRIBUTION_MESSAGE = NEW_LINE + "%s에게 2장의 카드를 나누었습니다." + NEW_LINE;
    private static final String PLAYER_CARDS_FORMAT = "%s 카드: %s";
    private static final String DEALER_BLACKJACK_MESSAGE = NEW_LINE + "블랙잭! 게임을 종료합니다.";
    private static final String PARTICIPANT_CARDS_AND_SCORE_FORMAT = "%s 카드: %s - 결과: %d";
    private static final String CAN_NOT_HIT_INFO_DELIMITER_TEXT = " - ";
    private static final String PLAYER_MAX_SCORE_MESSAGE = "패가 확정되었습니다!" + NEW_LINE;
    private static final String PLAYER_BUST_MESSAGE = "버스트! 21을 초과하였습니다!" + NEW_LINE;
    private static final String PLAYER_BLACKJACK_MESSAGE = "블랙잭! 패가 확정되었습니다!" + NEW_LINE;
    private static final String EXTRA_CARD_MESSAGE = "%s는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String BETTING_RESULT_ANNOUNCEMENT_MESSAGE = "## 최종 수익" + NEW_LINE;
    private static final String BETTING_RESULT_FORMAT = "%s: %s";

    private OutputView2() {
    }

    public static void printInitialParticipantsCards(final InitialDistributionDto dto) {
        final String message = getParticipantsCardCountInfo(dto.getAllParticipantNames())
                + getAllParticipantCardInfos(dto.getParticipantsInfo())
                + NEW_LINE;

        print(message);
    }

    public static void printDealerBlackjackInfo(final InitialDistributionDto dto) {
        final String message = getParticipantsCardCountInfo(dto.getAllParticipantNames())
                + DEALER_BLACKJACK_MESSAGE;

        print(message);
    }

    private static String getParticipantsCardCountInfo(final List<String> playerNames) {
        final String joinedPlayerNames = String.join(JOIN_DELIMITER, playerNames);
        return String.format(INITIAL_CARD_DISTRIBUTION_MESSAGE, joinedPlayerNames);
    }

    private static String getAllParticipantCardInfos(final List<ParticipantCardsDto> participantInfos) {
        return participantInfos.stream()
                .map(OutputView2::getParticipantCardsInfo)
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String getParticipantCardsInfo(final ParticipantCardsDto dto) {
        final String cards = getCardsInfo(dto.getCards());
        return String.format(PLAYER_CARDS_FORMAT, dto.getName(), cards);
    }

    public static void printPlayerCardDistributionInfo(final Participant player) {
        StringBuilder builder = new StringBuilder();

        builder.append(getPlayerCardHandInfo(player));
        if (player.getHand().isFinished()) {
            builder.append(CAN_NOT_HIT_INFO_DELIMITER_TEXT)
                    .append(getCanNotHitInfoOf(player));
        }

        print(builder.toString());
    }

    private static String getPlayerCardHandInfo(Participant player) {
        CardBundle cardBundle = player.getHand().getCardBundle();
        final String playerCards = getCardsInfo(cardBundle.getCards());
        return String.format(PLAYER_CARDS_FORMAT, player.getName(), playerCards);
    }

    private static String getCanNotHitInfoOf(Participant player) {
        CardHand playerHand = player.getHand();
        if (playerHand.isBust()) {
            return PLAYER_BUST_MESSAGE;
        }
        if (playerHand.isBlackjack()) {
            return PLAYER_BLACKJACK_MESSAGE;
        }
        return PLAYER_MAX_SCORE_MESSAGE;
    }

    public static void printDealerExtraCardInfo(Participant dealer) {
        String dealerName = dealer.getName();
        print(String.format(EXTRA_CARD_MESSAGE, dealerName));
    }

    public static void printBettingResults(final List<BettingResult> bettingResults) {
        final String message = getJoinedCardsAndScores(bettingResults)
                + NEW_LINE
                + NEW_LINE
                + getBettingResults(bettingResults);

        print(message);
    }

    private static String getJoinedCardsAndScores(final List<BettingResult> dtos) {
        return dtos.stream()
                .map(BettingResult::getParticipantCardsDto)
                .map(OutputView2::getParticipantCardsAndScore)
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String getParticipantCardsAndScore(final ParticipantCardsDto dto) {
        final String participantName = dto.getName();
        final String cards = getCardsInfo(dto.getCards());
        final int score = dto.getScoreValue();

        return String.format(PARTICIPANT_CARDS_AND_SCORE_FORMAT, participantName, cards, score);
    }

    private static String getBettingResults(final List<BettingResult> dto) {
        return BETTING_RESULT_ANNOUNCEMENT_MESSAGE
                + getAllParticipantsBettingResults(dto);
    }

    private static String getAllParticipantsBettingResults(final List<BettingResult> dtos) {
        return dtos.stream()
                .map(OutputView2::getParticipantGameResult)
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String getParticipantGameResult(final BettingResult dto) {
        final String participantName = dto.getParticipantName();
        final int money = dto.getProfit();

        return String.format(BETTING_RESULT_FORMAT, participantName, money);
    }

    private static String getCardsInfo(final List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static void print(String text) {
        System.out.println(text);
    }
}