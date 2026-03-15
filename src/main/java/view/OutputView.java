package view;

import java.util.List;
import model.card.Card;
import model.paticipant.Dealer;
import model.paticipant.Participant;
import model.paticipant.Player;
import view.mapper.EnumMapper;

public class OutputView {

    private OutputView() {
    }

    public static void printCardOpen(Dealer dealer, List<Player> players) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        System.out.println();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), String.join(", ", names));

        printCardByDealer(dealer);
        printCardByPlayers(players);
    }

    public static void printCardByPlayer(Player player) {
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s카드: %s%n", player.getName(), String.join(", ", cards));
    }


    public static void printFinalCards(Participant dealer, List<Player> players) {
        System.out.println();
        printCardByPlayerWithScore(dealer);
        players.forEach(OutputView::printCardByPlayerWithScore);
    }

    public static void printCardByPlayerWithScore(Player participant) {
        int sum = participant.calculateTotalScore();
        List<String> cards = participant.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s 카드: %s - 결과: %d%n", participant.getName(), String.join(", ", cards), sum);
    }

    public static void printCardByPlayerWithScore(Participant participant) {
        int sum = participant.calculateTotalScore();
        List<String> cards = participant.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s 카드: %s - 결과: %d%n", participant.getName(), String.join(", ", cards), sum);
    }

    private static String convert(Card card) {
        return EnumMapper.CARD_VALUE_MAPPER.get(card.value()) + EnumMapper.CARD_SHAPE_MAPPER.get(card.shape());
    }

    public static void printToOpenDealerNewCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16 이하라 한장의 카드를 더 받았습니다.%n", dealer.getName());
    }

    private static void printCardByDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().getFirst();
        String card = convert(firstCard);
        System.out.println(dealer.getName() + ": " + card);
    }

    private static void printCardByPlayers(List<Player> players) {
        players.forEach(OutputView::printCardByPlayer);
        System.out.println();
    }
}