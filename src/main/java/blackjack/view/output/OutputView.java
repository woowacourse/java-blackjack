package blackjack.view.output;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.result.MatchStatus;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.utils.Delimiter;

public class OutputView {

    public void printMessageOfInitiallyDistributeCards(final List<ParticipantDto> playerDtos) {
        final List<String> playerNames = playerDtos.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.toUnmodifiableList());
        final String combinedPlayerNames = Delimiter.COMMA.joinWith(playerNames);
        printMessage(String.format("딜러와 %s에게 2장의 카드를 나누었습니다.", combinedPlayerNames));
    }

    public void printFirstCardOfDealer(final String dealerFirstCard) {
        printMessage(Delimiter.COLON.joinWith("딜러", dealerFirstCard));
    }

    public void printDistributedCardsOfPlayer(final ParticipantDto participantDto) {
        final String playerName = participantDto.getName();
        final String distributedCards = Delimiter.COMMA.joinWith(participantDto.getCardNames());
        printMessage(Delimiter.COLON.joinWith(playerName, distributedCards));
    }

    public void printMessageOfRequestDrawingCardChoice(final String playerName) {
        printMessage(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName));
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<String> cardNames) {
        final String distributedCards = Delimiter.COMMA.joinWith(cardNames);
        printMessage(Delimiter.COLON.joinWith(playerName, distributedCards));
    }

    public void printFinalScoreOfParticipants(final List<ParticipantDto> participantDtos) {
        participantDtos.forEach(this::printFinalScoreOfParticipant);
    }

    public void printFinalScoreOfParticipant(final ParticipantDto participantDto) {
        final String playerName = participantDto.getName();
        final String distributedCards = Delimiter.COMMA.joinWith(participantDto.getCardNames());
        final int score = participantDto.getScore();
        printMessage(String.format("%s: %s - 결과: %d", playerName, distributedCards, score));
    }

    public void printMatchResult(final MatchResultDto resultDto) {
        printMatchResultOfDealer(resultDto.getResultOfDealer());
        printMatchResultOfPlayers(resultDto.getResultOfPlayers());
    }

    private void printMatchResultOfDealer(final Map<MatchStatus, Integer> resultOfDealer) {
        final List<String> dealerMatchCounts = resultOfDealer.entrySet().stream()
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(Collectors.toUnmodifiableList());
        final String matchResultOfDealer = Delimiter.SPACE.joinWith(dealerMatchCounts);
        printMessage(Delimiter.COLON.joinWith("딜러", matchResultOfDealer));
    }

    private void printMatchResultOfPlayers(final Map<String, MatchStatus> resultOfPlayers) {
        for (final Map.Entry<String, MatchStatus> entry : resultOfPlayers.entrySet()) {
            final String playerName = entry.getKey();
            final String matchStatusName = entry.getValue().getName();
            printMessage(Delimiter.COLON.joinWith(playerName, matchStatusName));
        }
    }

    public void printMessage(final String message) {
        System.out.println(message);
    }

    public void printEmptyLine() {
        printMessage("");
    }

}
