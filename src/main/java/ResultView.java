import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private final String LINE_SEPARATOR = System.lineSeparator();

    public void printPlayerCards(Gamer gamer) {
        System.out.printf("%s카드: ", gamer.getPlayerName());
        System.out.println(resolvePlayerCards(gamer));
    }

    private String resolvePlayerCards(Gamer gamer) {
        List<String> cards = new ArrayList<>();
        for (Card card : gamer.getCards()) {
            CardNumber cardNumber = card.getCardNumber();
            CardShape cardShape = card.getCardShape();
            cards.add(resolveCardNumber(cardNumber) + cardShape.getShape());
        }
        return String.join(", ", cards);
    }

    private String resolveCardNumber(CardNumber cardNumber) {
        if (cardNumber.equals(CardNumber.A) || cardNumber.equals(CardNumber.J)
                || cardNumber.equals(CardNumber.Q) || cardNumber.equals(CardNumber.K)) {
            return cardNumber.name();
        }
        return String.valueOf(cardNumber.getScore());
    }

    public void printInitialCards(Gamers gamers) {
        String dealerName = gamers.callDealer().getPlayerName();
        String playersNames = gamers.callPlayers().stream()
                .map(Gamer::getPlayerName)
                .collect(Collectors.joining(", "));
        System.out.println(LINE_SEPARATOR + String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames));
    }
}
