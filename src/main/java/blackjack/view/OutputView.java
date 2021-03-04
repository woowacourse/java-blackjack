package blackjack.view;

import blackjack.domain.BlackJackResult;
import blackjack.domain.MatchResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Person;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.gamer.Dealer.DEALER_NAME;

public class OutputView {
    private static final String ENTER_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DISTRIBUTE_MESSAGE_FORM = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CURRENT_CARD_FORM = "%s카드 : %s";
    private static final String ASK_DRAW_CARD_FORM = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String CARD_AND_SCORE_RESULT = "%s - 결과 : %d";
    private static final String PLAYER_RESULT_FORM = "%s: %s";
    private static final String FINAL_RESULT_TITLE = "## 최종 승패";
    private static final String DEALER_DRAW_ONE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";


    private OutputView() {

    }

    public static void printError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void enterPlayersName() {
        System.out.println(ENTER_PLAYERS_NAME);
    }

    public static void distributeCardMessage(Players players) {
        String playerName = players.getPlayers().stream().map(Person::getName).collect(Collectors.joining(", "));
        System.out.printf(DISTRIBUTE_MESSAGE_FORM + "%n", playerName);
    }

    public static void showDealerFirstCard(Dealer dealer) {
        Card card = dealer.getTakenCards().peekCard();
        System.out.printf(CURRENT_CARD_FORM + "%n", dealer.getName(), cardForm(card));
    }

    private static String cardForm(Card card) {
        Denomination denomination = card.getDenomination();
        Shape shape = card.getShape();
        return denomination.getName() + shape.getName();
    }

    public static void showCards(Person person) {
        System.out.println(getCardsMessageForm(person));
    }

    public static void askOneMoreCard(Player player) {
        System.out.printf(ASK_DRAW_CARD_FORM + "%n", player.getName());
    }

    public static void dealerReceiveOneCard() {
        System.out.println();
        System.out.println(DEALER_DRAW_ONE_CARD);
    }

    public static void showAllCards(Players players, Dealer dealer) {
        System.out.printf((CARD_AND_SCORE_RESULT) + "%n", getCardsMessageForm(dealer), Score.calculatorScore(dealer.getTakenCards()));
        for (Player player : players.getPlayers()) {
            System.out.printf((CARD_AND_SCORE_RESULT) + "%n", getCardsMessageForm(player), Score.calculatorScore(player.getTakenCards()));
        }
    }

    private static String getCardsMessageForm(Person person) {
        String cardsName = person.getTakenCards().getCards().stream().map(OutputView::cardForm).collect(Collectors.joining(", "));
        return String.format(CURRENT_CARD_FORM, person.getName(), cardsName);
    }

    public static void showFinalResult(BlackJackResult blackJackResult) {
        System.out.println();
        System.out.println(FINAL_RESULT_TITLE);

        Map<MatchResult, Integer> dealerResult = blackJackResult.getDealerResult();
        System.out.print(DEALER_NAME + ": ");
        dealerResult.entrySet().stream()
                .filter(entrySet -> entrySet.getValue() != 0)
                .forEach(entrySet -> System.out.print(entrySet.getValue() + entrySet.getKey().getResult()));

        System.out.println();
        blackJackResult.getResult()
                .forEach((key, value) -> System.out.printf(PLAYER_RESULT_FORM + "%n", key.getName(), value.getResult()));
    }
}
