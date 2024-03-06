import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printParticipants(Participants participants) {
        List<Name> names = participants.getNames();
        List<String> names2 = new ArrayList<>();
        for (Name name : names) {
            names2.add(name.getValue());
        }
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", String.join(", ", names2));
        System.out.println();
    }

    public static void printDealerCard(Dealer dealer) {
        Name name = dealer.getName();
        List<Card> cards = dealer.getCards();
        System.out.printf("%s카드: %s", name.getValue(), cards.get(0).getRank().getName() + cards.get(0).getShape().getName());
        System.out.println();
    }

    public static void printCard(Participant participant) {
        Name name = participant.getName();
        List<Card> cards = participant.getCards();
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(card.getRank().getName() + card.getShape().getName());
        }
        System.out.printf("%s카드: %s", name.getValue(), String.join(", ", cardNames));
        System.out.println();
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
