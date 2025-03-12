package view;

import domain.card.Card;
import dto.FinalResultDTO;
import domain.game.GameResult;
import domain.card.Rank;
import dto.SetUpCardsDTO;
import domain.card.Shape;
import domain.game.Winning;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private String cardNameOf(Card card) {
        return card.rank().getName() + card.shape().getName();
    }

    public void printSetUpCardDeck(SetUpCardsDTO setUpCardsDTO) {
        Card dealerOpenCard = setUpCardsDTO.dealerOpenCard();
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playerNames(setUpCardsDTO));

        System.out.println("딜러카드: " + cardNameOf(dealerOpenCard));
        Map<String, List<Card>> playerCards = setUpCardsDTO.cards();
        playerCards.forEach((key, value) -> System.out.printf("%s카드: %s%n", key, cardNames(value)));
        System.out.println();
    }

    public void printTakenMoreCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name, cardNames(cards));
    }

    public void printDealerTake() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardDeck(List<FinalResultDTO> dtos) {
        System.out.println();
        dtos.forEach(
            dto -> System.out.printf("%s카드: %s - 결과: %d\n",
            dto.name(), cardNames(dto.cards()), dto.score())
        );
    }

    public void printGameResult(GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerWinnings(gameResult);

        gameResult.getPlayerWinningResult()
            .forEach(
                (player, winning) -> System.out.printf("%s: %s\n",
                    player.getName(), winning.getName())
            );
    }

    private static void printDealerWinnings(GameResult gameResult) {
        System.out.print("딜러: ");
        if(gameResult.countDealerWin() > 0){
            System.out.print(gameResult.countDealerWin() + "승 ");
        }
        if(gameResult.countDealerDraw() > 0){
            System.out.print(gameResult.countDealerDraw() + "무 ");
        }
        if(gameResult.countDealerLose() > 0){
            System.out.print(gameResult.countDealerLose() + "패 ");
        }
        System.out.println();
    }

    private String cardNames(List<Card> cards) {
        return cards.stream()
            .map(this::cardNameOf)
            .collect(Collectors.joining(", "));
    }

    private String playerNames(SetUpCardsDTO setUpCardsDTO) {
        return String.join(", ", setUpCardsDTO.cards().keySet());
    }
}
