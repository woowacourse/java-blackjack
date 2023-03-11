package factory;

import static factory.DrawnCardsFactory.createBlackjackCards;
import static factory.DrawnCardsFactory.createEmptyCards;
import static factory.DrawnCardsFactory.createOverCards;
import static factory.DrawnCardsFactory.createStayCards;
import static factory.StatusFactory.createStatus;

import domain.card.DrawnCards;
import domain.participants.Player;
import domain.participants.Status;

public class PlayerFactory {

    public static Player createPlayer(final Status status, final DrawnCards drawnCards) {
        return new Player(status, drawnCards);
    }

    public static Player createPlayerWithEmptyCards(final String name) {
        return new Player(createStatus(name, 1000), createEmptyCards());
    }

    public static Player createPlayerWithStayCards(final String name) {
        return new Player(createStatus(name, 1000), createStayCards());
    }

    public static Player createPlayerWithOverCards(final String name) {
        return new Player(createStatus(name, 1000), createOverCards());
    }

    public static Player createPlayerWithBlackjackCards(final String name) {
        return new Player(createStatus(name, 1000), createBlackjackCards());
    }
}
