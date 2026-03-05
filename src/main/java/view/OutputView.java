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

    public static void printInitDeck(List<PlayerResult> players, PlayerResult dealer) {
        List<String> playerNames = players.stream().map(p -> p.name().get()).toList();
        printInitDeckDrawMessage(playerNames);
        printDealerInitDeck(dealer.deck().getFirst());
        printPlayersCurrentDeck(players);
    }

    public static void printPlayerCurrentDeck(PlayerResult playerResult) {
        List<String> cardString = playerResult.deck().stream().map(Card::getString).toList();
        System.out.print(playerResult.name().get() + "카드: " + String.join(", ", cardString));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }


    public static void printPlayersScore(List<PlayerResult> players) {
        for(PlayerResult playerResult : players) {
            printPlayerScore(playerResult);
            printNewLine();
        }
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printResult(ParticipantWinning participantWinning) {
        System.out.println("## 최종 승패");
        printDealerResult(participantWinning.dealerWinning());
        printPlayersResult(participantWinning.playersWinning());
    }


    private static void printInitDeckDrawMessage(List<String> players) {
        System.out.println("딜러와 " + String.join(", ", players) + "에게 2장을 나누었습니다.");
    }

    private static void printDealerInitDeck(Card card) {
        System.out.println("딜러카드: " + card.getString());
    }

    private static void printPlayersCurrentDeck(List<PlayerResult> players) {
        for(PlayerResult playerResult : players) {
            printPlayerCurrentDeck(playerResult);
            printNewLine();
        }
    }

    private static void printPlayerScore(PlayerResult playerResult) {
        List<String> cardString = playerResult.deck().stream().map(Card::getString).toList();
        System.out.print(playerResult.name().get() + "카드: " + String.join(", ", cardString) + " - 결과: " + playerResult.score());
    }

    private static void printDealerResult(DealerWinning dealerWinning) {
        System.out.print("딜러:");
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
        System.out.println(playerWinning.name().get() + ": " + playerWinning.matchStatus().getStatus());
    }




}
