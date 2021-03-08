package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.*;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.MatchResult;

import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.card.Cards.TOP_CARD;
import static blackjack.domain.participant.Dealer.DEALER_NAME;

public class OutputView {
    private static final String ENTER_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DISTRIBUTE_MESSAGE_FORM = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CURRENT_CARD_FORM = "%s카드 : %s";
    private static final String ASK_DRAW_CARD_FORM = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String CARD_AND_SCORE_RESULT = "%s - 결과 : %d";
    private static final String PLAYER_RESULT_FORM = "%s: %s";
    private static final String FINAL_RESULT_TITLE = "## 최종 승패";
    private static final String DEALER_DRAW_ONE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void enterPlayersName() {
        System.out.println(ENTER_PLAYERS_NAME);
    }

    public static void distributeCardMessage(Players players) {
        String playerName = players.getPlayers().stream()
                .map(Participant::getName)
                .map(Name::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.printf(DISTRIBUTE_MESSAGE_FORM + "%n", playerName);
    }

    public static void showDealerFirstCard(Dealer dealer) {
        Card card = dealer.getCurrentCards().getCards().get(TOP_CARD);
        System.out.printf(CURRENT_CARD_FORM + "%n", dealer.getName().getName(), cardForm(card));
    }

    private static String cardForm(Card card) {
        Denomination denomination = card.getDenomination();
        Shape shape = card.getShape();
        return denomination.getName() + shape.getName();
    }

    public static void showCards(Participant participant) {
        System.out.println(getCardsMessageForm(participant));
    }

    public static void askOneMoreCard(Player player) {
        System.out.printf(ASK_DRAW_CARD_FORM + "%n", player.getName().getName());
    }

    public static void dealerReceiveOneCard() {
        System.out.println(DEALER_DRAW_ONE_CARD);
    }

    public static void showAllCards(Players players, Dealer dealer) {
        int dealerScore = dealer.getCurrentCards().calculateScore();
        System.out.printf(CARD_AND_SCORE_RESULT + "%n", getCardsMessageForm(dealer), dealerScore);
        for (Player player : players.getPlayers()) {
            int playerScore = player.getCurrentCards().calculateScore();
            System.out.printf(CARD_AND_SCORE_RESULT + "%n", getCardsMessageForm(player), playerScore);
        }
    }

    private static String getCardsMessageForm(Participant participant) {
        String allCards = participant.getCurrentCards().getCards().stream()
                .map(OutputView::cardForm)
                .collect(Collectors.joining(DELIMITER));
        return String.format(CURRENT_CARD_FORM, participant.getName().getName(), allCards);
    }

    public static void showFinalResult(BlackJackResult blackJackResult) {
        printNewLine();
        System.out.println(FINAL_RESULT_TITLE);
        showDealerFinalResult(blackJackResult);
        showPlayersFinalResult(blackJackResult);
    }

    private static void showDealerFinalResult(BlackJackResult blackJackResult) {
        Map<MatchResult, Integer> dealerResult = blackJackResult.getDealerResult();
        System.out.print(DEALER_NAME.getName() + ": ");
        dealerResult.entrySet().stream()
                .filter(entrySet -> entrySet.getValue() != 0)
                .forEach(entrySet -> System.out.print(entrySet.getValue() + entrySet.getKey().getResult() + " "));
        System.out.println();
    }

    private static void showPlayersFinalResult(BlackJackResult blackJackResult) {
        blackJackResult.getResult()
                .forEach((key, value) -> System.out.printf(PLAYER_RESULT_FORM + "%n", key.getName().getName(), value.getResult()));
    }

    public static void printNewLine() {
        System.out.println();
    }
}
