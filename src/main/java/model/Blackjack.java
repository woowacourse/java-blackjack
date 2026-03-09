package model;

import static model.GameRule.DEALOUT_DRAW_COUNT;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import model.card.Card;
import model.card.Cards;
import model.card.Deck;
import model.card.RandomDeck;
import model.card.Rank;
import model.card.Suit;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Participants;
import model.participant.Player;

public class Blackjack {
    private final Participants participants;
    private final Deck deck;

    private Blackjack(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static Blackjack from(Participants participants) {
        return new Blackjack(participants, RandomDeck.of(Suit.values(), Rank.values()));
    }

    public static Blackjack of(Participants participants, Deck deck) {
        return new Blackjack(participants, deck);
    }

    public Map<String, Cards> dealout() {
        Map<String, Cards> dealOutResult = new LinkedHashMap<>();

        for (Participant participant : participants.asList()) {
            for (int i = 0; i < DEALOUT_DRAW_COUNT; i++) {
                Card card = drawNewCard();
                participant.receive(card);
            }

            dealOutResult.put(participant.getName(), participant.open());
        }

        return dealOutResult;
    }

    public void giveCardTo(Participant participant) {
        participant.receive(drawNewCard());
    }

    private Card drawNewCard() {
        return deck.draw();
    }

    public Map<String, Integer> calculateDealerResult() {
        return participants.getDealer()
                .calculateStatistics(participants.getPlayers());
    }

    public Map<String, Boolean> calculatePlayerResult() {
        Dealer dealer = participants.getDealer();

        return participants.getPlayers()
                .stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> player.beats(dealer)
                ));
    }
}
