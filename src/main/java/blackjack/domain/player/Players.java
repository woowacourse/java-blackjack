package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.player.bet.BetAmount;
import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.rule.BlackjackStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.player.bet.BetStatus;
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

    private final Map<PlayerName, Hands> handsRepository;
    private final Map<PlayerName, BetAmount> betAmountRepository;

    private Players(final Map<PlayerName, Hands> handsRepository,
            final Map<PlayerName, BetAmount> betAmountRepository) {
        this.handsRepository = handsRepository;
        this.betAmountRepository = betAmountRepository;
    }

    public static Players from(final List<String> inputPlayerNames) {
        final List<PlayerName> playerNames = inputPlayerNames.stream()
                .map(PlayerName::new)
                .toList();

        validateDuplicate(playerNames);

        final Map<PlayerName, Hands> playersHands = createEmptyRepository(playerNames, name -> new Hands());
        final Map<PlayerName, BetAmount> playersBetAmount = createEmptyRepository(playerNames, name -> new BetAmount(0));

        return new Players(playersHands, playersBetAmount);
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
        if (!handsRepository.containsKey(name) || !betAmountRepository.containsKey(name)) {
            throw new IllegalArgumentException("없는 참가자 입니다.");
        }
    }

    public void divideCard(final List<List<Card>> cards) {
        validateCardSize(cards);
        final Iterator<List<Card>> cardIterator = cards.iterator();

        for (final Hands hands : handsRepository.values()) {
            cardIterator.next().forEach(hands::addCard);
        }
    }

    private void validateCardSize(final List<List<Card>> cards) {
        if (cards.size() != handsRepository.values().size()) {
            throw new IllegalArgumentException("카드 묶음 수량이 플레이어 수량과 맞지 않습니다.");
        }
    }

    public void addCardTo(final String name, final Card card) {
        final Hands hands = findHands(name);
        hands.addCard(card);
    }

    private Hands findHands(final String name) {
        final PlayerName playerName = new PlayerName(name);
        validatePlayerName(playerName);

        return handsRepository.get(playerName);
    }

    public Map<PlayerName, BetRevenue> determineBetRevenue(final Hands dealerHands) {
        final Map<PlayerName, BetRevenue> playersWinStatus = new LinkedHashMap<>();

        for (Entry<PlayerName, Hands> entry : handsRepository.entrySet()) {
            final PlayerName name = entry.getKey();
            final BetStatus betStatus = BetStatus.of(dealerHands, entry.getValue());
            final BetAmount betAmount = betAmountRepository.get(name);

            playersWinStatus.put(name, betStatus.applyLeverage(betAmount));

        }
        return Collections.unmodifiableMap(playersWinStatus);
    }

    public boolean hasName(final String other) {
        return handsRepository.keySet().stream()
                .anyMatch(name -> name.equals(new PlayerName(other)));
    }

    public boolean isNotDead(final String name) {
        final Hands hands = findHands(name);
        return BlackjackStatus.from(hands.calculateScore()) != BlackjackStatus.DEAD;
    }

    public boolean isAllDead() {
        return handsRepository.values().stream()
                .noneMatch(hands -> BlackjackStatus.from(hands.calculateScore()) != BlackjackStatus.DEAD);
    }

    public int count() {
        return handsRepository.size();
    }

    public Hands getHandsOf(final String name) {
        return new Hands(findHands(name).getCards());
    }

    public List<PlayerName> getNames() {
        return handsRepository.keySet().stream().toList();
    }

    public Map<PlayerName, Hands> getPlayersHands() {
        return Collections.unmodifiableMap(handsRepository);
    }
}
