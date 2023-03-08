package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Users;
import blackjack.dto.CardAndScoreResult;
import blackjack.dto.HoldingCards;

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

    public HoldingCards getHandholdingCards(String name) {
        final List<Card> hands = users.getHandholdingCards(name);
        return new HoldingCards(name, hands);
    }

    public List<HoldingCards> getInitialHoldingCards() {
        final List<HoldingCards> initialHoldingCards = new ArrayList<>();

        initialHoldingCards.add(new HoldingCards(Dealer.DEALER_NAME_CODE, users.getInitialHoldingCards(Dealer.DEALER_NAME_CODE)));
        for (String name : users.getPlayerNames()) {
            initialHoldingCards.add(new HoldingCards(name, users.getInitialHoldingCards(name)));
        }
        return initialHoldingCards;
    }

//    public Map<String, List<Card>> getInitialHoldingCards() {
//        return users.getInitialHoldingStatus();
//    }

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
        final List<CardAndScoreResult> results = new ArrayList<>();

        results.add(new CardAndScoreResult(Dealer.DEALER_NAME_CODE,
                users.getHandholdingCards(Dealer.DEALER_NAME_CODE), users.getScore(Dealer.DEALER_NAME_CODE)));
        for (String name : users.getPlayerNames()) {
            results.add(new CardAndScoreResult(name, users.getHandholdingCards(name), users.getScore(name)));
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
