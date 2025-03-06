package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
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
    private static final String TITLE_DEALER_EXTRA_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_SUM_FORMAT = "%s카드: %s - 결과: %d";

    public void printSpreadCard(final Participants participants) {
        printNames(participants);
        printCards(participants);
    }

    public void printParticipantTotalCards(final Gamer gamer) {
        System.out.println(makeParticipantsMessage(gamer));
    }

    public void printDealerExtraCard() {
        System.out.println(LINE + TITLE_DEALER_EXTRA_CARD);
    }

    private void printNames(final Participants participants) {
        final String joinedNames = participants.getPlayers().stream()
                .map(Player::getNickname)
                .collect(Collectors.joining(COMMA));
        System.out.printf(LINE + NAME_FORMAT + LINE, joinedNames);
    }

    private void printCards(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final String dealerMessage = String.format(CARD_FORMAT, dealer.getNickname(),
                getCardMessage(dealer.showOneCard()));
        System.out.println(dealerMessage);
        final List<Player> players = participants.getPlayers();
        players.stream()
                .map(this::makeParticipantsMessage)
                .forEach(System.out::println);
    }

    private String makeParticipantsMessage(final Gamer gamer) {
        return String.format(CARD_FORMAT, gamer.getNickname(),
                getCardMessage(gamer.showAllCard()));
    }

    private String getCardMessage(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getDenominationName() + getShapeName(card.getShape()))
                .collect(Collectors.joining(COMMA));
    }

    private String getShapeName(final Shape shape) {
        return SHAPE_KOREAN.get(shape);
    }

    public void printCardsAndSum(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final String dealerMessage = String.format(CARD_SUM_FORMAT, dealer.getNickname(),
                getCardMessage(dealer.showAllCard()), dealer.calculateMaxSum());
        System.out.println(LINE + dealerMessage);
        final List<Player> players = participants.getPlayers();
        players.stream()
                .map(this::makeParticipantsWithSumMessage)
                .forEach(System.out::println);
    }

    private String makeParticipantsWithSumMessage(final Gamer gamer) {
        return String.format(CARD_SUM_FORMAT, gamer.getNickname(),
                getCardMessage(gamer.showAllCard()), gamer.calculateMaxSum());
    }
}
