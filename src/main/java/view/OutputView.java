package view;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.game.BlackJackGame;
import domain.game.Referee;
import domain.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OutputView {

    public static final String DEALER_CARD_CONDITION_FORMAT = "%s: %s%n";
    public static final String PARTICIPANT_CARD_CONDITION_FORMAT = "%s카드: %s%n";
    
    private OutputView() {
        throw new IllegalArgumentException("인스턴스를 생성할 수 없는 클래스입니다.");
    }
    
    public static void printParticipantNamesGuide() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printPlayersInformation(BlackJackGame blackJackGame) {
        printPlayerNames(blackJackGame);
        printPlayerCardConditions(blackJackGame);
    }

    private static void printPlayerNames(BlackJackGame blackJackGame) {
        Player dealer = blackJackGame.getDealer();
        List<String> participantsNames = parseParticipantsNames(blackJackGame);
        
        System.out.printf("%n%s와 %s에게 2장을 나누어주었습니다.%n",
                dealer.getName(),
                String.join(", ", participantsNames));
    }
    
    private static List<String> parseParticipantsNames(BlackJackGame blackJackGame) {
        return blackJackGame.getParticipants().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private static void printPlayerCardConditions(BlackJackGame blackJackGame) {
        printDealerCardCondition(blackJackGame.getDealer());
        printParticipantCardCondition(blackJackGame.getParticipants());
    }

    private static void printDealerCardCondition(Player dealer) {
        Card card = dealer.getCards().get(0);
        printPlayerCardCondition(dealer, DEALER_CARD_CONDITION_FORMAT, parseCardInformation(card));
    }

    public static void printParticipantCardCondition(List<Player> participants) {
        for (Player participant : participants) {
            printPlayerCardCondition(participant, PARTICIPANT_CARD_CONDITION_FORMAT, parseCardsInformation(participant.getCards()));
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
        return numberDescription.concat(shapeDescription);
    }

    private static void printPlayerCardCondition(Player player, String format, String cardsDisplay) {
        System.out.printf(format, player.getName(), cardsDisplay);
    }

    public static void printAddCardGuide(String name) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
    }

    public static void printBustMessage(String name) {
        System.out.printf("%s님은 더이상 카드를 뽑을 수 없습니다.%n", name);
    }

    public static void printGiveDealerCardMessage() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayersFinalInformation(List<Player> players) {
        println("");
        for (Player player : players) {
            printPlayerCardCondition(player, "%s카드: %s", parseCardsInformation(player.getCards()));
            System.out.printf(" - 결과: %d%n", player.getTotalScore().getScore());
        }
    }

    public static void printPlayersGameResults(BlackJackGame blackJackGame, Referee referee) {
        println("\n## 최종 수익");
//        printDealerGameResult(referee);
//        printParticipantsGameResult(blackJackGame, referee);
    }

//    private static void printDealerGameResult(Referee referee) {
//        System.out.printf("딜러: %s%n", parseDealerGameResultDisplay(referee));
//    }
//
//    private static String parseDealerGameResultDisplay(Referee referee) {
//        Map<ParticipantGameResult, Integer> dealerGameResults = referee.dealerGameResults();
//
//        return Arrays.stream(ParticipantGameResult.values())
//                .filter(Predicate.not(gameResult -> OutputView.isGameResultCountZero(gameResult, dealerGameResults)))
//                .map(gameResult -> dealerGameResults.get(gameResult) + gameResult.getSymbol())
//                .collect(Collectors.joining(" "));
//
//    }
//
//    private static boolean isGameResultCountZero(ParticipantGameResult participantGameResult, Map<ParticipantGameResult, Integer> dealerGameResults) {
//        return dealerGameResults.getOrDefault(participantGameResult, 0) == 0;
//    }
//
//    private static void printParticipantsGameResult(BlackJackGame blackJackGame, Referee referee) {
//        List<Player> participants = blackJackGame.getParticipants();
//        Map<Player, ParticipantGameResult> participantsGameResults = referee.participantsGameResults();
//
//        participants.stream()
//                .map(participant -> parseParticipantGameResultDisplay(participant, participantsGameResults))
//                .forEach(OutputView::println);
//    }
//
//    private static String parseParticipantGameResultDisplay(Player participant, Map<Player, ParticipantGameResult> participantsGameResults) {
//        ParticipantGameResult participantGameResult = participantsGameResults.get(participant);
//        return String.format("%s: %s", participant.getName(), participantGameResult.getSymbol());
//    }

    public static void println(String message) {
        System.out.println(message);
    }
}
