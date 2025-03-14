package view;

import domain.GameStatus;
import domain.card.Card;
import domain.dto.GameResultDto;
import domain.dto.ParticipantCardsDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PARTICIPANT_INITIAL_CARDS_HEADER_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String PARTICIPANT_CARDS_FORMAT = "%s카드: ";
    private static final String CARD_FORMAT = "%s%s";
    private static final String CARDS_SCORE_FORMAT = " - 결과: %d";
    private static final String DEALER_EXTRA_CARD_TRUE_FORMAT = "%s는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_EXTRA_CARD_FALSE_FORMAT = "%s는 16초과라 한장카드를 더 받지 않았습니다.";
    private static final String GAME_RESULT_HEADER = "## 최종 승패";
    private static final String DEALER_GAME_RESULT_FORMAT = "%d%s";
    private static final String PLAYER_NAMES_DELIMITER  =",";
    private static final String CARDS_DELIMITER = ",";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printParticipantInitialCards(ParticipantCardsDto dealerCardsDto, List<ParticipantCardsDto> playerCardsDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator()).append(formatParticipantInitialCardsHeader(dealerCardsDto, playerCardsDtos)).append(System.lineSeparator());
        stringBuilder.append(formatParticipantCards(dealerCardsDto)).append(System.lineSeparator());
        for (ParticipantCardsDto playerCardsDto : playerCardsDtos) {
            stringBuilder.append(formatParticipantCards(playerCardsDto)).append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    public static void printParticipantCards(ParticipantCardsDto participantCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatParticipantCards(participantCardsDto));
        System.out.println(stringBuilder);
    }

    public static void printDealerExtraCard(ParticipantCardsDto dealerCardsDto, boolean dealerExtraCard) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator())
                .append(formatDealerExtraCard(dealerCardsDto, dealerExtraCard))
                .append(System.lineSeparator());
        System.out.println(stringBuilder);
    }

    public static void printParticipantsFinalCards(List<ParticipantCardsDto> participantCardsDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ParticipantCardsDto participantCardsDto : participantCardsDtos) {
            stringBuilder.append(formatFinalParticipantCards(participantCardsDto))
                    .append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    public static  void printFinalGameResult(GameResultDto dealerGameResultDto, List<GameResultDto> playerGameResultDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GAME_RESULT_HEADER).append(System.lineSeparator());
        stringBuilder.append(formatDealerGameResult(dealerGameResultDto)).append(System.lineSeparator());
        for (GameResultDto playerGameResultDto : playerGameResultDtos) {
            stringBuilder.append(formatPlayerGameResult(playerGameResultDto)).append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private static String formatParticipantInitialCardsHeader(ParticipantCardsDto dealerCardsDto, List<ParticipantCardsDto> playerCardsDtos) {
        return String.format(PARTICIPANT_INITIAL_CARDS_HEADER_FORMAT, dealerCardsDto.name(), formatPlayerNames(playerCardsDtos));
    }

    private static String formatPlayerNames(List<ParticipantCardsDto> participantCardsDtos) {
        return participantCardsDtos.stream()
                .map(ParticipantCardsDto::name)
                .collect(Collectors.joining(PLAYER_NAMES_DELIMITER));
    }

    private static String formatParticipantCards(ParticipantCardsDto participantCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatParticipantName(participantCardsDto));
        stringBuilder.append(formatCards(participantCardsDto.cards()));
        return stringBuilder.toString();
    }

    private static String formatDealerExtraCard(ParticipantCardsDto dealerCardsDto, boolean dealerExtraCard) {
        if (dealerExtraCard) {
            return java.lang.String.format(DEALER_EXTRA_CARD_TRUE_FORMAT, dealerCardsDto.name());
        }
        return java.lang.String.format(DEALER_EXTRA_CARD_FALSE_FORMAT, dealerCardsDto.name());
    }

    public static String formatFinalParticipantCards(ParticipantCardsDto participantCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatParticipantCards(participantCardsDto));
        stringBuilder.append(formatCardsScore(participantCardsDto));
        return stringBuilder.toString();
    }

    private static String formatDealerGameResult(GameResultDto gameResultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gameResultDto.name()).append(": ");
        for (GameStatus gameStatus : GameStatus.values()) {
            int count = gameResultDto.gameResults().get(gameStatus);
            if (count == 0) {
                continue;
            }
            stringBuilder.append(formatGameResult(gameStatus, count));
        }
        return stringBuilder.toString();
    }

    private static String formatPlayerGameResult(GameResultDto gameResultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gameResultDto.name()).append(": ");
        for (GameStatus gameStatus : GameStatus.values()) {
            int count = gameResultDto.gameResults().get(gameStatus);
            if (count == 0) {
                continue;
            }
            stringBuilder.append(gameStatus.getName());
        }
        return stringBuilder.toString();
    }

    private static String formatGameResult(GameStatus gameStatus, int count) {
        return String.format(DEALER_GAME_RESULT_FORMAT, count, gameStatus.getName());
    }

    private static String formatParticipantName(ParticipantCardsDto participantCardsDto) {
        return String.format(PARTICIPANT_CARDS_FORMAT, participantCardsDto.name());
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(card -> java.lang.String.format(CARD_FORMAT, card.rank().getName(), card.suit().getName()))
                .collect(Collectors.joining(CARDS_DELIMITER));
    }

    private static String formatCardsScore(ParticipantCardsDto participantCardsDto) {
        return java.lang.String.format(CARDS_SCORE_FORMAT, participantCardsDto.cardsScore());
    }
}
