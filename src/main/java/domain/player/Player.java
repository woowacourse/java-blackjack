package domain.player;

import domain.MatchResult;
import domain.card.PlayingCard;
import domain.card.PlayingCards;
import java.util.ArrayList;
import java.util.List;
import vo.Wallet;

public abstract class Player {
    private final Wallet wallet;
    protected final PlayingCards playingCards;

    protected Player(Wallet wallet, PlayingCards playingCards) {
        this.wallet = wallet;
        this.playingCards = playingCards;
    }

    protected Player(Wallet wallet) {
        this(wallet, new PlayingCards());
    }

    protected Player(String name) {
        this(Wallet.of(name), new PlayingCards());
    }

    protected Player(String name, List<PlayingCard> cards) {
        this(Wallet.of(name), new PlayingCards(cards));
    }

    public Player(Wallet wallet, List<PlayingCard> cards) {
        this(wallet, new PlayingCards(cards));
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

    public double getRevenue(MatchResult matchResult) {
        return matchResult.calculateRevenue(wallet.getMoney(), isBlackJack());
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
