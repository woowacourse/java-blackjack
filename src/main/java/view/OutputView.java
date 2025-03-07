package view;

import domain.Card;
import domain.FinalResultDTO;
import domain.GameResult;
import domain.Rank;
import domain.SetUpCardsDTO;
import domain.Shape;
import domain.Winning;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    Map<Rank, String> rankNames = Map.ofEntries(
        Map.entry(Rank.ACE, "A"),
        Map.entry(Rank.TWO, "2"),
        Map.entry(Rank.THREE, "3"),
        Map.entry(Rank.FOUR, "4"),
        Map.entry(Rank.FIVE, "5"),
        Map.entry(Rank.SIX, "6"),
        Map.entry(Rank.SEVEN, "7"),
        Map.entry(Rank.EIGHT, "8"),
        Map.entry(Rank.NINE, "9"),
        Map.entry(Rank.TEN, "10"),
        Map.entry(Rank.JACK, "J"),
        Map.entry(Rank.QUEEN, "Q"),
        Map.entry(Rank.KING, "K")
    );

    Map<Shape, String> shapeNames = Map.ofEntries(
        Map.entry(Shape.SPADE, "스페이드"),
        Map.entry(Shape.DIAMOND, "다이아몬드"),
        Map.entry(Shape.HEART, "하트"),
        Map.entry(Shape.CLOVER, "클로버")
    );

    Map<Winning, String> winningNames = Map.ofEntries(
        Map.entry(Winning.WIN, "승"),
        Map.entry(Winning.DRAW, "무"),
        Map.entry(Winning.LOSE, "패")
    );

    private String cardNameOf(Card card) {
        return rankNames.get(card.getRank()) + shapeNames.get(card.getShape());
    }

    public void printSetUpCardDeck(SetUpCardsDTO setUpCardsDTO) {
        Card dealerOpenCard = setUpCardsDTO.dealerOpenCard();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", playerNames(setUpCardsDTO));
        System.out.println("딜러카드: " + cardNameOf(dealerOpenCard));
        Map<String, List<Card>> playerCards = setUpCardsDTO.cards();
        playerCards.forEach((key, value) -> System.out.printf("%s카드: %s%n", key, cardNames(value)));
    }

    public void printTakenMoreCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s\n", name, cardNames(cards));
    }

    public void printDealerTake() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCardDeck(List<FinalResultDTO> dtos) {
        dtos.forEach(dto -> System.out.printf("%s카드: %s - 결과: %d\n", dto.name(), cardNames(dto.cards()), dto.score()));
    }

    public void printGameResult(GameResult gameResult) {
        System.out.println("## 최종 승패");
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

        gameResult.getPlayerWinningResult()
            .forEach(((player, winning) -> System.out.printf("%s: %s\n", player.getName(),
                winningNames.get(winning))));
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
