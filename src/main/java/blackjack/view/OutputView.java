package blackjack.view;

import blackjack.domain.cards.Card;
import blackjack.domain.names.Name;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import blackjack.dto.GameResult;
import blackjack.dto.Participants;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String NAMES_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String CARD_DISTRIBUTION_FORMAT = "\n %s에게 %d장의 카드를 나누었습니다.\n";
    private static final String PARTICIPANT_STATE_FORMAT = "%s카드: %s %s\n";
    private static final String SCORE_MESSAGE_FORMAT = "- 결과: %s";
    private static final String BUST = "BUST";
    private static final String BLACKJACK = "BLACKJACK";
    private static final String ASKING_DRAW_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String DEALER_TO_DRAW_CARD_FORMAT = "\n%s는 %d이하라 한장의 카드를 더 받았습니다.\n";
    private static final String RESULT_TITLE_MESSAGE = "##최종 승패";
    private static final String PARTICIPANT_PROFIT_FORMAT = "%s: %d\n";
    private static final String BETTING_REQUEST_FORMAT = "\n%s의 배팅 금액은?\n";

    private OutputView() {
    }

    public static void printInputNames() {
        System.out.println(NAMES_REQUEST_MESSAGE);
    }

    public static void printGameInitializeMessage(Participants participants,
        int startingCardCount) {
        System.out
            .printf(CARD_DISTRIBUTION_FORMAT, getParticipantNames(participants), startingCardCount);
    }

    private static String getParticipantNames(Participants participants) {
        return participants.unwrap().stream()
            .map(Participant::getName)
            .collect(Collectors.joining(NAME_DELIMITER));
    }

    public static void printParticipantsStatus(Participants participants, boolean withScore) {
        participants.unwrap()
            .forEach(participant -> printParticipantStatus(participant, withScore));
        System.out.println();
    }

    public static void printParticipantStatus(Participant participant, boolean withScore) {
        String cardNames = participant.getHand().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(NAME_DELIMITER));

        String scoreMessage = makeMessageByScore(participant, withScore);
        System.out.printf(PARTICIPANT_STATE_FORMAT, participant.getName(), cardNames, scoreMessage);
    }

    private static String makeMessageByScore(Participant participant, boolean withScore) {
        String scoreMessage = "";
        if (withScore) {
            scoreMessage = makeMessageByState(participant);
        }
        return scoreMessage;
    }

    private static String makeMessageByState(Participant participant) {
        String score = String.valueOf(participant.getScore());
        if (participant.isBust()) {
            score = BUST;
        }
        if (participant.isBlackJack()) {
            score = BLACKJACK;
        }

        return String.format(SCORE_MESSAGE_FORMAT, score);
    }

    public static void willDrawCard(Player player) {
        System.out.printf(ASKING_DRAW_CARD_FORMAT, player.getName());
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printDealerDrawCard(Dealer dealer) {
        System.out.printf(DEALER_TO_DRAW_CARD_FORMAT, dealer.getName(), Dealer.DEALER_LIMIT);
    }

    public static void printResult(GameResult gameResult) {
        System.out.println(RESULT_TITLE_MESSAGE);
        printParticipantsResult(gameResult);
    }

    private static void printParticipantsResult(GameResult gameResult) {
        Map<String, Integer> unwrappedResult = gameResult.unwrap();
        unwrappedResult.keySet()
            .forEach(playerName -> System.out
                .printf(PARTICIPANT_PROFIT_FORMAT, playerName, unwrappedResult.get(playerName)));
    }

    public static void printBetting(Name name) {
        System.out.printf(BETTING_REQUEST_FORMAT, name.unwrap());
    }
}
