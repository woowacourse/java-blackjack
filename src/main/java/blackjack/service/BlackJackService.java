package blackjack.service;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJackService {

    private Deck deck = new Deck();
    private Players players;
    private Dealer dealer;

    public void initDealer() {
        this.dealer = new Dealer(getInitCards());
    }

    public void initPlayers(final List<String> requestNames) {
        List<Player> players = new ArrayList<>();
        for (String name : requestNames) {
            players.add(new Player(getInitCards(), name));
        }
        this.players = new Players(players);
    }

    public List<Participant> getParticipantsAsList() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayersAsList());
        return participants;
    }

    public List<Player> getPlayersAsList() {
        return this.players.getPlayersAsList();
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }

    public void receiveMoreCard(final Participant participant) {
        participant.receiveMoreCard(deck.draw());
    }

    public boolean isDealerBlackJack() {
        return this.dealer.isBlackJack();
    }

    private Cards getInitCards() {
        List<Card> cards = Arrays.asList(deck.draw(), deck.draw());
        return new Cards(cards);
    }
}
