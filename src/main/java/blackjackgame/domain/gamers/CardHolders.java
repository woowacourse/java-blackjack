package blackjackgame.domain.gamers;

import blackjackgame.domain.blackjack.PlayerRandomCardDrawStrategy;
import blackjackgame.domain.card.Deck;
import java.util.List;

public class CardHolders {
    private final List<CardHolder> cardHolders;

    public CardHolders(List<CardHolder> players) {
        this.cardHolders = players;
    }

    public void drawNTimes(Deck deck, int execution_count) {
        cardHolders.forEach(cardHolder ->
                cardHolder.draw(deck, new PlayerRandomCardDrawStrategy(cardHolder), execution_count));
    }

    public List<String> getRawPlayerNames() {
        return cardHolders.stream()
                .map(CardHolder::getRawName)
                .toList();
    }

    public List<CardHolder> getCardHolders() {
        return cardHolders;
    }
}
