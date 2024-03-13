package domain.blackjack;

import domain.card.Card;
import domain.card.CardSelectStrategy;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Gamer {

    static Dealer of(HoldingCards holdingCards) {
        return new Dealer(new BlackJackGameMachine(holdingCards));
    }

    Dealer(BlackJackGameMachine blackJackGameMachine) {
        super(blackJackGameMachine);
    }

    @Override
    DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy) {
        return blackJackGameMachine.draw(deck, cardSelectStrategy, new DealerCardDrawCondition(blackJackGameMachine));
    }

    public List<Card> getRawHoldingCardsWithoutFirstCard() {
        List<Card> rawHoldingCards = new ArrayList<>(blackJackGameMachine.getRawHoldingCards());
        rawHoldingCards.remove(0);
        return List.copyOf(rawHoldingCards);
    }

    public Map<GameResult, Integer> calculateGameResultWithPlayers(Players players) {
        List<GameResult> gameResults = players.calculateGameResultsWith(this).stream()
                .map(GameResult::changeBase)
                .toList();
        return gameResults.stream()
                .collect(Collectors.groupingBy(gameResult -> gameResult, Collectors.summingInt(value -> 1)));
    }
}
