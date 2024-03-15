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

    public void printInitialSettingMessage(ParticipantDto dealer, ParticipantsDto players) {
        String dealerName = dealer.name();
        List<String> playerNames = getParticipantNames(players.participants());
        String playerNamesMessage = joinWithComma(playerNames);

        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", dealerName, playerNamesMessage, INITIAL_CARD_COUNT);
    }

    private List<String> getParticipantNames(List<ParticipantDto> participants) {
        return participants.stream()
                .map(ParticipantDto::name)
                .toList();
    }

    private String joinWithComma(List<String> source) {
        return String.join(DELIMITER, source);
    }

    public void printFirstCardOfDealer(ParticipantDto participant) {
        String dealerName = participant.name();
        List<CardDto> cardDtos = participant.cards();
        List<CardDto> firstCardOfDealer = List.of(cardDtos.get(0));
        String cardsInfoMessage = generateCardsInfoMessage(firstCardOfDealer);

        System.out.printf("%s: %s%n", dealerName, cardsInfoMessage);
    }

    private String generateCardsInfoMessage(List<CardDto> cards) {
        List<String> cardsInformation = getCardsInformation(cards);

        return joinWithComma(cardsInformation);
    }

    private List<String> getCardsInformation(List<CardDto> cards) {
        return cards.stream()
                .map(cardDto -> cardDto.denomination() + cardDto.suit())
                .toList();
    }

    public void printParticipantsCards(ParticipantsDto participants) {
        List<ParticipantDto> participantDtos = participants.participants();
        List<String> participantsCardsMessage = generateParticipantsCardsMessage(participantDtos);

        String message = joinWithBlankLine(participantsCardsMessage);
        System.out.println(message + NEWLINE);
    }

    private String joinWithBlankLine(List<String> source) {
        return String.join(NEWLINE, source);
    }

    private List<String> generateParticipantsCardsMessage(List<ParticipantDto> participantDtos) {
        return participantDtos.stream()
                .map(this::generateParticipantCardsMessage)
                .toList();
    }

    private String generateParticipantCardsMessage(ParticipantDto participant) {
        String name = participant.name();
        String cardsInfoMessage = generateCardsInfoMessage(participant.cards());

        return String.format("%s카드: %s", name, cardsInfoMessage);
    }

    public void printParticipantCards(ParticipantDto participant) {
        String participantCardsMessage = generateParticipantCardsMessage(participant);
        System.out.println(participantCardsMessage);
    }

    public void printDealerReceiveCardMessage() {
        String message = String.format("%n%s는 %d이하라 한장의 카드를 더 받았습니다.", DEFAULT_NAME_OF_DEALER, DEALER_MIN_SCORE_POLICY);
        System.out.println(message);
    }

    public void printParticipantsCardsWithScore(ParticipantsDto participantsDto) {
        List<ParticipantDto> participants = participantsDto.participants();
        List<String> cardsWithScoreMessages = generateCardsWithScoreMessages(participants);

        System.out.println(NEWLINE + joinWithBlankLine(cardsWithScoreMessages));
    }

    private List<String> generateCardsWithScoreMessages(List<ParticipantDto> participants) {
        return participants.stream()
                .map(this::generateCardsWithScoreMessage)
                .toList();
    }

    private String generateCardsWithScoreMessage(ParticipantDto participant) {
        String participantCardsMessage = generateParticipantCardsMessage(participant);
        int score = participant.score();

        return String.format("%s - 결과: %d", participantCardsMessage, score);
    }

    public void printProfits(ProfitResultDto profitResult) {
        System.out.println(NEWLINE + "## 최종 수익");

        Map<String, Integer> result = profitResult.result();
        result.forEach((name, profit) -> {
            String profitMessage = String.format("%s: %d", name, profit);
            System.out.println(profitMessage);
        });
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s %s%n", ERROR_SUFFIX, errorMessage);
    }
}
