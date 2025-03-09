package view;

import domain.card.Card;
import domain.game.Winning;
import domain.game.WinningCounts;
import domain.participant.Player;
import dto.ParticipantResultResponse;
import dto.ParticipantsCardsResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    public void printSetUpCardDeck(ParticipantsCardsResponse participantsCardsResponse) {
        Card dealerOpenCard = participantsCardsResponse.dealerOpenCard();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n",
            playerNames(participantsCardsResponse.cards().keySet()));

        System.out.println("딜러카드: " + dealerOpenCard);
        Map<String, List<Card>> playerCards = participantsCardsResponse.cards();
        playerCards.forEach(
            (key, value) -> System.out.printf("%s카드: %s%n", key, cardNamesOf(value)));
    }

    public void printTakenMoreCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name, cardNamesOf(cards));
    }

    public void printDealerTake() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardDeck(List<ParticipantResultResponse> dtos) {
        dtos.forEach(
            dto -> System.out.printf("%s카드: %s - 결과: %d\n",
                dto.name(), cardNamesOf(dto.cards()), dto.score())
        );
    }

    public void printGameResult(WinningCounts winningCounts, Map<Player, Winning> playerWinnings) {
        System.out.println("## 최종 승패");
        printDealerWinnings(winningCounts);

        playerWinnings.forEach((player, winning) ->
            System.out.printf("%s: %s\n", player.getName(), winning.getName())
        );
    }

    private static void printDealerWinnings(WinningCounts winningCounts) {
        System.out.print("딜러: ");
        if (winningCounts.winCount() > 0) {
            System.out.print(winningCounts.winCount() + "승 ");
        }
        if (winningCounts.drawCount() > 0) {
            System.out.print(winningCounts.drawCount() + "무 ");
        }
        if (winningCounts.loseCount() > 0) {
            System.out.print(winningCounts.loseCount() + "패 ");
        }
        System.out.println();
    }

    private String cardNamesOf(List<Card> cards) {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "));
    }

    private String playerNames(Set<String> playerNames) {
        return String.join(", ", playerNames);
    }
}
