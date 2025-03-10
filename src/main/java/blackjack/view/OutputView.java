package blackjack.view;

import blackjack.domain.WinningStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.view.writer.Writer;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    private final Writer writer;
    
    public OutputView(final Writer writer) {
        this.writer = writer;
    }
    
    public void outputInitialCardOpeningMessage(List<String> names) {
        StringJoiner sj = new StringJoiner(", ");
        for (String name : names) {
            sj.add(name);
        }
        final String message = LINE_SEPARATOR + "딜러와 %s에게 2장을 나누었습니다.".formatted(sj.toString());
        writer.write(message);
    }
    
    public void outputDealerCards(List<Card> cards) {
        String message = parseNameAndCards("딜러", cards);
        writer.write(message);
    }
    
    public void outputPlayerCards(PlayerBlackjackCardHand playerHand) {
        String message = parseNameAndCards(playerHand.getPlayerName(), playerHand.getCards());
        writer.write(message);
    }
    
    public void outputAddingMessage(String name) {
        final String message = LINE_SEPARATOR + "이제 %s가 카드를 더 받는 순서입니다.".formatted(name);
        writer.write(message);
    }
    
    public void is21Warning() {
        writer.write("카드 총합이 21이기 때문에 더 받을 수 없습니다.");
    }
    
    public void bustWarning() {
        writer.write("버스트이기 때문에 더 받을 수 없습니다.");
    }
    
    public void outputCardsAndSum(PlayerBlackjackCardHand playerHand) {
        final String message = parseCards(playerHand.getCards()) + parseSum(playerHand.getBlackjackSum());
        writer.write(message);
    }
    
    public void outputDealerAddedCards(int addedCardsSize) {
        if (addedCardsSize != 0) {
            String message = LINE_SEPARATOR + "딜러는 16이하라 %d장의 카드를 더 받았습니다.".formatted(addedCardsSize) + LINE_SEPARATOR;
            writer.write(message);
        }
    }
    
    public void outputDealerCardsAndResult(DealerBlackjackCardHand dealerHand) {
        String message = parseNameAndCards("딜러", dealerHand.getCards()) + parseFinalSum(dealerHand.getBlackjackSum());
        writer.write(message);
    }
    
    public void outputPlayerCardsAndResult(PlayerBlackjackCardHand playerHand) {
        String message = parseNameAndCards(playerHand.getPlayerName(), playerHand.getCards())
                + parseFinalSum(playerHand.getBlackjackSum());
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
        return card.getNumber().name().replace("NUMBER_", "")
                + card.getShape().name();
    }

    private String parseFinalSum(int sum) {
        return " - 결과: %d".formatted(sum);
    }
    
    private String parseSum(int sum) {
        return " - 합계: %d".formatted(sum);
    }

    public void outputFinalWinOrLossMessage() {
        writer.write(LINE_SEPARATOR + "<최종 승패>");
    }
    
    public void outputDealerFinalWinOrLoss(
            int dealerWinningCount,
            int dealerDrawingCount,
            int dealerLosingCount
    ) {
        writer.write("딜러: %d승 %d무 %d패".formatted(dealerWinningCount, dealerDrawingCount, dealerLosingCount));
    }
    
    public void outputPlayerFinalWinOrLoss(String name, WinningStatus winningStatus) {
        writer.write("%s: %s".formatted(name, winningStatus.name()));
    }
}
