package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Users;
import blackjack.dto.CardAndScoreResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Users users;

    public BlackJackGame(final List<String> playerNames, final DeckGenerator deckGenerator) {
        this.deck = new Deck(deckGenerator);
        this.users = new Users(playerNames, deck);
    }

    public Map<String, List<Card>> getHandholdingCards() {
        return users.getHandholdingCards();
    }

    public Map<String, List<Card>> getInitialHoldingCards() {
        return users.getInitialHoldingStatus();
    }

    public List<String> getPlayerNames() {
        return users.getPlayerNames();
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

    public List<CardAndScoreResult> getCardAndScoreResult() {
        final Map<String, List<Card>> hands = users.getHandholdingCards();
        final List<CardAndScoreResult> results = new ArrayList<>();

        results.add(new CardAndScoreResult(Dealer.DEALER_NAME_CODE,
                hands.get(Dealer.DEALER_NAME_CODE), users.getScore(Dealer.DEALER_NAME_CODE)));
        for (String name : users.getPlayerNames()) {
            results.add(new CardAndScoreResult(name, hands.get(name), users.getScore(name)));
        }
        return results;
    }

    public Map<String, GameResult> getGameResult() {
        return users.getGameResult();
    }

    public boolean isPossibleToDraw(final String name) {
        final Score userScore = users.getScore(name);
        return !userScore.isBust() && !userScore.isMaxNumber();
    }
}
