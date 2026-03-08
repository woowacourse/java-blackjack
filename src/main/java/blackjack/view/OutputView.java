package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantInitialDealDtos;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.PlayerResultDto;
import blackjack.model.PlayerBlackjackResult;
import blackjack.model.Score;
import java.util.List;

public class OutputView {

    public void printInitialDeal(ParticipantInitialDealDtos participantInitialDealDtos) {
        List<String> playerNames = participantInitialDealDtos.participantCardsDtos().stream()
            .map(ParticipantCardsDto::participantName)
            .filter(name -> !name.equals("딜러"))
            .toList();
        String joinedPlayerNames = String.join(", ", playerNames);
        System.out.println("딜러와 " + joinedPlayerNames + "에게 2장을 나누었습니다.");

        for (ParticipantCardsDto participantCardsDto : participantInitialDealDtos.participantCardsDtos()) {
            printPlayerCards(participantCardsDto);
        }
        System.out.println();
    }

    public void printPlayerCards(ParticipantCardsDto participantCardsDto) {
        List<String> cardOutputs = parseCardsToOutputs(participantCardsDto.cards());
        String joinedCards = String.join(", ", cardOutputs);

        System.out.println(participantCardsDto.participantName() + "카드: " + joinedCards);
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printScore(List<ParticipantScoreDto> participantScoreDtos) {
        for (ParticipantScoreDto participantScoreDto : participantScoreDtos) {
            printPlayerScore(participantScoreDto.participantName(), participantScoreDto.cards(),
                participantScoreDto.score());
        }
        System.out.println();
    }

    private void printPlayerScore(String playerName, List<CardDto> cards, Score score) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(", ", cardOutputs);
        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score.value());
    }

    public void printResult(List<PlayerResultDto> playerResultDtos) {
        System.out.println("## 최종 승패");

        long dealerLoseCount = playerResultDtos.stream()
            .filter(playerResultDto -> playerResultDto.result() == PlayerBlackjackResult.WIN)
            .count();
        long dealerWinCount = playerResultDtos.stream()
            .filter(playerResultDto -> playerResultDto.result() == PlayerBlackjackResult.LOSE)
            .count();
        int playerCount = playerResultDtos.size();
        System.out.println(
            "딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패 " +
                (playerCount - dealerLoseCount - dealerWinCount) + "무");

        playerResultDtos.forEach(this::printResult);
    }

    public void printResult(PlayerResultDto playerResultDto) {
        System.out.println(playerResultDto.participantName() + ": " + playerResultDto.result().getLabel());
    }

    private List<String> parseCardsToOutputs(List<CardDto> cards) {
        return cards.stream()
            .map(cardDto -> cardDto.rank().getLabel() + cardDto.suit().getKorean())
            .toList();
    }
}
