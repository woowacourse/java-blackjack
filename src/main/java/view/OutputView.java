package view;

import static domain.Participants.DEALER_NAME;

import domain.GameStatus;
import domain.card.Card;
import domain.dto.GameResultDto;
import domain.dto.ParticipantCardsDto;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INITIAL_CARDS_HEADER_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String PARTICIPANT_CARDS_FORMAT = "%s카드: ";
    private static final String CARD_FORMAT = "%s%s";
    private static final String CARDS_SCORE_FORMAT = " - 결과: %d";
    private static final String DEALER_CARD_ADDED_FORMAT = "%s는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String DEALER_CARD_NOT_ADDED_FORMAT = "%s는 16초과라 카드를 더 받지 않았습니다.";
    private static final String GAME_RESULT_HEADER = "## 최종 승패";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialParticipantsCards(List<ParticipantCardsDto> participantCardsDtos) {
        System.out.println(formatInitialCardsHeader(participantCardsDtos));
        printParticipantsCardsByFormat(participantCardsDtos, OutputView::formatParticipantCards);
    }

    public static void printFinalParticipantsCards(List<ParticipantCardsDto> participantCardsDtos) {
        printParticipantsCardsByFormat(participantCardsDtos, OutputView::formatFinalParticipantCards);
    }

    private static void printParticipantsCardsByFormat(
            List<ParticipantCardsDto> participantCardsDtos,
            Function<ParticipantCardsDto, String> formatter
    ) {
        ParticipantCardsDto dealerDto = findDealerDto(participantCardsDtos, ParticipantCardsDto::name);
        System.out.println(formatter.apply(dealerDto));
        participantCardsDtos.remove(dealerDto);
        for (ParticipantCardsDto participantCardsDto : participantCardsDtos) {
            System.out.println(formatter.apply(participantCardsDto));
        }
    }

    public static void printParticipantCards(ParticipantCardsDto participantCardsDto) {
        System.out.println(formatParticipantCards(participantCardsDto));
    }

    public static void printDealerExtraCard(boolean isAdded) {
        System.out.println(System.lineSeparator() + formatDealerExtraCard(isAdded) + System.lineSeparator());
    }

    private static String formatDealerExtraCard(boolean isAdded) {
        if (isAdded) {
            return String.format(DEALER_CARD_ADDED_FORMAT, DEALER_NAME);
        }
        return String.format(DEALER_CARD_NOT_ADDED_FORMAT, DEALER_NAME);
    }

    private static String formatInitialCardsHeader(List<ParticipantCardsDto> participantCardsDtos) {
        ParticipantCardsDto dealerCardsDto = findDealerDto(participantCardsDtos, ParticipantCardsDto::name);
        List<ParticipantCardsDto> playerCardsDtos = participantCardsDtos.stream()
                .filter(dto -> !dealerCardsDto.equals(dto))
                .toList();
        return String.format(INITIAL_CARDS_HEADER_FORMAT, dealerCardsDto.name(), formatPlayerNames(playerCardsDtos));
    }

    private static String formatPlayerNames(List<ParticipantCardsDto> participantCardsDtos) {
        return participantCardsDtos.stream()
                .map(ParticipantCardsDto::name)
                .collect(Collectors.joining(", "));
    }

    private static String formatFinalParticipantCards(ParticipantCardsDto participantCardsDto) {
        return formatParticipantCards(participantCardsDto) + formatCardsScore(participantCardsDto.cardsScore());
    }

    private static String formatParticipantCards(ParticipantCardsDto participantCardsDto) {
        return formatParticipantName(participantCardsDto.name()) + formatCards(participantCardsDto.cards());
    }

    private static String formatParticipantName(String name) {
        return String.format(PARTICIPANT_CARDS_FORMAT, name);
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(card -> String.format(CARD_FORMAT, card.rank().getName(), card.suit().getName()))
                .collect(Collectors.joining(", "));
    }

    private static String formatCardsScore(int cardsScore) {
        return String.format(CARDS_SCORE_FORMAT, cardsScore);
    }

    public static void printGameResult(List<GameResultDto> gameResultDtos) {
        GameResultDto dealerGameResultDto = findDealerDto(gameResultDtos, GameResultDto::name);
        gameResultDtos.remove(dealerGameResultDto);
        printGameResult(dealerGameResultDto, gameResultDtos);
    }

    public static void printGameResult(GameResultDto dealerGameResultDto, List<GameResultDto> playerGameResultDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GAME_RESULT_HEADER).append(System.lineSeparator());
        stringBuilder.append(formatDealerGameResult(dealerGameResultDto)).append(System.lineSeparator());
        for (GameResultDto playerGameResultDto : playerGameResultDtos) {
            stringBuilder.append(formatPlayerGameResult(playerGameResultDto)).append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private static String formatDealerGameResult(GameResultDto gameResultDto) {
        return formatGameResult(gameResultDto, String::valueOf);
    }

    private static String formatPlayerGameResult(GameResultDto gameResultDto) {
        return formatGameResult(gameResultDto, (Integer count) -> "");
    }

    private static String formatGameResult(GameResultDto gameResultDto, Function<Integer, String> formatter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gameResultDto.name()).append(": ");
        for (GameStatus gameStatus : GameStatus.values()) {
            int count = gameResultDto.gameResults().get(gameStatus);
            if (count == 0) {
                continue;
            }
            stringBuilder.append(formatter.apply(count)).append(gameStatus.getName());
        }
        return stringBuilder.toString();
    }

    private static <T> T findDealerDto(final List<T> dtos, Function<T, String> converter) {
        return dtos.stream()
                .filter(dto -> converter.apply(dto).equals(DEALER_NAME))
                .findFirst()
                .orElseThrow();
    }
}
