package view;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.attributes.Name;
import domain.participant.player.Players;

public interface BlackjackViewParser {

    default String parsePlayerNames(final Players players) {
        return players.toList()
                .stream()
                .map(player -> parseName(player.name()))
                .reduce((player1, player2) -> player1 + ", " + player2)
                .orElse("");
    }

    default String parseName(final Name name) {
        return name.value();
    }

    default String parseHand(final Hand hand) {
        return hand.toList()
                .stream()
                .map(this::parseCard)
                .reduce((card1, card2) -> card1 + ", " + card2)
                .orElse("");
    }

    default String parseCard(final Card card) {
        Rank number = card.rank();
        Suit suit = card.suit();
        return number.getName() + suit.getName();
    }
}
