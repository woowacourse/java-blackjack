package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.ProfitResultDto;

import java.util.List;
import java.util.Map;

import static blackjack.utils.Constants.*;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String ERROR_SUFFIX = "[ERROR]";

    public void printInitialSettingMessage(final ParticipantDto dealer, final ParticipantsDto players) {
        final String dealerName = dealer.name();
        final List<String> playerNames = getParticipantNames(players.participants());
        final String playerNamesMessage = joinWithComma(playerNames);

        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", dealerName, playerNamesMessage, INITIAL_CARD_COUNT);
    }

    private List<String> getParticipantNames(final List<ParticipantDto> participants) {
        return participants.stream()
                .map(ParticipantDto::name)
                .toList();
    }

    private String joinWithComma(final List<String> source) {
        return String.join(DELIMITER, source);
    }

    public void printFirstCardOfDealer(final ParticipantDto participant) {
        final String dealerName = participant.name();
        final List<CardDto> cardDtos = participant.cards();
        final List<CardDto> firstCardOfDealer = List.of(cardDtos.get(0));
        final String cardsInfoMessage = generateCardsInfoMessage(firstCardOfDealer);

        System.out.printf("%s: %s%n", dealerName, cardsInfoMessage);
    }

    private String generateCardsInfoMessage(final List<CardDto> cards) {
        final List<String> cardsInformation = getCardsInformation(cards);

        return joinWithComma(cardsInformation);
    }

    private List<String> getCardsInformation(final List<CardDto> cards) {
        return cards.stream()
                .map(cardDto -> cardDto.denomination() + cardDto.suit())
                .toList();
    }

    public void printParticipantsCards(final ParticipantsDto participants) {
        final List<ParticipantDto> participantDtos = participants.participants();
        final List<String> participantsCardsMessage = generateParticipantsCardsMessage(participantDtos);

        final String message = joinWithBlankLine(participantsCardsMessage);
        System.out.println(message + NEWLINE);
    }

    private String joinWithBlankLine(final List<String> source) {
        return String.join(NEWLINE, source);
    }

    private List<String> generateParticipantsCardsMessage(final List<ParticipantDto> participantDtos) {
        return participantDtos.stream()
                .map(this::generateParticipantCardsMessage)
                .toList();
    }

    private String generateParticipantCardsMessage(ParticipantDto participant) {
        final String name = participant.name();
        final String cardsInfoMessage = generateCardsInfoMessage(participant.cards());

        return String.format("%s카드: %s", name, cardsInfoMessage);
    }

    public void printParticipantCards(final ParticipantDto participant) {
        final String participantCardsMessage = generateParticipantCardsMessage(participant);
        System.out.println(participantCardsMessage);
    }

    public void printDealerReceiveCardMessage() {
        final String message = String.format("%n%s는 %d이하라 한장의 카드를 더 받았습니다.", DEFAULT_NAME_OF_DEALER, DEALER_MIN_SCORE_POLICY);
        System.out.println(message);
    }

    public void printParticipantsCardsWithScore(final ParticipantsDto participantsDto) {
        final List<ParticipantDto> participants = participantsDto.participants();
        final List<String> cardsWithScoreMessages = generateCardsWithScoreMessages(participants);

        System.out.println(NEWLINE + joinWithBlankLine(cardsWithScoreMessages));
    }

    private List<String> generateCardsWithScoreMessages(final List<ParticipantDto> participants) {
        return participants.stream()
                .map(this::generateCardsWithScoreMessage)
                .toList();
    }

    private String generateCardsWithScoreMessage(final ParticipantDto participant) {
        final String participantCardsMessage = generateParticipantCardsMessage(participant);
        final int score = participant.score();

        return String.format("%s - 결과: %d", participantCardsMessage, score);
    }

    public void printProfits(final ProfitResultDto profitResult) {
        System.out.println(NEWLINE + "## 최종 수익");

        final Map<String, Integer> result = profitResult.result();
        result.forEach((name, profit) -> {
            final String profitMessage = String.format("%s: %d", name, profit);
            System.out.println(profitMessage);
        });
    }

    public void printErrorMessage(final String errorMessage) {
        System.out.printf("%s %s%n", ERROR_SUFFIX, errorMessage);
    }
}
