package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.InitialDealDtos;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.PlayerResultDto;
import blackjack.model.Dealer;
import blackjack.model.PlayerBlackjackResult;
import blackjack.model.Score;
import blackjack.model.Suit;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String YES = "y";
    private static final String NO = "n";

    private static final Map<Suit, String> SUIT_NAMES = Map.of(
        Suit.DIAMOND, "다이아몬드",
        Suit.CLOVER, "클로버",
        Suit.SPADE, "스페이드",
        Suit.HEART, "하트"
    );

    private static final Map<PlayerBlackjackResult, String> PLAYER_RESULT_NAMES = Map.of(
        PlayerBlackjackResult.WIN, "승",
        PlayerBlackjackResult.LOSE, "패",
        PlayerBlackjackResult.PUSH, "푸시"
    );

    public void askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitialDeal(InitialDealDtos initialDealDtos) {
        List<String> playerNames = initialDealDtos.playerCardsDtos().stream()
            .map(ParticipantCardsDto::participantName).toList();
        String joinedPlayerNames = String.join(", ", playerNames);
        System.out.println(
            initialDealDtos.dealerCardsDto().participantName() + "와 " + joinedPlayerNames + "에게 2장을 나누었습니다.");

        printParticipantInitialCards(initialDealDtos);
        System.out.println();
    }

    private void printParticipantInitialCards(InitialDealDtos initialDealDtos) {
        printParticipantCards(initialDealDtos.dealerCardsDto());
        for (ParticipantCardsDto participantCardsDto : initialDealDtos.playerCardsDtos()) {
            printParticipantCards(participantCardsDto);
        }
    }

    public void printParticipantCards(ParticipantCardsDto participantCardsDto) {
        List<String> cardOutputs = parseCardsToOutputs(participantCardsDto.cards());
        String joinedCards = String.join(", ", cardOutputs);

        System.out.println(participantCardsDto.participantName() + "카드: " + joinedCards);
    }

    public void askHit(String playerName) {
        System.out.println(playerName + "은 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")");
    }

    public void printDealerHit(Dealer dealer) {
        System.out.println();
        System.out.println(dealer.getName() + "는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printScore(List<ParticipantScoreDto> participantScoreDtos) {
        for (ParticipantScoreDto participantScoreDto : participantScoreDtos) {
            printPlayerScore(participantScoreDto.participantName(), participantScoreDto.cards(),
                participantScoreDto.score());
        }
        System.out.println();
    }

    private void printPlayerScore(final String playerName, List<CardDto> cards, Score score) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(", ", cardOutputs);
        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score.value());
    }

    public void printResults(GameResultDto resultDto) {
        System.out.println("## 최종 승패");

        DealerResultDto dealerResult = resultDto.dealerResultDto();
        System.out.printf("딜러: %d승 %d패 %d무\n", dealerResult.win(), dealerResult.lose(), dealerResult.push());

        resultDto.playerResultDtos().forEach(this::printResult);
    }

    public void printResult(PlayerResultDto playerResultDto) {
        System.out.println(playerResultDto.playerName() + ": " + PLAYER_RESULT_NAMES.get(playerResultDto.result()));
    }

    private List<String> parseCardsToOutputs(List<CardDto> cards) {
        return cards.stream()
            .map(this::formatCardToOutput)
            .toList();
    }

    private String formatCardToOutput(CardDto cardDto) {
        return cardDto.rank().getSymbol() + SUIT_NAMES.get(cardDto.suit());
    }
}
