package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MAXIMUM_PLAYER_COUNT = 5;

    private final List<Participant> participants;

    public Participants(final Dealer dealer, final List<Player> players) {
        validate(players);

        final List<Participant> recruits = new ArrayList<>();
        recruits.add(dealer);
        recruits.addAll(players);

        this.participants = recruits;
    }

    private void validate(final List<Player> players) {
        validateCount(players);
        validateDuplicate(players);
    }

    private void validateCount(final List<Player> players) {
        if (players.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException("플레이어는 " + MAXIMUM_PLAYER_COUNT + "명을 초과할 수 없습니다");
        }
    }

    private void validateDuplicate(final List<Player> players) {
        final HashSet<Player> uniquePlayers = new HashSet<>(players);

        if (players.size() != uniquePlayers.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void drawCardForPlayer(final PlayerName playerName, final Deck deck) {
        final Player player = getPlayer(playerName);
        player.drawCard(deck.draw());
    }

    public void drawCardForDealer(final Deck deck) {
        final Dealer dealer = getDealer();
        dealer.drawCard(deck.draw());
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public int getDealerScore() {
        return getDealer().getScore();
    }

    public Hand getDealerHand() {
        return getDealer().getHand();
    }

    public int getDealerAdditionalDrawScore() {
        return getDealer().getMaximumDrawableScore();
    }

    public boolean isDealerAdditionalDrawn() {
        return getDealer().isAdditionalDrawn();
    }

    private Dealer getDealer() {
        return participants.stream()
                .filter(Participant::isDealer)
                .map(Dealer.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러는 존재해야 합니다."));
    }

    public List<PlayerName> getPlayerNames() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .map(Player.class::cast)
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public boolean isDrawablePlayer(final PlayerName playerName) {
        return getPlayer(playerName).isDrawable();
    }

    public int getPlayerScore(final PlayerName playerName) {
        return getPlayer(playerName).getScore();
    }

    public Hand getPlayerHand(final PlayerName playerName) {
        return getPlayer(playerName).getHand();
    }

    private Player getPlayer(final PlayerName playerName) {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .map(Player.class::cast)
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."));
    }
}
