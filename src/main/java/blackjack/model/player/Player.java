package blackjack.model.player;

import blackjack.model.betting.Betting;
import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.dealer.Dealer;
import java.util.List;

public class Player {
    private static final int MAX_DRAWABLE_SCORE = 21;

    private final PlayerName name;
    private final Cards cards;

    public Player(final PlayerName name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void dealCards(final CardGenerator cardGenerator) {
        List<Card> receivedCards = List.of(cardGenerator.pick(), cardGenerator.pick());
        cards.addCards(receivedCards);
    }

    public void drawCard(final CardGenerator cardGenerator) {
        cards.addCard(cardGenerator.pick());
    }

    public boolean canDrawCard() {
        return cards.canAddCardWithinScoreLimit(MAX_DRAWABLE_SCORE);
    }

    public int calculateCardsTotalScore() {
        return cards.calculateScore();
    }

    public int calculateBettingProfit(final Betting betting, final Dealer dealer) {
        MatchResult matchResult = MatchResult.determine(dealer, this);
        return betting.calculatePlayerBettingProfit(name, matchResult);
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public PlayerName getName() {
        return name;
    }
}
