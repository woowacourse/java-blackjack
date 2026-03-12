package view;

import domain.card.Card;
import domain.dto.ParticipantCardsResponse;
import domain.dto.ParticipantResultResponse;
import domain.dto.ProfitResponse;
import domain.dto.ProfitsResultResponse;
import java.util.List;
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

    public void printProfitsResult( ProfitsResultResponse profitsResultResponse) {
        System.out.println("## 최종 수익");
        for (ProfitResponse profitResponse : profitsResultResponse.results()) {
            System.out.printf("%s: %d%n", profitResponse.name(), profitResponse.profit());
        }
    }
}
