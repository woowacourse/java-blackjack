package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.Result;
import dto.ResultDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA = ", ";
    private static final String HAND_OUT_FORMAT = "%s와 %s에게 2장의 카드를 나누었습니다.";
    private static final String STATUS_FORMAT = "%s: %s";
    private static final String DEALER_HIT_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARDS_SCORE_MESSAGE = "%s - 결과: %d";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String RECORD_FORMAT = "%d승 %d무 %d패";
    private static final String WIN_MESSAGE = "승";
    private static final String DRAW_MESSAGE = "무";
    private static final String LOSE_MESSAGE = "패";

    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void showInitialTurnStatus(Players players, Dealer dealer) {
        System.out.print(System.lineSeparator());
        System.out.printf(HAND_OUT_FORMAT, dealer.getName(), String.join(COMMA, players.toNames()));
        System.out.print(System.lineSeparator());

        showInitialDealerCardStatus(dealer);
        for (Player player : players.getPlayers()) {
            showPlayerCardStatus(player);
        }
        System.out.print(System.lineSeparator());
    }

    public void showPlayerCardStatus(Player player) {
        System.out.println(showCardStatus(player));
    }

    private String showCardStatus(Participant participant) {
        return String.format(STATUS_FORMAT, participant.getName(),
            toString(participant.getCards()));
    }

    private void showInitialDealerCardStatus(Dealer dealer) {
        List<Card> card = dealer.getCards().subList(0, 1);
        System.out.printf(STATUS_FORMAT, dealer.getName(), toString(card));
        System.out.print(System.lineSeparator());
    }

    private String toString(List<Card> cards) {
        List<String> cardRepresentation = cards.stream()
            .map(card -> card.getDenomination() + card.getSymbol())
            .collect(Collectors.toList());

        return String.join(COMMA, cardRepresentation);
    }

    public void showFinalTurnStatus(Players players, Dealer dealer) {
        System.out.print(System.lineSeparator());
        System.out.printf(CARDS_SCORE_MESSAGE, showCardStatus(dealer), dealer.calculateScore());
        System.out.print(System.lineSeparator());

        for (Player player : players.getPlayers()) {
            System.out.printf(CARDS_SCORE_MESSAGE, showCardStatus(player), player.calculateScore());
            System.out.print(System.lineSeparator());
        }
    }

    public void showDealerHitCardMessage() {
        System.out.print(System.lineSeparator());
        System.out.println(DEALER_HIT_CARD_MESSAGE);
    }

    public void showResult(ResultDto resultDto, List<String> names, List<Result> results) {
        System.out.print(System.lineSeparator());

        System.out.println(FINAL_RESULT_MESSAGE);
        showDealerResult(resultDto);
        showPlayersResult(names, results);
    }

    private void showDealerResult(ResultDto resultDto) {
        String record = String.format(RECORD_FORMAT, resultDto.getWinCount(),
            resultDto.getDrawCount(), resultDto.getLoseCount());
        System.out.printf(STATUS_FORMAT, resultDto.getDealerName(), record);
        System.out.print(System.lineSeparator());
    }

    private void showPlayersResult(List<String> names, List<Result> results) {
        for (int i = 0; i < names.size(); i++) {
            System.out.printf(STATUS_FORMAT, names.get(i), showPlayerResult(results.get(i)));
            System.out.print(System.lineSeparator());
        }
    }

    private String showPlayerResult(Result playerResult) {
        if (playerResult == Result.WIN) {
            return WIN_MESSAGE;
        }
        if (playerResult == Result.LOSE) {
            return LOSE_MESSAGE;
        }
        return DRAW_MESSAGE;
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
