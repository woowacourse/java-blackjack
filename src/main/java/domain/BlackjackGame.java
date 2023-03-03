package domain;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class BlackjackGame {

    private Participants participants;
    private Deck deck;

    public void initialize(List<String> playerNames, ShuffleStrategy shuffleStrategy) {
        participants = Participants.create(playerNames);
        deck = Deck.create();

        deck.shuffle(shuffleStrategy);
        participants.readyForGame(deck);
    }

    public boolean hasDrawablePlayer() {
        return participants.hasDrawablePlayer();
    }

    public String nextDrawablePlayer() {
        return participants.nextDrawablePlayerName();
    }

    public void handOutCardToNextPlayer() {
        participants.handOutCardToPlayer(deck.draw());
    }

    public void standCurrentPlayer() {
        participants.standCurrentPlayer();
    }

    public boolean isDealerDrawable() {
        return participants.isDealerDrawable();
    }

    public void handOutCardToDealer() {
        participants.handOutCardToDealer(deck.draw());
    }

//    public Map<String, List<Card>> getParticipantsCards() {
//    }

    public Map<String, GameOutcome> getPlayersOutcome() {
        int dealerScore = participants.getDealerScore();
        return participants.getPlayerScores()
                .entrySet()
                .stream()
                .collect(toMap(Entry::getKey, e -> GameOutcome.of(e.getValue(), dealerScore)
                        , (d1, d2) -> d1, LinkedHashMap::new));
    }

    public List<Card> getParticipantCards(String currentPlayerName) {
        return participants.getParticipantCards(currentPlayerName);
    }

    public Map<String, List<Card>> getParticipantsCards() {
        return participants.names()
                .stream()
                .collect(toMap(Function.identity(), this::getParticipantCards
                        , (d1, d2) -> d1, LinkedHashMap::new));
    }

    public List<Integer> scores() {
        List<Integer> scores = participants.scores();
        scores.add(0, participants.getDealerScore());
        return scores;
    }
}
