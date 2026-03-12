package view;

import domain.MatchResult;
import domain.card.Card;
import domain.dto.GameResultResponse;
import domain.dto.ParticipantCardsResponse;
import domain.dto.ParticipantResultResponse;
import domain.dto.PlayerMatchResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printGameInitResult(List<ParticipantCardsResponse> initialGameResponses) {
        String playersName = initialGameResponses.stream()
                .map(ParticipantCardsResponse::name)
                .filter(name -> !name.equals("딜러"))
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playersName);

        for (ParticipantCardsResponse response : initialGameResponses) {
            printParticipantCard(response);
        }
        System.out.println();
    }

    public void printCompleteDealerTurn() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerTurn(List<ParticipantResultResponse> dealerTurnResponse) {
        for (ParticipantResultResponse response : dealerTurnResponse) {
            printParticipantResult(response);
        }
        System.out.println();
    }

    public void printGameResult(GameResultResponse response) {
        System.out.println("## 최종 승패");
        printDealerResult(response.dealerMatchResult());
        printPlayerResults(response.playerMatchResults());
    }

    public void printParticipantCard(ParticipantCardsResponse response) {
        System.out.printf("%s카드: %s%n", response.name(), formattedCards(response.cards()));
    }

    public void printNewLine() {
        System.out.println();
    }

    private void printParticipantResult(ParticipantResultResponse response) {
        System.out.printf("%s카드: %s - 결과: %d%n", response.name(), formattedCards(response.cards()),
                response.score().value());
    }

    private String formattedCards(List<Card> cards) {
        return cards.stream()
                .map(card -> RankView.from(card.rank()) + SuitView.from(card.suit()))
                .collect(Collectors.joining(", "));
    }

    private void printDealerResult(Map<MatchResult, Integer> dealerResults) {
        String formattedResult = dealerResults.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + MatchResultView.from(entry.getKey()))
                .collect(Collectors.joining(" "));

        System.out.printf("딜러: %s%n", formattedResult);
    }

    private void printPlayerResults(List<PlayerMatchResult> playerMatchResults) {
        for (PlayerMatchResult playerMatchResult : playerMatchResults) {
            System.out.printf("%s: %s%n", playerMatchResult.name(), MatchResultView.from(playerMatchResult.result()));
        }
    }
}
