package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.deck.Deck;
import model.deck.DeckImpl;
import model.participant.Participant;

public class BlackJack {
    private final Participants participants;
    private final Deck deck;
    private final List<Card> pickedCards = new ArrayList<>();

    private BlackJack(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJack from(Participants participants) {
        return new BlackJack(participants, DeckImpl.of(Suit.VALUES, Rank.VALUES));
    }

    public static BlackJack of(Participants participants, Deck deck) {
        return new BlackJack(participants, deck);
    }

    public Map<String, List<String>> dealOut() {
        Map<String, List<String>> dealOutResult = new LinkedHashMap<>();
        for (Participant participant : participants) {
            for (int i = 0; i < 2; i++) {
                Card card = deck.draw();
                participant.draw(card);
            }

            dealOutResult.put(participant.getName(), participant.open());
        }

        return dealOutResult;
    }

    public void hit(Participant participant) {
        participant.draw(deck.draw());
    }

    public Map<String, Integer> calculateDealerResult() {
        Map<String, Integer> resultMap = new HashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore() || player.isBust()) {
                resultMap.merge("승", 1, Integer::sum);
                continue;
            }

            resultMap.merge("패", 1, Integer::sum);
        }

        return resultMap;
    }

    public Map<String, Boolean> calculatePlayerResult() {
        Map<String, Boolean> resultMap = new HashMap<>();

        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore() || player.isBust()) {
                resultMap.put(player.getName(), Boolean.FALSE);
                continue;
            }

            resultMap.put(player.getName(), Boolean.TRUE);
        }

        return resultMap;
    }
}
