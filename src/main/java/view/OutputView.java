package view;


import java.util.List;
import java.util.Map.Entry;
import model.DealerWinning;
import model.MatchStatus;
import model.PlayersWinning;
import model.dto.Card;
import model.dto.ParticipantWinning;
import model.dto.PlayerResult;
import model.dto.PlayerWinning;

public class OutputView {

    private static final String CARD_TEXT = "카드: ";
    private static final String DEALER_DRAW_ONE_CARD_TEXT = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_TEXT = "## 최종 승패";
    private static final String DEALER_INIT_DECK_TEXT = "딜러와 ";
    private static final String INIT_DECK_TEXT = "에게 2장을 나누었습니다.";
    private static final String DEALER_CARD_TEXT = "딜러카드: ";
    private static final String SCORE_TEXT = " - 결과: ";
    private static final String DEALER_TEXT = "딜러:";

    public static void printInitDeck(List<PlayerResult> players, PlayerResult dealer) {
        List<String> playerNames = players.stream().map(p -> p.name().value()).toList();
        printInitDeckDrawMessage(playerNames);
        printDealerInitDeck(dealer.deck().getFirst());
        printPlayersCurrentDeck(players);
        printNewLine();
    }

    public static void printPlayerCurrentDeck(PlayerResult playerResult) {
        List<String> cardString = playerResult.deck().stream().map(Card::getString).toList();
        System.out.println(playerResult.name().value() + CARD_TEXT + String.join(", ", cardString));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println(DEALER_DRAW_ONE_CARD_TEXT);
        printNewLine();
    }


    public static void printPlayersScore(List<PlayerResult> players) {
        for(PlayerResult playerResult : players) {
            printPlayerScore(playerResult);
        }
        printNewLine();
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printResult(ParticipantWinning participantWinning) {
        System.out.println(FINAL_RESULT_TEXT);
        printDealerResult(participantWinning.dealerWinning());
        printPlayersResult(participantWinning.playersWinning());
    }


    private static void printInitDeckDrawMessage(List<String> players) {
        System.out.println(DEALER_INIT_DECK_TEXT + String.join(", ", players) + INIT_DECK_TEXT);
    }

    private static void printDealerInitDeck(Card card) {
        System.out.println(DEALER_CARD_TEXT + card.getString());
    }

    private static void printPlayersCurrentDeck(List<PlayerResult> players) {
        for(PlayerResult playerResult : players) {
            printPlayerCurrentDeck(playerResult);
        }
    }

    private static void printPlayerScore(PlayerResult playerResult) {
        List<String> cardString = playerResult.deck().stream().map(Card::getString).toList();
        System.out.println(playerResult.name().value() + CARD_TEXT + String.join(", ", cardString) + SCORE_TEXT + playerResult.score());
    }

    private static void printDealerResult(DealerWinning dealerWinning) {
        System.out.print(DEALER_TEXT);
        for(Entry<MatchStatus, Integer> matchStatus : dealerWinning.getDealerWinning().entrySet()) {
            printStatusResult(matchStatus.getKey(), matchStatus.getValue());
        }
        printNewLine();
    }


    private static void printStatusResult(MatchStatus matchStatus, Integer quantity) {
        if(quantity > 0) {
            System.out.print(" " + quantity + matchStatus.getStatus());
        }

    }

    private static void printPlayersResult(PlayersWinning playersWinning) {
        for(PlayerWinning playerWinning : playersWinning.getPlayersWinnings()) {
            printPlayerResult(playerWinning);
        }
    }

    private static void printPlayerResult(PlayerWinning playerWinning) {
        System.out.println(playerWinning.name().value() + ": " + playerWinning.matchStatus().getStatus());
    }




}
