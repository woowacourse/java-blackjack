package view;

import domain.BlackjackResultStatus;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.Cards;
import domain.participant.Name;
import domain.participant.player.Players;

public interface BlackjackViewParser {

    default String parsePlayerNames(final Players players) {
        return players.stream()
                .map(player -> parseName(player.name()))
                .reduce((player1, player2) -> player1 + ", " + player2)
                .orElse("");
    }

    default String parseName(final Name name) {
        return name.value();
    }

    default String parseCards(final Cards cards) {
        return cards.stream()
                .map(this::parseCard)
                .reduce((card1, card2) -> card1 + ", " + card2)
                .orElse("");
    }

    default String parseCard(final Card card) {
        CardRank number = card.rank();
        CardSuit suit = card.suit();
        return number.getName() + suit.getName();
    }

    default String parseResultStatus(final BlackjackResultStatus resultStatus) {
        return resultStatus.getValue();
    }
}
