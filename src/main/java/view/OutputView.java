package view;

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
    private static final String PLAYER_NAMES_DELIMITER  =",";
    private static final String CARDS_DELIMITER = ",";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printParticipantInitialCards(ParticipantCardsDto dealerCardsDto, List<ParticipantCardsDto> playerCardsDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator()).append(formatParticipantInitialCardsHeader(dealerCardsDto, playerCardsDtos)).append(System.lineSeparator());
        stringBuilder.append(formatParticipantCards(dealerCardsDto)).append(System.lineSeparator());
        for (ParticipantCardsDto playerCardsDto : playerCardsDtos) {
            stringBuilder.append(formatParticipantCards(playerCardsDto)).append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    public void printParticipantCards(ParticipantCardsDto participantCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatParticipantCards(participantCardsDto));
        System.out.println(stringBuilder);
    }

    public void printDealerExtraCard(ParticipantCardsDto dealerCardsDto, boolean dealerExtraCard) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator())
                .append(formatDealerExtraCard(dealerCardsDto, dealerExtraCard))
                .append(System.lineSeparator());
        System.out.println(stringBuilder);
    }

    public void printParticipantsFinalCards(List<ParticipantCardsDto> participantCardsDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ParticipantCardsDto participantCardsDto : participantCardsDtos) {
            stringBuilder.append(formatFinalParticipantCards(participantCardsDto))
                    .append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    public void printFinalGameResult(GameResultDto dealerGameResultDto, List<GameResultDto> playerGameResultDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GAME_RESULT_HEADER).append(System.lineSeparator());
        stringBuilder.append(formatParticipantGameResult(dealerGameResultDto)).append(System.lineSeparator());
        for (GameResultDto playerGameResultDto : playerGameResultDtos) {
            stringBuilder.append(formatParticipantGameResult(playerGameResultDto)).append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private String formatParticipantInitialCardsHeader(ParticipantCardsDto dealerCardsDto, List<ParticipantCardsDto> playerCardsDtos) {
        return String.format(PARTICIPANT_INITIAL_CARDS_HEADER_FORMAT, dealerCardsDto.name(), formatPlayerNames(playerCardsDtos));
    }

    private String formatPlayerNames(List<ParticipantCardsDto> participantCardsDtos) {
        return participantCardsDtos.stream()
                .map(ParticipantCardsDto::name)
                .collect(Collectors.joining(PLAYER_NAMES_DELIMITER));
    }

    private String formatParticipantCards(ParticipantCardsDto participantCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatParticipantName(participantCardsDto));
        stringBuilder.append(formatCards(participantCardsDto.cards()));
        return stringBuilder.toString();
    }

    private String formatDealerExtraCard(ParticipantCardsDto dealerCardsDto, boolean dealerExtraCard) {
        if (dealerExtraCard) {
            return String.format(DEALER_EXTRA_CARD_TRUE_FORMAT, dealerCardsDto.name());
        }
        return String.format(DEALER_EXTRA_CARD_FALSE_FORMAT, dealerCardsDto.name());
    }

    public String formatFinalParticipantCards(ParticipantCardsDto participantCardsDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(formatParticipantCards(participantCardsDto));
        stringBuilder.append(formatCardsScore(participantCardsDto));
        return stringBuilder.toString();
    }

    private String formatParticipantGameResult(GameResultDto gameResultDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gameResultDto.name())
                .append(": ")
                .append(gameResultDto.gameProfit());
        return stringBuilder.toString();
    }

    private String formatParticipantName(ParticipantCardsDto participantCardsDto) {
        return java.lang.String.format(PARTICIPANT_CARDS_FORMAT, participantCardsDto.name());
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
                .map(card -> java.lang.String.format(CARD_FORMAT, card.rank().getName(), card.suit().getName()))
                .collect(Collectors.joining(CARDS_DELIMITER));
    }

    private String formatCardsScore(ParticipantCardsDto participantCardsDto) {
        return String.format(CARDS_SCORE_FORMAT, participantCardsDto.cardsScore());
    }
}
