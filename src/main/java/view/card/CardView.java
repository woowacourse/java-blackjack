package view.card;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.deck.UserDeck;
import domain.user.Dealer;
import domain.user.User;
import domain.user.Users;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardView {
    private final Map<Card, String> cardViewer;

    public CardView() {
        this.cardViewer = Shape.giveShapes().stream()
                .flatMap(shape -> Number.giveNumbers().stream()
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toMap(
                        Function.identity(),
                        card -> NumberViewer.show(card.number()) + ShapeViewer.show(card.shape())
                ));
    }

    public void printStartStatus(Users users) {
        printUserNames(users);
        printUserCards(users);
        System.out.println();
    }

    private void printUserNames(Users users) {
        String names = users.getPlayers()
                .stream()
                .map(player -> player.getName().value())
                .collect(Collectors.joining(", "));
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.%n", names);
    }

    private void printUserCards(Users users) {
        Dealer dealer = users.getDealer();
        Card dealerVisibleCard = dealer.getVisibleCard();
        System.out.println(dealer.getName().value() + ": " + show(dealerVisibleCard));
        users.getPlayers()
                .forEach(this::printPlayerAndDeck);
    }

    public void printBust(User user) {
        printPlayerAndDeck(user);
        System.out.println("버스트!");
    }

    public void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printPlayerAndDeck(User user) {
        System.out.println(joinUserNameAndDeck(user));
    }

    public void printCardsAndSum(Users users) {
        System.out.println();
        users.getUsers()
                .forEach((user) ->
                        System.out.println(joinUserNameAndDeck(user)
                                + " - 결과: " + user.sumUserDeck()));
    }

    private String joinUserNameAndDeck(User user) {
        return user.getName().value()
                + "카드: "
                + joinDeck(user.getUserDeck());
    }

    private String joinDeck(UserDeck userDeck) {
        return userDeck.getCards()
                .stream()
                .map(this::show)
                .collect(Collectors.joining(", "));
    }

    private String show(Card card) {
        return cardViewer.get(card);
    }
}
