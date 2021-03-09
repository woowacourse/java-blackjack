package blackjack.view;

import blackjack.domain.BlackJackResult;
import blackjack.domain.MatchResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participants;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DISTRIBUTE_MESSAGE_FORM = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CURRENT_CARD_FORM = "%s카드 : %s";
    private static final String ASK_DRAW_CARD_FORM = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String CARD_AND_SCORE_RESULT = "%s - 결과 : %d";
    private static final String PLAYER_RESULT_FORM = "%s: %s";

    private OutputView() {

    }

    public static void printError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void enterPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void distributeCardMessage(List<Participants> participants) {
        String playerName = participants
                .stream()
                .map(Participants::getName)
                .filter(name -> !name.equals(Dealer.DEALER_NAME))
                .collect(Collectors.joining(","));
        System.out.printf((DISTRIBUTE_MESSAGE_FORM) + "%n", playerName);
    }

    public static void showDealerFirstCard(Card card) {
        System.out.println("딜러: " + cardForm(card));
    }

    private static String cardForm(Card card) {
        Denomination denomination = card.getDenomination();
        Shape shape = card.getShape();
        return denomination.getName() + shape.getName();
    }

    public static void showCards(Participants participants) {
        System.out.println(getCardsMessageForm(participants));
    }

    public static void askOneMoreCard(Player player) {
        System.out.printf((ASK_DRAW_CARD_FORM) + "%n", player.getName());
    }

    public static void dealerReceiveOneCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showAllCards(Players players, Dealer dealer) {
        System.out.printf("%n" + (CARD_AND_SCORE_RESULT) + "%n", getCardsMessageForm(dealer), Score.calculatorScore(dealer.getTakenCards()));
        for (Player player : players.getPlayers()) {
            System.out.printf((CARD_AND_SCORE_RESULT) + "%n", getCardsMessageForm(player), Score.calculatorScore(player.getTakenCards()));
        }
    }

    public static void showFinalResult(BlackJackResult blackJackResult) {
        System.out.println("\n## 최종 승패");

        Map<MatchResult, Integer> dealerResult = blackJackResult.getDealerResult();
        System.out.printf("딜러: ");
        dealerResult.entrySet().stream()
                .filter(entrySet -> entrySet.getValue() != 0)
                .forEach(entrySet -> System.out.printf(entrySet.getValue() + entrySet.getKey().getResult()));

        System.out.println();
        blackJackResult.getResult()
                .forEach((key, value) -> System.out.printf((PLAYER_RESULT_FORM) + "%n", key.getName(), value.getResult()));
    }

    private static String getCardsMessageForm(Participants participants) {
        String cardsName = participants.getTakenCards().getCards().stream().map(OutputView::cardForm).collect(Collectors.joining(","));

        return String.format(CURRENT_CARD_FORM, participants.getName(), cardsName);
    }
}
