package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.CardShape;
import blackjack.domain.WinningStatus;
import blackjack.view.writer.Writer;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    
    private final Writer writer;
    
    public OutputView(final Writer writer) {
        this.writer = writer;
    }
    
    public void outputDealing(List<String> names) {
        StringJoiner sj = new StringJoiner(", ");
        for (String name : names) {
            sj.add(name);
        }
        final String message = "딜러와 %s에게 2장을 나누었습니다.".formatted(sj.toString());
        writer.write(message);
    }
    
    public void outputDealerCards(List<Card> cards) {
        String message = parseNameAndCards("딜러", cards);
        writer.write(message);
    }
    
    public void outputPlayerCards(String name, List<Card> cards) {
        String message = parseNameAndCards(name, cards);
        writer.write(message);
    }
    
    public void is21Warning() {
        writer.write("카드 총합이 21이기 때문에 더 받을 수 없습니다.");
    }
    
    public void burstWarning() {
        writer.write("버스트이기 때문에 더 받을 수 없습니다.");
    }
    
    public void outputDealerAddedCards(int addedCardsSize) {
        if (addedCardsSize != 0) {
            String message = "딜러는 16이하라 %d장의 카드를 더 받았습니다.".formatted(addedCardsSize);
            writer.write(message);
        }
    }
    
    public void outputDealerResult(List<Card> cards, int sum) {
        String message = parseNameAndCards("딜러", cards) + parseSum(sum);
        writer.write(message);
    }
    
    public void outputPlayerResult(String name, List<Card> cards, int sum) {
        String message = parseNameAndCards(name, cards) + parseSum(sum);
        writer.write(message);
    }
    
    private String parseNameAndCards(String name, List<Card> cards) {
        return "%s카드: %s".formatted(name, parseCards(cards));
    }
    
    private String parseCards(List<Card> cards) {
        StringJoiner sj = new StringJoiner(", ");
        for (Card card : cards) {
            sj.add(parseCard(card));
        }
        return sj.toString();
    }
    
    private String parseCard(Card card) {
        if (card.getShape() == CardShape.다이아몬드) {
            return parseCardNumber(card) + "다이아몬드";
        }
        if (card.getShape() == CardShape.하트) {
            return parseCardNumber(card) + "하트";
        }
        if (card.getShape() == CardShape.스페이드) {
            return parseCardNumber(card) + "스페이드";
        }
        return parseCardNumber(card) + "클로버";
    }
    
    private String parseCardNumber(Card card) {
        if (card.getNumber() == 1) {
            return "A";
        }
        if (card.getNumber() == 11) {
            return "J";
        }
        if (card.getNumber() == 12) {
            return "Q";
        }
        if (card.getNumber() == 13) {
            return "K";
        }
        return String.valueOf(card.getNumber());
    }
    
    private static String parseSum(int sum) {
        return "- 결과: %d".formatted(sum);
    }

    public void outputFinalWinOrLossHeader() {
        writer.write("## 최종 승패");
    }
    
    public void outputDealerFinalWinOrLoss(int dealerWinningCount,
                                           int dealerDrawingCount,
                                           int dealerLosingCount) {
        writer.write("딜러: %d승 %d무 %d패".formatted(dealerWinningCount, dealerDrawingCount, dealerLosingCount));
    }
    
    public void outputPlayerFinalWinOrLoss(String name, WinningStatus winningStatus) {
        writer.write("%s: %s".formatted(name, winningStatus.name()));
    }
}
