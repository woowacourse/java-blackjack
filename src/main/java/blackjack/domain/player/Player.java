package blackjack.domain.player;

import blackjack.domain.GameScore;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Player {

    private final PlayerName playerName;
    private final List<Card> cards;
    private static final int MAX_HAND_VALUE = 21;
    private static final int ACE_ADDITIONAL_VALUE = 10;

    public Player(final String name) {
        this.playerName = new PlayerName(name);
        cards = new ArrayList<>();
    }

    public Player(PlayerName playerName) {
        this.playerName = playerName;
        cards = new ArrayList<>();
    }

    public void pushDealCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public GameScore getGameScore() {
        GameScore gameScore = new GameScore(cards.stream()
                .mapToInt(Card::getValue)
                .sum(), cards.size());
        if (hasAce()) {
            gameScore = gameScore.addAce();
        }
        return gameScore;
    }

    public boolean isPlayerBust() {
        return getGameScore().isBust();
    }

    public boolean isPlayerNotBust() {
        return !isPlayerBust();
    }

    public String getName() {
        return playerName.name();
    }

    abstract public List<Card> getOpenedCards();

    public List<Card> getCards() {
        return cards;
    }

    private boolean canCalculateAceWithEleven(int sum) {
        return hasAce() && sum + ACE_ADDITIONAL_VALUE <= MAX_HAND_VALUE;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
