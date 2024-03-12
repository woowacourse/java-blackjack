package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.player.bet.BetAmount;
import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.player.bet.BetStatus;
import blackjack.domain.rule.state.Init;
import blackjack.domain.rule.state.State;
import blackjack.exception.NeedRetryException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

public class Players {

    private final Map<PlayerName, State> stateRepository;
    private final Map<PlayerName, BetAmount> betAmountRepository;

    private Players(final Map<PlayerName, State> stateRepository,
            final Map<PlayerName, BetAmount> betAmountRepository) {
        this.stateRepository = stateRepository;
        this.betAmountRepository = betAmountRepository;
    }

    public static Players from(final List<String> inputPlayerNames) {
        final List<PlayerName> playerNames = inputPlayerNames.stream()
                .map(PlayerName::new)
                .toList();

        validateDuplicate(playerNames);

        final Map<PlayerName, State> playersStates = createEmptyRepository(playerNames, name -> new Init());
        final Map<PlayerName, BetAmount> playersBetAmount = createEmptyRepository(playerNames, name -> new BetAmount(0));

        return new Players(playersStates, playersBetAmount);
    }

    private static void validateDuplicate(final List<PlayerName> playerNames) {
        if (Set.copyOf(playerNames).size() != playerNames.size()) {
            throw new NeedRetryException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    private static <T, U> Map<T, U> createEmptyRepository(final List<T> names, final Function<T, U> supplier) {
        return names.stream()
                .collect(toMap(Function.identity(),
                        supplier,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public void saveBetAmountByName(final int betAmount, final String name) {
        final PlayerName playerName = new PlayerName(name);
        validatePlayerName(playerName);

        betAmountRepository.put(playerName, new BetAmount(betAmount));
    }

    private void validatePlayerName(final PlayerName name) {
        if (!stateRepository.containsKey(name) || !betAmountRepository.containsKey(name)) {
            throw new IllegalArgumentException("없는 참가자 입니다.");
        }
    }

    public void divideCard(final List<List<Card>> cardDump) {
        validateCardSize(cardDump);
        final Iterator<List<Card>> cardIterator = cardDump.iterator();

        for (PlayerName name : stateRepository.keySet()) {
            final List<Card> cards = cardIterator.next();
            final State state = stateRepository.get(name);
            final State newState = state.start(cards.get(0), cards.get(1));
            stateRepository.put(name, newState);
        }
    }

    private void validateCardSize(final List<List<Card>> cards) {
        if (cards.size() != stateRepository.values().size()) {
            throw new IllegalArgumentException("카드 묶음 수량이 플레이어 수량과 맞지 않습니다.");
        }
    }

    public void addCardTo(final String name, final Card card) {
        final State state = findState(name);
        state.draw(card);
    }

    private State findState(final String name) {
        final PlayerName playerName = new PlayerName(name);
        validatePlayerName(playerName);

        return stateRepository.get(playerName);
    }

    public Map<PlayerName, BetRevenue> determineBetRevenue(final State dealerState) {
        final Map<PlayerName, BetRevenue> playersWinStatus = new LinkedHashMap<>();

        for (Entry<PlayerName, State> entry : stateRepository.entrySet()) {
            final PlayerName name = entry.getKey();
            final BetStatus betStatus = BetStatus.of(dealerState, entry.getValue());
            final BetAmount betAmount = betAmountRepository.get(name);

            playersWinStatus.put(name, betStatus.applyLeverage(betAmount));

        }
        return Collections.unmodifiableMap(playersWinStatus);
    }

    public boolean hasName(final String other) {
        return stateRepository.keySet().stream()
                .anyMatch(name -> name.equals(new PlayerName(other)));
    }

    public boolean isNotDead(final String name) {
        final State state = findState(name);
        return !state.getHands().calculateScore().isDead();
    }

    public boolean isAllDead() {
        return stateRepository.values().stream()
                .allMatch(state -> state.getHands().calculateScore().isDead());
    }

    public int count() {
        return stateRepository.size();
    }

    public Hands getHandsOf(final String name) {
        return findState(name).getHands();
    }

    public List<PlayerName> getNames() {
        return stateRepository.keySet().stream().toList();
    }

    public Map<PlayerName, Hands> getPlayersHands() {
        return stateRepository.entrySet().stream()
                .collect(toMap(Entry::getKey,
                        entry -> entry.getValue().getHands(),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }
}
