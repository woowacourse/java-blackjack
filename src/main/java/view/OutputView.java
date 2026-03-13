package view;

import java.util.List;
import model.card.Card;
import model.paticipant.Dealer;
import model.paticipant.Participant;
import model.paticipant.Player;
import model.paticipant.Players;
import view.mapper.EnumMapper;

public class OutputView {

    private OutputView() {
    }

    public static void printCardOpen(Players players) {
        List<String> names = players.getPlayers()
                .stream()
                .map(Player::getName)
                .toList();

        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", names));
    }

    public static void printCardByPlayers(Players players) {
        players.getPlayers().forEach(OutputView::printCardByPlayer);
        System.out.println();
    }

    public static void printCardByDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().getFirst();
        String card = convert(firstCard);
        System.out.println(dealer.getName() + ": " + card);
    }

    public static void printCardByPlayer(Player player) {
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s카드: %s%n", player.getName(), String.join(", ", cards));
    }


    public static void printCardByPlayerWithScore(Participant participant) {
        int sum = participant.calculateTotalScore();
        List<String> cards = participant.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s 카드: %s - 결과: %d%n", participant.getName(), String.join(", ", cards), sum);
    }

    public static void printCardByPlayerWithScore(Player player) {
        int sum = player.calculateTotalScore();
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s 카드: %s - 결과: %d%n", player.getName(), String.join(", ", cards), sum);
    }

    private static String convert(Card card) {
        return EnumMapper.CARD_VALUE_MAPPER.get(card.value()) + EnumMapper.CARD_SHAPE_MAPPER.get(card.shape());
    }

    public static void printToOpenDealerNewCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16 이하라 한장의 카드를 더 받았습니다.%n", dealer.getName());
    }

    public static void printBlank() {
        System.out.println();
    }
}