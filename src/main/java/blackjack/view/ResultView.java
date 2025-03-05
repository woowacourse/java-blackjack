package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final Map<Shape, String> SHAPE_KOREAN = Map.of(
            Shape.SPADE, "스페이드",
            Shape.DIAMOND, "다이아몬드",
            Shape.HEART, "하트",
            Shape.CLOB, "클로버"
    );
    private static final String LINE = System.lineSeparator();
    private static final String NAME_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COMMA = ", ";
    private static final String CARD_FORMAT = "%s카드: %s";

    public void printSpreadCard(final Participants participants) {
        printNames(participants);
        printCards(participants);
    }

    private static void printNames(final Participants participants) {
        final String joinedNames = participants.getPlayers().stream()
                .map(Player::getNickname)
                .collect(Collectors.joining(COMMA));
        System.out.printf(LINE + NAME_FORMAT + LINE, joinedNames);
    }

    private void printCards(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final String dealerMessage = String.format(CARD_FORMAT, dealer.getNickname(),
                getCardMessage(dealer.getCards()));
        System.out.println(dealerMessage);
        final List<Player> players = participants.getPlayers();
        players.stream()
                .map(player -> String.format(CARD_FORMAT, player.getNickname(),
                        getCardMessage(player.getCards())))
                .forEach(System.out::println);
    }

    private String getCardMessage(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getDenominationName() + getShapeName(card.getShape()))
                .collect(Collectors.joining(COMMA));
    }

    private String getShapeName(final Shape shape) {
        return SHAPE_KOREAN.get(shape);
    }
}
