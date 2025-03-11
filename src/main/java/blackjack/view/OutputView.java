package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBettingBlackjackCardHand;
import blackjack.view.writer.Writer;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    private final Writer writer;
    
    public OutputView(final Writer writer) {
        this.writer = writer;
    }
    
    public void outputInitialCards(final DealerBlackjackCardHand dealerHand, final List<PlayerBettingBlackjackCardHand> playerHands) {
        StringJoiner sj = new StringJoiner(LINE_SEPARATOR);
        sj.add(outputInitialCardOpeningMessage(extractPlayerNames(playerHands)));
        sj.add(parseNameAndCards("딜러", List.of(dealerHand.getInitialCard())));
        for (PlayerBettingBlackjackCardHand playerHand : playerHands) {
            sj.add(parseNameAndCards(playerHand.getPlayerName(), playerHand.getCards()));
        }
        writer.write(sj.toString());
    }
    
    private List<String> extractPlayerNames(final List<PlayerBettingBlackjackCardHand> playerHands) {
        return playerHands.stream()
                .map(PlayerBettingBlackjackCardHand::getPlayerName)
                .toList();
    }
    
    private String outputInitialCardOpeningMessage(List<String> names) {
        StringJoiner sj = new StringJoiner(", ");
        for (String name : names) {
            sj.add(name);
        }
        return LINE_SEPARATOR + "딜러와 %s에게 2장을 나누었습니다.".formatted(sj.toString());
    }
    
    public void outputAddingMessage(String name) {
        final String message = LINE_SEPARATOR + "이제 %s가 카드를 더 받는 순서입니다.".formatted(name);
        writer.write(message);
    }
    
    public void addTo21Warning() {
        writer.write("카드 총합이 21이기 때문에 더 받을 수 없습니다.");
    }
    
    public void bustWarning() {
        writer.write("버스트이기 때문에 더 받을 수 없습니다.");
    }
    
    public void outputCardsAndSum(PlayerBettingBlackjackCardHand playerHand) {
        final String message = parseCards(playerHand.getCards()) + parseSum(playerHand.getBlackjackSum());
        writer.write(message);
    }
    
    public void outputDealerAddedCards(int addedCardsSize) {
        if (addedCardsSize != 0) {
            String message = LINE_SEPARATOR + "딜러는 16이하라 %d장의 카드를 더 받았습니다.".formatted(addedCardsSize) + LINE_SEPARATOR;
            writer.write(message);
        }
    }
    
    public void outputOpenCards(
            final DealerBlackjackCardHand dealerHand,
            final List<PlayerBettingBlackjackCardHand> playerHands
    ) {
        StringJoiner sj = new StringJoiner(LINE_SEPARATOR);
        sj.add(parseNameAndCards("딜러", dealerHand.getCards()) + parseFinalSum(dealerHand.getBlackjackSum()));
        for (PlayerBettingBlackjackCardHand playerHand : playerHands) {
            sj.add(parseNameAndCards(playerHand.getPlayerName(), playerHand.getCards()) + parseFinalSum(playerHand.getBlackjackSum()));
        }
        writer.write(sj.toString());
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

    public void outputFinalProfit(
            final DealerBlackjackCardHand dealerHand,
            final List<PlayerBettingBlackjackCardHand> playerHands
    ) {
        StringJoiner sj = new StringJoiner(LINE_SEPARATOR);
        sj.add("## 최종 수익");
        sj.add("딜러: %d".formatted((int) getTotalProfitOfDealer(dealerHand, playerHands)));
        for (PlayerBettingBlackjackCardHand playerHand : playerHands) {
            sj.add("%s: %d".formatted(
                    playerHand.getPlayerName(),
                    (int) playerHand.compareHand(dealerHand)
            ));
        }
        writer.write(sj.toString());
    }

    private static double getTotalProfitOfDealer(final DealerBlackjackCardHand dealerHand, final List<PlayerBettingBlackjackCardHand> playerHands) {
        final Map<String, Double> profits = playerHands.stream()
                .collect(Collectors.toMap(
                        PlayerBettingBlackjackCardHand::getPlayerName,
                        hand -> hand.compareHand(dealerHand)
                ));

        return -profits.values().stream().mapToDouble(Double::doubleValue).sum();
    }
}
