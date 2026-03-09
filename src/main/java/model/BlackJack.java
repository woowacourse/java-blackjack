package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.card.Card;
import model.card.Deck;
import model.card.RandomDeck;
import model.card.Rank;
import model.card.Suit;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;

public class BlackJack {
    private final Participants participants;
    private final Deck deck;

    private BlackJack(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJack from(Participants participants) {
        return new BlackJack(participants, RandomDeck.of(Suit.values(), Rank.values()));
    }

    public static BlackJack of(Participants participants, Deck deck) {
        return new BlackJack(participants, deck);
    }

    public Map<String, List<String>> dealOut() {
        Map<String, List<String>> dealOutResult = new LinkedHashMap<>();
        for (Participant participant : participants) {
            for (int i = 0; i < 2; i++) {
                Card card = drawNewCard();
                participant.receive(card);
            }

            dealOutResult.put(participant.getName(), participant.open());
        }

        return dealOutResult;
    }

    public void startDealerTurn(Consumer<Boolean> afterDealerTurn) {
        Dealer dealer = participants.getDealer();

        boolean draw = dealer.needDraw();
        if (draw) {
            giveCardTo(dealer);
        }

        afterDealerTurn.accept(draw);
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
