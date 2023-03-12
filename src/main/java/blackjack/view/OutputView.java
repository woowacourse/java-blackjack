package blackjack.view;

import static java.text.MessageFormat.format;

import blackjack.constants.ErrorCode;
import blackjack.domain.game.BettingResult;
import blackjack.domain.game.BlackjackResult;
import blackjack.domain.game.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.domain.vo.Money;
import blackjack.view.message.CardMessageConvertor;
import blackjack.view.message.ErrorCodeMessage;
import blackjack.view.message.GameResultMessage;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INIT_FORMAT = LINE_SEPARATOR + "딜러와 %s에게 2장을 나누었습니다." + LINE_SEPARATOR;
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s" + LINE_SEPARATOR;
    private static final String CARDS_RESULT_FORMAT = "%s카드: %s - 결과: %d" + LINE_SEPARATOR;
    private static final String DEALER_HIT_MESSAGE = LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_MESSAGE = "## 최종 수익";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d" + LINE_SEPARATOR;
    private static final String PLAYER_RESULT_FORMAT = "%s: %d" + LINE_SEPARATOR;
    private static final String DELIMITER = ", ";

    public void printInitCards(Participants participants) {
        System.out.printf(INIT_FORMAT, String.join(DELIMITER, participants.getPlayerNames()));
        printParticipantInitCard(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            printParticipantInitCard(player);
        }
        System.out.println();
    }

    private void printParticipantInitCard(Participant participant) {
        String cardNames = getParticipantInitCardsView(participant);
        System.out.printf(PLAYER_CARDS_FORMAT, participant.getName(), cardNames);
    }

    public void printPlayerCards(Participant participant) {
        String cardNames = getParticipantCardsView(participant);
        System.out.printf(PLAYER_CARDS_FORMAT, participant.getName(), cardNames);
    }

    private String getParticipantInitCardsView(Participant participant) {
        return participant.getFirstCard()
                .stream()
                .map(CardMessageConvertor::convert)
                .collect(Collectors.joining(DELIMITER));
    }

    public void printDealerState() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public void printCardResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        String dealerCards = getParticipantCardsView(dealer);
        printCardResultFormat(dealer, dealerCards);

        for (Player player : participants.getPlayers()) {
            String cards = getParticipantCardsView(player);
            printCardResultFormat(player, cards);
        }
    }

    private void printCardResultFormat(Participant participant, String cards) {
        System.out.printf(CARDS_RESULT_FORMAT, participant.getName(), cards, participant.getScore().getValue());
    }

    private String getParticipantCardsView(Participant participant) {
        return participant.getAllCards()
                .stream()
                .map(CardMessageConvertor::convert)
                .collect(Collectors.joining(DELIMITER));
    }

    public void printGameResult(BlackjackResult result) {
        System.out.println(RESULT_MESSAGE);
        System.out.printf(DEALER_RESULT_FORMAT, result.getDealerWinCount(), result.getTieCount(),
                result.getDealerLoseCount());
        for (Player player : result.getPlayer()) {
            GameResult gameResult = result.get(player);
            System.out.printf(PLAYER_RESULT_FORMAT, player.getName(), getGameResultMessage(gameResult));
        }
    }

    public void printBettingResult(BettingResult result) {
        System.out.println(RESULT_MESSAGE);
        System.out.printf(DEALER_RESULT_FORMAT, result.getDealerBettingResult().getValue());
        Map<Player, Money> bettingResults = result.getBettingResults();
        for (Player player : bettingResults.keySet()) {
            Money money = bettingResults.get(player);
            System.out.printf(PLAYER_RESULT_FORMAT, player.getName(), money.getValue());
        }
    }

    private String getGameResultMessage(GameResult gameResult) {
        return GameResultMessage.from(gameResult).getMessage();
    }

    public void printError(ErrorCode errorCode) {
        System.out.println(format("[ERROR] {0}", ErrorCodeMessage.from(errorCode).getMessage()));
    }
}
