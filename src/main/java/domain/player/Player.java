package domain.player;

import domain.MatchResult;
import domain.card.PlayingCard;
import domain.card.PlayingCards;
import java.util.ArrayList;
import java.util.List;
import vo.Wallet;

public abstract class Player {
    private static final int EMPTY_MONEY = 0;

    private final Wallet wallet;
    protected final PlayingCards playingCards;

    protected Player(Wallet wallet) {
        this.wallet = wallet;
        this.playingCards = new PlayingCards();
    }

    protected Player(String name) {
        this(Wallet.of(name, EMPTY_MONEY));
    }

    public abstract boolean isHittable();

    public abstract List<PlayingCard> getOpenCards();

    public abstract MatchResult match(Player another);

    public abstract boolean isDealer();

    public void addCard(PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    public int getScore() {
        return playingCards.getScore();
    }

    public boolean isBust() {
        return playingCards.isBust();
    }

    public boolean isBlackJack() {
        return playingCards.isBlackJack();
    }

    protected MatchResult getMatchResultAfterBustCheck(Player another) {
        if (hasHigherScoreIgnoreBust(another) || winByBlackJack(another)) {
            return MatchResult.WIN;
        }

        if (another.hasHigherScoreIgnoreBust(this) || another.winByBlackJack(this)) {
            return MatchResult.LOSE;
        }

        return MatchResult.DRAW;
    }

    private boolean hasHigherScoreIgnoreBust(Player another) {
        return this.getScore() > another.getScore();
    }

    private boolean winByBlackJack(Player another) {
        return this.isBlackJack() && !another.isBlackJack();
    }

    public String getName() {
        return wallet.getName();
    }

    public List<PlayingCard> getHoldingCards() {
        return new ArrayList<>(playingCards.getCards());
    }

    @Override
    public String toString() {
        return "Player{" +
                "wallet=" + wallet +
                ", playingCards=" + playingCards +
                '}';
    }
}
