package blackjack.view.output;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.result.MatchStatus;
import blackjack.dto.CardDto;
import blackjack.dto.MatchResultDto;
import blackjack.dto.dealer.DealerDto;
import blackjack.dto.dealer.DealerInitialCardDto;
import blackjack.dto.player.PlayerDto;
import blackjack.dto.player.PlayerInitialCardsDto;
import blackjack.view.utils.Delimiter;

public class OutputView {

    public void printMessageOfInitiallyDistributeCards(final List<PlayerInitialCardsDto> playerInitiallyDrewCardDtos) {
        final List<String> playerNames = playerInitiallyDrewCardDtos.stream()
                .map(PlayerInitialCardsDto::getPlayerName)
                .collect(Collectors.toUnmodifiableList());
        final String combinedPlayerNames = Delimiter.COMMA.joinWith(playerNames);
        printMessage(String.format("딜러와 %s에게 2장의 카드를 나누었습니다.", combinedPlayerNames));
    }

    public void printDistributedCardsOfDealer(final DealerInitialCardDto initiallyDrewCardDto) {
        final String dealerCard = initiallyDrewCardDto.getCardName();
        printMessageOfDistributedCards("딜러", dealerCard);
    }

    public void printDistributedCardsOfPlayer(final PlayerInitialCardsDto initiallyDrewCardDto) {
        final String playerName = initiallyDrewCardDto.getPlayerName();
        final String playerCards = Delimiter.COMMA.joinWith(initiallyDrewCardDto.getCardNames());
        printMessageOfDistributedCards(playerName, playerCards);
    }

    private void printMessageOfDistributedCards(final String participantName, final String distributedCards) {
        printMessage(Delimiter.COLON.joinWith(participantName, distributedCards));
    }

    public void printMessageOfRequestDrawingCardChoice(final String playerName) {
        printMessage(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName));
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<CardDto> cardDtos) {
        final List<String> cardNames = cardDtos.stream()
                .map(CardDto::getCardName)
                .collect(Collectors.toUnmodifiableList());
        final String playerCards = Delimiter.COMMA.joinWith(cardNames);
        printMessage(Delimiter.COLON.joinWith(playerName, playerCards));
    }

    public void printFinalScoreOfDealer(final DealerDto dealerDto) {
        final String dealerCards = Delimiter.COMMA.joinWith(dealerDto.getCardNames());
        final int score = dealerDto.getScore();
        printMessageOfFinalScore("딜러", dealerCards, score);
    }

    public void printFinalScoreOfPlayers(final List<PlayerDto> participantDtos) {
        participantDtos.forEach(this::printFinalScoreOfPlayer);
    }

    public void printFinalScoreOfPlayer(final PlayerDto participantDto) {
        final String playerName = participantDto.getName();
        final String playerCards = Delimiter.COMMA.joinWith(participantDto.getCardNames());
        final int score = participantDto.getScore();
        printMessageOfFinalScore(playerName, playerCards, score);
    }

    private void printMessageOfFinalScore(final String participantName,
                                          final String distributedCards,
                                          final int score) {
        printMessage(String.format("%s: %s - 결과: %d", participantName, distributedCards, score));
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
