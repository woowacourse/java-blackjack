package domain.participant;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;
import domain.game.GameResult;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class Dealer extends GameParticipant {
    private final Deck deck;

    public Dealer(Deck deck, CardHand cardHand) {
        super("딜러", cardHand);
        this.deck = deck;
    }

    public Card pickCard() {
        return deck.drawCard();
    }

    public CardHand pickInitialDeal() {
        return deck.getInitialDeal();
    }

    public EnumMap<GameResult, Integer> calculateGameResult(List<Player> players) {
        EnumMap<GameResult, Integer> gameResults = new EnumMap<>(GameResult.class);
        initGameResults(gameResults);
        for (Player player : players) {
            GameResult gameResult = GameResult.calculateDealerGameResult(this, player);
            gameResults.put(gameResult, gameResults.get(gameResult) + 1);
        }
        return gameResults;
    }

    public boolean doesNeedCard() {
        return cardHand.doesDealerNeedCard();
    }

    private void initGameResults(EnumMap<GameResult, Integer> gameResults) {
        Arrays.stream(GameResult.values())
                .forEach(result -> gameResults.put(result, 0));
    }
}
