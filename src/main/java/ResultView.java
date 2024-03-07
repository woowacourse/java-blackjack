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
            cards.add(resolveCardExpression(card));
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
        Dealer dealer = gamers.callDealer();
        List<Player> players = gamers.callPlayers();

        String dealerName = dealer.getPlayerName();
        String playersNames = players.stream()
                .map(Gamer::getPlayerName)
                .collect(Collectors.joining(", "));
        System.out.println(LINE_SEPARATOR + String.format("%s와 %s에게 2장을 나누었습니다.", dealerName, playersNames));

        Card dealerCard = dealer.openOneCard();
        System.out.println(dealerName + ": " + resolveCardExpression(dealerCard));
        for (Gamer gamer : gamers.callPlayers()) {
            printPlayerCards(gamer);
        }
        System.out.print(LINE_SEPARATOR);
    }

    private String resolveCardExpression(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        return resolveCardNumber(cardNumber) + cardShape.getShape();
    }

    public void printDealerHitMessage(Dealer dealer, Card card) {
        System.out.printf(LINE_SEPARATOR + "%s는 16이하라 카드 %s를 더 받았습니다.", dealer.getPlayerName(), resolveCardExpression(card));
    }

    public void printAllGamersResult(Gamers gamers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LINE_SEPARATOR);
        stringBuilder.append(LINE_SEPARATOR);
        for (Gamer gamer : gamers.getGamers()) {
            stringBuilder.append(String.format("%s카드: ", gamer.getPlayerName()));
            stringBuilder.append(resolvePlayerCards(gamer));
            stringBuilder.append(" - 결과: ");
            stringBuilder.append(gamer.finalizeCardsScore());
            stringBuilder.append(LINE_SEPARATOR);
        }
        System.out.println(stringBuilder.toString());
    }
}
