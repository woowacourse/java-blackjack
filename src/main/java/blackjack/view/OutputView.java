package blackjack.view;

import static blackjack.model.card.Rank.ACE;
import static blackjack.model.card.Rank.EIGHT;
import static blackjack.model.card.Rank.FIVE;
import static blackjack.model.card.Rank.FOUR;
import static blackjack.model.card.Rank.JACK;
import static blackjack.model.card.Rank.KING;
import static blackjack.model.card.Rank.NINE;
import static blackjack.model.card.Rank.QUEEN;
import static blackjack.model.card.Rank.SEVEN;
import static blackjack.model.card.Rank.SIX;
import static blackjack.model.card.Rank.TEN;
import static blackjack.model.card.Rank.THREE;
import static blackjack.model.card.Rank.TWO;
import static blackjack.model.card.Suit.CLUB;
import static blackjack.model.card.Suit.DIAMOND;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static blackjack.model.game.BlackjackResult.LOSE;
import static blackjack.model.game.BlackjackResult.PUSH;
import static blackjack.model.game.BlackjackResult.WIN;
import static java.util.Map.entry;

import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.game.BlackjackResult;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.DealerScoreDto;
import blackjack.view.dto.PlayerDto;
import blackjack.view.dto.PlayerScoreDto;
import blackjack.view.dto.ResultDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";

    private static final Map<Rank, String> RANK_LABELS = Map.ofEntries(
            entry(ACE, "A"),
            entry(TWO, "2"),
            entry(THREE, "3"),
            entry(FOUR, "4"),
            entry(FIVE, "5"),
            entry(SIX, "6"),
            entry(SEVEN, "7"),
            entry(EIGHT, "8"),
            entry(NINE, "9"),
            entry(TEN, "10"),
            entry(KING, "K"),
            entry(QUEEN, "Q"),
            entry(JACK, "J")
    );

    private static final Map<BlackjackResult, String> RESULT_LABELS = Map.of(
            WIN, "승",
            LOSE, "패",
            PUSH, "푸시"
    );

    private static final Map<Suit, String> SUIT_LABELS = Map.of(
            DIAMOND, "다이아몬드",
            CLUB, "클로버",
            SPADE, "스페이드",
            HEART, "하트"
    );

    public void printInitialDeal(List<CardDto> dealerCards, List<PlayerDto> players) {
        String joinedPlayerNames = players.stream()
                .map(PlayerDto::playerName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println("딜러와 " + joinedPlayerNames + "에게 2장을 나누었습니다.");

        printDealerCards(dealerCards);
        for (PlayerDto player : players) {
            printPlayerCards(player.playerName(), player.cards());
        }
        System.out.println();
    }

    public void printPlayerCards(String playerName, List<CardDto> cards) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(DELIMITER, cardOutputs);

        System.out.println(playerName + "카드: " + joinedCards);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printScore(DealerScoreDto dealer, List<PlayerScoreDto> players) {
        printDealerScore(dealer);
        for (PlayerScoreDto player : players) {
            printPlayerScore(player.playerName(), player.cards(), player.score());
        }
        System.out.println();
    }

    public void printErrorMessage(Exception error) {
        System.out.println(error.getMessage());
    }

    private void printDealerCards(List<CardDto> cards) {
        printPlayerCards(DEALER_NAME, List.of(cards.getFirst()));
    }

    private void printDealerScore(DealerScoreDto dealer) {
        printPlayerScore(DEALER_NAME, dealer.cards(), dealer.score());
    }

    private void printPlayerScore(String playerName, List<CardDto> cards, int score) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(DELIMITER, cardOutputs);

        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score);
    }

    public void printResult(List<ResultDto> playerResults) {
        System.out.println("## 최종 수익");

        printDealerResult(playerResults);
        playerResults.forEach(this::printPlayerResult);
    }

    private void printDealerResult(List<ResultDto> playerResults) {
        double dealerProfit = playerResults.stream()
                .mapToDouble(ResultDto::profit)
                .sum()
                * -1;

        printPlayerResult(new ResultDto(DEALER_NAME, dealerProfit));
    }

    private void printPlayerResult(ResultDto playerResult) {
        int integerProfit = (int) playerResult.profit();

        System.out.println(playerResult.playerName() + ": " + integerProfit);
    }

    private List<String> parseCardsToOutputs(List<CardDto> cards) {
        return cards.stream()
                .map(this::parseCardToOutput)
                .toList();
    }

    private String parseCardToOutput(CardDto card) {
        String rank = RANK_LABELS.get(card.rank());
        String suit = SUIT_LABELS.get(card.suit());

        return rank + suit;
    }
}
