package blackjack.domain.player;

import blackjack.domain.BetMoney;
import blackjack.domain.GameResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import java.util.List;

public abstract class Player {

    protected String name;
    protected final PlayingCards playingCards;

    protected Player(final String name) {
        this.name = name;
        this.playingCards = new PlayingCards();
    }

    public void receiveCard(final CardDeck cardDeck) {
        final PlayingCard poppedCard = cardDeck.pop();
        playingCards.addCard(poppedCard);
    }

    public boolean isBlackjack() {
        return playingCards.isBlackjack();
    }

    public boolean isBurst() {
        return playingCards.isBurst();
    }

    public abstract boolean isNotFinished();

    public abstract boolean isDealer();

    public GameResult compare(final Player player) {
        return GameResult.of(this, player);
    }

    public int getSumOfCards() {
        return playingCards.getCardSum();
    }

    public String getName() {
        return this.name;
    }

    public List<PlayingCard> getCards() {
        return playingCards.getPlayingCards();
    }

    public boolean isWin(final Player otherPlayer) {
        return this.getSumOfCards() > otherPlayer.getSumOfCards();
    }

    public boolean isDraw(final Player otherPlayer) {
        return this.getSumOfCards() == otherPlayer.getSumOfCards();
    }

    public boolean isLose(final Player otherPlayer) {
        return this.getSumOfCards() < otherPlayer.getSumOfCards();
    }

    public abstract BetMoney getBetMoney();

}
