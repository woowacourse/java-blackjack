package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Player {
    private final PlayerMeta playerMeta;
    private final PlayerCards playerCards;

    public Player(PlayerMeta playerMeta) {
        this.playerMeta = playerMeta;
        this.playerCards = PlayerCards.empty();
    }

    public static Player from(String name, int betAmount) {
        return new Player(new PlayerMeta(name, betAmount));
    }

    public void draw(Deck deck) {
        Card card = deck.draw();
        playerCards.append(card);
    }

    public boolean isBusted() {
        return playerCards.isBusted();
    }

    public boolean shouldDealerDrawMore() {
        Score dealerScore = playerCards.calculateScore();
        return dealerScore.isLessThanDealerMinimumScore();
    }

    public List<Card> getCards() {
        return playerCards.getCards();
    }

    public Score getScore() {
        return playerCards.calculateScore();
    }

    public int getScoreValue() {
        return getScore().getValue();
    }

    public String getName() {
        return playerMeta.name();
    }

    public int getBetAmount() {
        return playerMeta.betAmount();
    }

    public int getTotalCardsCount() {
        return playerCards.size();
    }
}
