package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_MARK = "[Error] ";
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String BUST_MESSAGE = "카드의 합이 21을 넘어, 게임에서 패배하였습니다.";
    private static final String DEALER_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DISTRIBUTE_MESSAGE = "딜러와 %s에게 2장의 카드를 나누어주었습니다.";
    private static final String PLAYER_CARD_STATUS_FORMAT = "%s카드: %s";
    private static final String CARD_RESULT_FORMAT = "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_MESSAGE = "## 최종 수익";
    private static final String GAME_RESULT_FORMAT = "%s: %d";

    public static void showNameAndCardInfo(final Participants participants) {
        distributeMessage(participants.getPlayers());
        participants.getParticipantGroup()
            .forEach(participant -> showParticipantCard(participant, participant.initialCards()));
    }

    private static void distributeMessage(final List<Participant> players) {
        final String status = players.stream()
            .map(Participant::getName)
            .map(Name::getValue)
            .collect(Collectors.joining(", "));
        System.out.printf(NEWLINE + DISTRIBUTE_MESSAGE + NEWLINE, status);
    }

    public static void showParticipantCard(final Participant participant, final List<Card> cards) {
        final Name name = participant.getName();
        final List<String> cardStatuses = getCardInfo(cards);
        final String cardStatus = String.join(", ", cardStatuses);
        System.out.printf(PLAYER_CARD_STATUS_FORMAT, name.getValue(), cardStatus + NEWLINE);
    }

    public static void showCardsResult(final List<Participant> participants) {
        System.out.println();
        for (final Participant participant : participants) {
            showCardResult(participant);
        }
    }

    public static void showCardResult(final Participant participant) {
        final List<String> cardStatuses = getCardInfo(participant.getPlayerCards());
        final String cardStatus = String.join(", ", cardStatuses);
        final Name name = participant.getName();
        final int result = participant.calculate();
        System.out.printf(CARD_RESULT_FORMAT + NEWLINE, name.getValue(), cardStatus, result);
    }

    private static List<String> getCardInfo(final List<Card> cards) {
        final List<String> cardStatuses = new ArrayList<>();
        for (final Card card : cards) {
            final CardNumber cardNumber = card.getCardNumber();
            final CardType cardType = card.getCardType();
            cardStatuses.add(cardNumber.getSymbol() + cardType.getType());
        }
        return cardStatuses;
    }

    public static void showGameResult(final Map<Name, Integer> participantResult) {
        System.out.println(NEWLINE + GAME_RESULT_MESSAGE);
        participantResult.forEach((name, money) -> {
            System.out.printf(GAME_RESULT_FORMAT + NEWLINE, name.getValue(), money);
        });
    }

    public static void bustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public static void dealerMoreCard() {
        System.out.println(NEWLINE + DEALER_MORE_CARD_MESSAGE);
    }

    public static void getErrorMessage(final String message) {
        System.out.println(ERROR_MARK + message);
    }
}
