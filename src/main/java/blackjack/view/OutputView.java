package blackjack.view;

import blackjack.domain.game.Score;
import blackjack.dto.CardNameDto;
import blackjack.dto.DealerHitDto;
import blackjack.dto.DealerProfitDto;
import blackjack.dto.GameResultDtos;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.ParticipantScoreDtos;
import blackjack.dto.PlayerProfitDto;
import java.util.List;

public class OutputView {
    private static final String YES = "y";
    private static final String NO = "n";

    public void askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void askBetAmount(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
    }

    public void printInitialDeal(InitialDealDtos initialDealDtos) {
        List<String> playerNames = initialDealDtos.playerCardsDtos().stream()
            .map(ParticipantCardsDto::participantName)
            .toList();
        String joinedPlayerNames = String.join(", ", playerNames);

        System.out.println();
        System.out.printf(
            "%s와 %s에게 2장을 나누었습니다.%n",
            initialDealDtos.dealerCardsDto().participantName(),
            joinedPlayerNames
        );

        printParticipantInitialCards(initialDealDtos);
        System.out.println();
    }

    private void printParticipantInitialCards(InitialDealDtos initialDealDtos) {
        printParticipantCards(initialDealDtos.dealerCardsDto());
        for (ParticipantCardsDto playerCardsDto : initialDealDtos.playerCardsDtos()) {
            printParticipantCards(playerCardsDto);
        }
    }

    public void printParticipantCards(ParticipantCardsDto participantCardsDto) {
        List<String> cardOutputs = parseCardsToOutputs(participantCardsDto.cards());
        String joinedCards = String.join(", ", cardOutputs);

        System.out.println(participantCardsDto.participantName() + "카드: " + joinedCards);
    }

    public void askHit(final String playerName) {
        System.out.println(playerName + "은 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")");
    }

    public void printDealerHit(DealerHitDto dealerHitDto) {
        System.out.printf("%s는 16장이하라 %d장의 카드를 더 받았습니다.%n",
            dealerHitDto.dealerName(),
            dealerHitDto.hitCount());
        System.out.println();
    }

    public void printScore(ParticipantScoreDtos participantScoreDtos) {
        for (ParticipantScoreDto participantScoreDto : participantScoreDtos.scoreDtos()) {
            printPlayerScore(participantScoreDto.participantName(),
                participantScoreDto.cards(),
                participantScoreDto.score());
        }
        System.out.println();
    }

    private void printPlayerScore(final String playerName, List<CardNameDto> cards, Score score) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(", ", cardOutputs);
        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score.value());
    }

    private List<String> parseCardsToOutputs(List<CardNameDto> cards) {
        return cards.stream()
            .map(CardNameDto::cardName)
            .toList();
    }

    public void printResults(GameResultDtos resultDto) {
        System.out.println("## 최종 승패");

        DealerProfitDto dealerProfitDto = resultDto.dealerProfitDto();
        System.out.println("딜러: " + dealerProfitDto.profit());

        resultDto.playerProfitDtos().forEach(this::printResult);
    }

    public void printResult(PlayerProfitDto playerProfitDto) {
        System.out.println(playerProfitDto.playerName() + ": " + playerProfitDto.profit());
    }
}
