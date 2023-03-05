package domain;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participants;
import domain.participant.Player;

import java.util.List;

public final class BlackJack {

    public static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    private BlackJack(List<Name> userNames, Deck deck) {
        this.participants = Participants.create(userNames);
        this.deck = deck;
        participants.initGame(deck);
    }

    public static BlackJack getInstance(List<Name> userNames, Deck deck) {
        return new BlackJack(userNames, deck);
    }


}
