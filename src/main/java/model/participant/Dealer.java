package model.participant;

import model.card.CardDeck;
import model.score.MatchType;
import model.score.ResultType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_CONDITION = 17;
    private static final int INITIAL_DEAL_COUNT = 2;

    private final String nickname;
    private final CardDeck deck;
    private final Map<MatchType, Integer> matchResult;

    private Dealer(String nickname, CardDeck deck) {
        this.nickname = nickname;
        this.deck = deck;
        this.matchResult = new HashMap<>();
    }

    public static Dealer from(CardDeck deck) {
        return new Dealer(DEALER_NAME,deck);
    }


    public void divideInitialCardToParticipant(Players players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(this);
        participants.addAll(players.getPlayers());
        for (Participant participant : participants) {
            divideCardsByParticipant(participant, INITIAL_DEAL_COUNT);
        }
    }

    public void divideCardsByParticipant(Participant participant, int amount) {
        for (int i = 0; i < amount; i++) {
            divideCardByParticipant(participant);
        }
    }
    public void divideCardByParticipant(Participant participant) {
        participant.addCard(deck.pickCard());
    }

    public ResultType createGameResult(Player player) {
        if (player.isBust()) {
            return ResultType.WIN_LOSE;
        }
        if (isBust()) {
            return ResultType.LOSE_WIN;
        }
        return ResultType.of(cards.calculateScore().compareTo(player.cards.calculateScore()));
    }

    public void calculateVictory(Players players) {
        for (Player player : players.getPlayers()) {
            ResultType resultType = createGameResult(player);
            List<MatchType> matches = resultType.getMatches();
            updateDealerResult(matches);
            updatePlayerResult(player, matches);
        }
    }

    private static void updatePlayerResult(Player player, List<MatchType> matches) {
        player.updateResult(matches.getLast());
    }

    private void updateDealerResult(List<MatchType> matches) {
        updateResult(matches.getFirst());
    }

    public void updateResult(MatchType type) {
        matchResult.computeIfAbsent(type, k -> 0);
        matchResult.put(type, matchResult.get(type) + 1);
    }

    public Map<MatchType, Integer> getMatchResult() {
        return matchResult;
    }

    @Override
    public boolean isHit() {
        return cards.isHit(HIT_CONDITION);
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
