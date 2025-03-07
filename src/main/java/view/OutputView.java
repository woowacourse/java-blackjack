package view;

import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final Map<Number, String> NUMBER_SYMBOL_MAP = new HashMap<>(
            Map.of(ACE, "A", QUEEN, "Q", JACK, "J", KING, "K"));
    private static final String COMMA_DELIMITER = ", ";

    private final String HIT_CARDS = "딜러와 %s에게 2장을 나누었습니다.%n";
    private final String PLAYER_CARDS = "%s카드: ";
    private final String DEALER_CARDS = "딜러카드: ";
    private final String HIT_DEALER_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private final String SCORE = " - 결과: %d";
    private final String RESULT_INTRO = "## 최종 승패";
    private final String DEALER_RESULT = "딜러: %d승 %d패";
    private final String WINNER_RESULT = "%s: 승";
    private final String LOSER_RESULT = "%s: 패";
    private final String NEW_LINE = System.lineSeparator();

    public void printParticipant(Players players, Dealer dealer){
        printHitNotice(players);
        printDealerDeck(dealer);

        for(Player player : players.getPlayers()){
            printPlayerDeck(player);
        }
        System.out.print(NEW_LINE);
    }

    private void printDealerDeck(Dealer dealer) {
        System.out.println(DEALER_CARDS + toSymbol(dealer.getHand().getCards().getFirst()));
    }

    private void printHitNotice(Players players) {
        List<String> playersName = new ArrayList<>();

        for(Player player : players.getPlayers()){
            playersName.add(player.getName());
        }
        System.out.print(NEW_LINE);
        System.out.printf(HIT_CARDS, String.join(COMMA_DELIMITER, playersName));
    }

    public void printPlayerDeck(Player player) {
        System.out.printf(PLAYER_CARDS, player.getName());
        List<String> cardSymbols = new ArrayList<>();
        for (Card card : player.getHand().getCards()) {
            cardSymbols.add(toSymbol(card));
        }
        System.out.println(String.join(COMMA_DELIMITER, cardSymbols));
    }

    private static String toSymbol(Card card) {
        Number number = card.getNumber();
        Shape shape = card.getShape();
        return NUMBER_SYMBOL_MAP.getOrDefault(number, String.valueOf(number.getScore())) + shape.getShape();
    }

    public void printDrawDealer(Dealer dealer) {
        if(dealer.isUnderThreshold()){
            System.out.print(NEW_LINE);
            System.out.println(HIT_DEALER_CARD);
        }
    }
}
