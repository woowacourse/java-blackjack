package blackjack.blackjack.participant;

import blackjack.blackjack.card.Deck;
import blackjack.blackjack.card.Hand;
import blackjack.util.ExceptionMessage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    public static Players from(final List<String> names, final List<BigDecimal> bettingAmounts, final Deck deck,
                               final int count) {
        return new Players(IntStream.range(0, names.size())
                .mapToObj(
                        index -> new Player(deck.drawCardsByCount(count), names.get(index), bettingAmounts.get(index)))
                .toList());
    }

    public void receiveCardsByCount(final Hand hand, final int count) {
        for (int i = 0; i < players.size(); i++) {
            final Player player = players.get(i);
            player.receiveCards(hand.getPartialHand(i * count, (i + 1) * count));
        }
    }

    public Map<String, Hand> showTotalInitialCards() {
        return players.stream()
                .collect(Collectors.toMap(Participant::getNickname,
                        Participant::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Map<String, Hand> showTotalCards() {
        return players.stream()
                .collect(Collectors.toMap(Participant::getNickname, Participant::showInitialCards, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Players findHitAvailablePlayers() {
        return new Players(players.stream()
                .filter(Participant::canHit)
                .toList());
    }

    public void dealInitialCards(final Deck deck, final int count) {
        Hand playerHand = deck.drawCardsByCount(count * players.size());
        receiveCardsByCount(playerHand, count);
    }

    private void validate(List<Player> players) {
        if (hasDuplicateName(players)) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("[ERROR] 중복된 이름을 입력했습니다."));
        }
    }

    private boolean hasDuplicateName(final List<Player> players) {
        return players.size() != players.stream()
                .map(Player::getNickname)
                .distinct()
                .count();
    }

    public List<String> getNames() {
        return players.stream()
                .map(Participant::getNickname)
                .toList();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
