package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ProfitCalculator;
import blackjack.domain.result.Score;
import blackjack.domain.user.Users;
import blackjack.dto.domain.CardAndScore;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Users users;
    private final ProfitCalculator profitCalculator;

    public BlackJackGame(final List<String> playerNames, final DeckGenerator deckGenerator) {
        this.deck = new Deck(deckGenerator);
        this.users = new Users(playerNames, deck);
        profitCalculator = new ProfitCalculator();
    }

    public List<String> getPlayerNames() {
        return users.getPlayerNames();
    }

    public void bet(final String name, final int amount) {
        profitCalculator.bet(name, amount);
    }

    public List<Card> getInitialHoldingCards(final String name) {
        return users.getInitialHoldingCards(name);
    }

    public List<Card> getHandholdingCards(String name) {
        return users.getHandholdingCards(name);
    }

    public int playDealerTurn() {
        int drawCount = 0;
        while (!users.isDealerOverDrawLimit()) {
            users.drawDealer(deck);
            drawCount++;
        }
        return drawCount;
    }

    public void playPlayer(final String userName) {
        users.drawCard(userName, deck);
    }

    public CardAndScore getCardAndScore(final String name) {
        return new CardAndScore(users.getHandholdingCards(name), users.getScore(name));
    }

    public boolean isPossibleToDraw(final String name) {
        final Score userScore = users.getScore(name);
        return !userScore.isBust() && !userScore.isMaxNumber();
    }

    public void judgeResults() {
        final Map<String, GameResult> playerResults = users.getGameResult();
        playerResults.entrySet().stream()
                .forEach(resultEntry ->
                        profitCalculator.putGameResult(resultEntry.getKey(), resultEntry.getValue())
                );
    }

    public int getDealerProfitAmount() {
        return profitCalculator.calculateDealerProfit();
    }

    public int getPlayerProfitAmount(final String name) {
        return profitCalculator.calculateProfit(name);
    }
}
