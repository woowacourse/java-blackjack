package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Player;
import blackjack.domain.result.ProfitResult;
import blackjack.dto.ParticipantDto;

import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.card.Deck.TOP_CARD;

public class OutputView {
    private static final String ENTER_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ENTER_BETTING_MONEY = "%s의 베팅 금액은?";
    private static final String DISTRIBUTE_MESSAGE_FORM = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CURRENT_CARD_FORM = "%s카드 : %s";
    private static final String ASK_DRAW_CARD_FORM = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String CARD_AND_SCORE_RESULT = "%s - 결과 : %d";
    private static final String FINAL_RESULT_TITLE = "## 최종 수익";
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

    public static void askBettingMoney(Player player) {
        System.out.printf(ENTER_BETTING_MONEY, player.getName().getName());
        printNewLine();
    }

    public static void distributeFirstTwoCard(List<ParticipantDto> playersDto, ParticipantDto dealerDto) {
        distributeCardMessage(playersDto);
        showDealerFirstCard(dealerDto);
        showCards(playersDto);
        printNewLine();
    }

    public static void distributeCardMessage(List<ParticipantDto> players) {
        String playerName = players.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.printf(DISTRIBUTE_MESSAGE_FORM + "%n", playerName);
    }

    public static void showDealerFirstCard(ParticipantDto dealer) {
        Card card = dealer.getCards().get(TOP_CARD);
        System.out.printf(CURRENT_CARD_FORM + "%n", dealer.getName(), cardForm(card));
    }

    private static String cardForm(Card card) {
        Denomination denomination = card.getDenomination();
        Shape shape = card.getShape();
        return denomination.getName() + shape.getName();
    }

    public static void showCards(List<ParticipantDto> players) {
        for (ParticipantDto player : players) {
            showCards(player);
        }
    }

    public static void showCards(ParticipantDto participant) {
        System.out.println(getCardsMessageForm(participant));
    }

    public static void askOneMoreCard(ParticipantDto player) {
        System.out.printf(ASK_DRAW_CARD_FORM + "%n", player.getName());
    }

    public static void dealerReceiveOneCard() {
        System.out.println(DEALER_DRAW_ONE_CARD);
    }

    public static void showAllCards(List<ParticipantDto> players, ParticipantDto dealer) {
        int dealerScore = dealer.getFinalScore();
        printNewLine();
        System.out.printf(CARD_AND_SCORE_RESULT + "%n", getCardsMessageForm(dealer), dealerScore);
        for (ParticipantDto player : players) {
            int playerScore = player.getFinalScore();
            System.out.printf(CARD_AND_SCORE_RESULT + "%n", getCardsMessageForm(player), playerScore);
        }
    }

    private static String getCardsMessageForm(ParticipantDto participant) {
        String allCards = participant.getCards().stream()
                .map(OutputView::cardForm)
                .collect(Collectors.joining(DELIMITER));
        return String.format(CURRENT_CARD_FORM, participant.getName(), allCards);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void showFinalProfitResult(ProfitResult result) {
        printNewLine();
        System.out.println(FINAL_RESULT_TITLE);
        result.getProfitResult()
                .forEach((key, value) -> System.out.println(key.getName().getName() + ": " + value.toString()));
    }
}
