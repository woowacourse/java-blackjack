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

    public static void printCardOpen(Dealer dealer, List<Player> participants) {
        List<String> names = participants.stream()
                .map(Player::getName)
                .toList();
        System.out.println();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), String.join(", ", names));

        printCardByDealer(dealer);
        printCardByPlayers(participants);
    }

    private static void printCardByDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().getFirst();
        String card = EnumMapper.convert(firstCard);
        System.out.println(dealer.getName() + ": " + card);
    }

    private static void printCardByPlayers(List<Player> participants) {
        participants.forEach(OutputView::printCardByPlayer);
        System.out.println();
    }

    public static void printCardByPlayer(Participant participant) {
        List<String> cards = participant.getCards()
                .stream()
                .map(EnumMapper::convert)
                .toList();
        System.out.printf("%s카드: %s%n", participant.getName(), String.join(", ", cards));
    }

    public static void printToOpenDealerNewCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16 이하라 한장의 카드를 더 받았습니다.%n", dealer.getName());
    }

    public static void printFinalCards(Dealer dealer, List<Player> participants) {
        System.out.println();
        printCardByParticipantWithScore(dealer);
        participants.forEach(OutputView::printCardByParticipantWithScore);
    }

    public static void printCardByParticipantWithScore(Participant participant) {
        int sum = participant.calculateTotalScore();
        List<String> cards = participant.getCards()
                .stream()
                .map(EnumMapper::convert)
                .toList();
        System.out.printf("%s 카드: %s - 결과: %d%n", participant.getName(), String.join(", ", cards), sum);
    }
}