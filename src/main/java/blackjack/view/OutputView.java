package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.BettingResultDto;
import blackjack.dto.ParticipantDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printMessageOfRequestPlayerNames() {
        printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printMessageOfPlayerStatuses(ParticipantDto dealerDto, List<ParticipantDto> playerDtos) {
        printMessageOfPlayerNames(playerDtos);
        printListOfDistributedCards(dealerDto, playerDtos);
    }

    private void printMessageOfPlayerNames(final List<ParticipantDto> playerDtos) {
        final String combinedPlayerNames = playerDtos.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(", "));
        printMessage("딜러와 " + combinedPlayerNames + " 에게 2장의 카드를 나누었습니다.");
    }

    private void printListOfDistributedCards(final ParticipantDto dealerDto, final List<ParticipantDto> playerDtos) {
        printDistributedCards(dealerDto);
        for (final ParticipantDto participantDto : playerDtos) {
            printDistributedCards(participantDto);
        }
    }

    public void printScores(final List<ParticipantDto> participantDtos) {
        printMessage("");
        for (final ParticipantDto participantDto : participantDtos) {
            final String playerName = participantDto.getName();
            final String cardDetails = distributedCardsToString(participantDto);
            final int score = participantDto.getScore();
            printMessage(playerName + " 카드: " + cardDetails + " - 결과: " + score);
        }
    }

    public void printDistributedCards(final ParticipantDto participantDto) {
        final String playerName = participantDto.getName();
        final String cardDetails = distributedCardsToString(participantDto);
        printMessage(playerName + ": " + cardDetails);
    }

    public void printMessageOfRequestContinuable(final String playerName) {
        printMessage(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printMessageOfDealerDrawCard() {
        printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printBettingResult(final BettingResultDto resultDto) {
        printMessage("\n## 최종 수익");

        final double dealerResult = resultDto.getDealerResult();
        printMessage("딜러: " + String.format("%.0f", dealerResult));

        final Map<String, Double> playerResult = resultDto.getPlayerResult();
        for (Map.Entry<String, Double> entry : playerResult.entrySet()) {
            printMessage(entry.getKey() + ": " + String.format("%.0f", entry.getValue()));
        }
    }

    public void printDistributedCards(String playerName, List<Card> cards) {
        String cardNames = cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(", "));
        printMessage(playerName + ": " + cardNames);
    }

    public void printMessageOfInputBetAmount(String name) {
        printMessage(name + "의 배팅 금액은?");
    }

    private String distributedCardsToString(final ParticipantDto participantDto) {
        final List<String> playerCardDetails = new ArrayList<>();
        for (final Card card : participantDto.getCards()) {
            playerCardDetails.add(card.getCardName());
        }
        return String.join(", ", playerCardDetails);
    }
}
