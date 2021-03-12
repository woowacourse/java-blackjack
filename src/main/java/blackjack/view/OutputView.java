package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.view.dto.PlayerStatusDto;
import blackjack.view.dto.RoundStatusDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String TWO_CARDS_DEAL_OUT_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String PARTICIPANT_STATUS_MESSAGE = "%s: %s";
    private static final String DELIMITER = ", ";
    private static final String DEALER_CARD_ADD_MESSAGE = "딜러는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_MESSAGE = "%s카드 : %s - 결과: %d";

    private OutputView() {
    }

    public static void showInitialStatus(final RoundStatusDto roundStatusDto) {
        String dealerName = roundStatusDto.getDealerName();
        List<PlayerStatusDto> playerStatusDto = roundStatusDto.getPlayerStatusDto();

        String playerNames = playerStatusDto.stream()
                .map(dto -> dto.getPlayerName())
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format(TWO_CARDS_DEAL_OUT_MESSAGE, dealerName, playerNames));
        System.out.println(makeParticipantStatusMessage(dealerName, roundStatusDto.getSingleDealerCards()));
        playerStatusDto.forEach(dto -> System.out.println(makeParticipantStatusMessage(dto.getPlayerName(), dto.getPlayerCards())));
    }

    public static void showPlayCardStatus(final PlayerStatusDto playerStatusDto) {
        System.out.println(makeParticipantStatusMessage(playerStatusDto.getPlayerName(), playerStatusDto.getPlayerCards()));
    }

    public static void showDealerAddCard(final int turnOverCount) {
        System.out.println(String.format(DEALER_CARD_ADD_MESSAGE, turnOverCount));
    }

    public static void showFinalStatus(final RoundStatusDto statusDto) {
        List<PlayerStatusDto> playerStatusDto = statusDto.getPlayerStatusDto();
        System.out.println(makeGameResultMessage(statusDto.getDealerName(), statusDto.getDealerCards(), statusDto.getDealerScore()));
        playerStatusDto.forEach(dto -> System.out.println(makeGameResultMessage(dto.getPlayerName(), dto.getPlayerCards(), dto.getPlayerScore())));
    }

    private static String makeParticipantStatusMessage(final String name, final List<Card> cards) {
        return String.format(
                PARTICIPANT_STATUS_MESSAGE,
                name,
                String.join(DELIMITER, cardsToString(cards))
        );
    }

    private static String makeGameResultMessage(String name, List<Card> cards, int score) {
        return String.format(
                GAME_RESULT_MESSAGE,
                name,
                String.join(DELIMITER, cardsToString(cards)),
                score
        );
    }

    private static List<String> cardsToString(final List<Card> cards) {
        return cards.stream()
                .map(playerCard -> playerCard.symbolName() + playerCard.numberName())
                .collect(Collectors.toList());
    }
}
