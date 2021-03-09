package blackjack.service;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackService {

    private Deck deck = new Deck();
    private Challengers challengers;
    private Dealer dealer;

    public void initDealer() {
        this.dealer = new Dealer(getInitCards());
    }

    public void initChallengers(final List<String> requestNames) {
        List<Challenger> challengers = new ArrayList<>();
        for (String name : requestNames) {
            challengers.add(new Challenger(getInitCards(), new Name(name)));
        }
        this.challengers = new Challengers(challengers);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(challengers.getChallengersAsList());
        return participants;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Challengers getChallengers() {
        return this.challengers;
    }

    public void receiveMoreCard(final Participant participant) {
        participant.receiveMoreCard(deck.draw());
    }

    public boolean isDealerBlackJack() {
        return this.dealer.isBlackJack();
    }

    private Cards getInitCards() {
        List<Card> cards = Collections.singletonList(deck.draw());
        return new Cards(cards);
    }
}
