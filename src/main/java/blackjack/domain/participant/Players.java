package blackjack.domain.participant;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.betting.BetAmount;
import blackjack.domain.betting.BetRevenue;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.betting.BetStatus;
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

    private final Map<ParticipantName, Hands> handsRepository;
    private final Map<ParticipantName, BetAmount> betAmountRepository;

    private Players(final Map<ParticipantName, Hands> handsRepository,
            final Map<ParticipantName, BetAmount> betAmountRepository) {
        this.handsRepository = handsRepository;
        this.betAmountRepository = betAmountRepository;
    }

    public static Players from(final List<String> inputPlayerNames) {
        final List<ParticipantName> playerNames = inputPlayerNames.stream()
                .map(ParticipantName::new)
                .toList();

        validateDuplicate(playerNames);

        final Map<ParticipantName, Hands> playersHands = createEmptyRepository(playerNames, name -> new Hands());
        final Map<ParticipantName, BetAmount> playersBetAmount = createEmptyRepository(playerNames, name -> new BetAmount(0));

        return new Players(playersHands, playersBetAmount);
    }

    private static void validateDuplicate(final List<ParticipantName> playerNames) {
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
        final ParticipantName playerName = new ParticipantName(name);
        validatePlayerName(playerName);

        betAmountRepository.put(playerName, new BetAmount(betAmount));
    }

    private void validatePlayerName(final ParticipantName name) {
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
        final ParticipantName playerName = new ParticipantName(name);
        validatePlayerName(playerName);

        return handsRepository.get(playerName);
    }

    public Map<ParticipantName, BetRevenue> determineBetRevenue(final Hands dealerHands) {
        final Map<ParticipantName, BetRevenue> playersWinStatus = new LinkedHashMap<>();

        for (Entry<ParticipantName, Hands> entry : handsRepository.entrySet()) {
            final ParticipantName name = entry.getKey();
            final BetStatus betStatus = BetStatus.of(dealerHands, entry.getValue());
            final BetAmount betAmount = betAmountRepository.get(name);

            playersWinStatus.put(name, betStatus.applyLeverage(betAmount));

        }
        return playersWinStatus;
    }

    public boolean hasName(final ParticipantName other) {
        return handsRepository.keySet().stream()
                .anyMatch(name -> name.equals(other));
    }

    public boolean isNotDead(final String name) {
        final Hands hands = findHands(name);
        return hands.isNotDead();
    }

    public boolean isAllDead() {
        return handsRepository.values().stream()
                .noneMatch(Hands::isNotDead);
    }

    public int count() {
        return handsRepository.size();
    }

    public Hands getHandsOf(final String name) {
        return new Hands(findHands(name).getCards());
    }

    public List<ParticipantName> getNames() {
        return handsRepository.keySet().stream().toList();
    }

    public Map<ParticipantName, Hands> getPlayersHands() {
        return Collections.unmodifiableMap(handsRepository);
    }
}
