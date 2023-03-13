package view;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.game.Referee;
import domain.player.Player;
import domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String PLAYER_PROFIT_STATUS_FORMAT = "%s: %.0f";
    private static final String PLAYER_CARD_STATUS_FORMAT = "%s 카드: %s";
    
    private OutputView() {
        throw new IllegalArgumentException("인스턴스를 생성할 수 없는 클래스입니다.");
    }
    
    public static void printPlayersInformation(Players players) {
        printPlayerNames(players);
        printPlayerCardsStatus(players);
    }
    
    private static void printPlayerNames(Players players) {
        Player dealer = players.getDealer();
        List<String> participantsNames = parseParticipantsNames(players);
        
        System.out.printf(NEW_LINE + "%s와 %s에게 2장을 나누어주었습니다." + NEW_LINE,
                dealer.getName(),
                String.join(", ", participantsNames));
    }
    
    private static List<String> parseParticipantsNames(Players players) {
        return players.getParticipants().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private static void printPlayerCardsStatus(Players players) {
        printDealerCardStatus(players.getDealer());
        printParticipantCardStatus(players.getParticipants());
    }
    
    private static void printDealerCardStatus(Player dealer) {
        Card card = dealer.getCards().get(0);
        printPlayerCardStatus(dealer, PLAYER_CARD_STATUS_FORMAT + NEW_LINE, parseCardInformation(card));
    }
    
    public static void printParticipantCardStatus(List<Player> participants) {
        for (Player participant : participants) {
            printPlayerCardStatus(participant, PLAYER_CARD_STATUS_FORMAT + NEW_LINE, parseCardsInformation(participant.getCards()));
        }
    }
    
    private static String parseCardsInformation(List<Card> cards) {
        return cards.stream()
                .map(OutputView::parseCardInformation)
                .collect(Collectors.joining(", "));
    }
    
    private static String parseCardInformation(Card card) {
        Number number = card.getNumber();
        String numberDescription = number.getSymbol();
        
        Shape shape = card.getShape();
        String shapeDescription = shape.getSymbol();
        return numberDescription + shapeDescription;
    }
    
    private static void printPlayerCardStatus(Player player, String format, String cardsDisplay) {
        System.out.printf(format, player.getName(), cardsDisplay);
    }
    
    public static void printGiveDealerCardMessage() {
        println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
    
    public static void printPlayersFinalInformation(Players players) {
        println("");
        for (Player player : players.getPlayers()) {
            printPlayerCardStatus(player, PLAYER_CARD_STATUS_FORMAT, parseCardsInformation(player.getCards()));
            System.out.printf(" - 결과: %d%n", player.getTotalScore().getScore());
        }
    }
    
    public static void printPlayersGameResults(Players players, Referee referee) {
        println(NEW_LINE + "## 최종 수익");
        println(parsePlayersResult(players, referee));
    }
    
    private static String parsePlayersResult(Players players, Referee referee) {
        return players.getPlayers().stream()
                .map(player -> parsePlayerGameResult(player, referee))
                .collect(Collectors.joining(NEW_LINE));
    }
    
    private static String parsePlayerGameResult(Player player, Referee referee) {
        return String.format(PLAYER_PROFIT_STATUS_FORMAT, player.getName(), referee.findProfitByPlayer(player));
    }
    
    public static void println(String message) {
        System.out.println(message);
    }
}
