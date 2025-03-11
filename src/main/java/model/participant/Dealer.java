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

    private Dealer(String nickname, CardDeck deck) {
        this.nickname = nickname;
        this.deck = deck;
    }

    public static Dealer from(CardDeck deck) {
        return new Dealer(DEALER_NAME, deck);
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

    public Map<MatchType, Integer> calculateVictory(Players players) {
        Map<MatchType, Integer> matchResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            ResultType resultType = createGameResult(player);
            List<MatchType> matches = resultType.getMatches();
            MatchType dealerMatchType = matches.getFirst();
            MatchType playerMatchType = matches.getLast();
            updateDealerMatchResult(matchResult, dealerMatchType);
            player.updateResult(playerMatchType);
        }
        return matchResult;
    }

    private static void updateDealerMatchResult(Map<MatchType, Integer> matchResult, MatchType dealerMatchType) {
        matchResult.computeIfAbsent(dealerMatchType, k -> 0);
        matchResult.put(dealerMatchType, matchResult.get(dealerMatchType) + 1);
    }

    private ResultType createGameResult(Player player) {
        if (player.isBust()) {
            return ResultType.WIN_LOSE;
        }
        if (isBust()) {
            return ResultType.LOSE_WIN;
        }
        return ResultType.of(cards.calculateScore().compareTo(player.cards.calculateScore()));
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
