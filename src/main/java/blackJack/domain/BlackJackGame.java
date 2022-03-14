package blackJack.domain;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.MatchResult;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(Participants participants) {
        this.deck = new Deck();
        this.participants = participants;
    }

    public void firstCardDispensing() {
        distributeCard(getDealer(), INITIAL_CARD_COUNT);
        getPlayers().forEach(player -> distributeCard(player, INITIAL_CARD_COUNT));
    }

    public void distributeCard(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.hit(deck.getCard());
        }
    }

    public Map<MatchResult, Integer> getDealerGameResult() {
        final Map<MatchResult, Integer> gameResult = initDealerGameResult();

        for (Player player : getPlayers()) {
            final MatchResult matchResult = getDealer().getMatchResult(player);
            gameResult.computeIfPresent(matchResult, (k, v) -> v + 1);
        }

        return gameResult;
    }

    private Map<MatchResult, Integer> initDealerGameResult() {
        final Map<MatchResult, Integer> dealerGameResult = new EnumMap<>(MatchResult.class);

        for (MatchResult value : MatchResult.values()) {
            dealerGameResult.put(value, 0);
        }

        return dealerGameResult;
    }

    public Map<Player, MatchResult> getPlayersGameResult() {
        final Map<Player, MatchResult> gameResult = new LinkedHashMap<>();

        for (Player player : getPlayers()) {
            final MatchResult matchResult = player.getMatchResult(getDealer());
            gameResult.put(player, matchResult);
        }

        return gameResult;
    }

    public Participants getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
