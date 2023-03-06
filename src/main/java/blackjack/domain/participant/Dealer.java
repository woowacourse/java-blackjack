package blackjack.domain.participant;

import static blackjack.domain.Result.DRAW;
import static blackjack.domain.Result.LOSE;
import static blackjack.domain.Result.WIN;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    public static final int INIT_CARD_COUNT = 2;
    public static final int BLACKJACK_SCORE = 21;
    private static final int DEALER_CAN_DRAW_SCORE = 16;

    private final Players players;
    private final Deck deck;

    public Dealer(final Players players) {
        super(Cards.generateEmptyCards());
        this.players = players;
        this.deck = new Deck();
    }

    public void settingCards() {
        List<Card> cards = new ArrayList<>();
        int targetSize = players.size() * INIT_CARD_COUNT;

        while (cards.size() < targetSize) {
            cards.add(deck.drawCard());
        }

        players.receiveSettingCards(cards);
    }

    public void settingSelfCards() {
        for (int count = 0; count < INIT_CARD_COUNT; count++) {
            receiveCard(deck.drawCard());
        }
    }

    public void giveOneMoreCard(Player player) {
        player.receiveCard(deck.drawCard());
    }

    public void drawOneMoreCard() {
        receiveCard(deck.drawCard());
    }

    public Map<Player, Result> makePlayerResults() {
        return players.makeResult(this.cards.calculateTotalScore());
    }

    public List<Integer> countSelfResults(Map<Player, Result> playerResults) {
        Map<Result, Integer> dealerResults = new LinkedHashMap<>();

        for (Result playerResult : playerResults.values()) {
            compareToPlayerResult(dealerResults, playerResult);
        }

        return new ArrayList<>(dealerResults.values());
    }

    private void compareToPlayerResult(Map<Result, Integer> dealerResult, Result playerResult) {
        if (playerResult == WIN) {
            dealerResult.put(LOSE, dealerResult.getOrDefault(LOSE, 0) + 1);
            return;
        }
        if (playerResult == DRAW) {
            dealerResult.put(DRAW, dealerResult.getOrDefault(DRAW, 0) + 1);
            return;
        }
        dealerResult.put(WIN, dealerResult.getOrDefault(WIN, 0) + 1);
    }

    public boolean canDraw() {
        return cards.calculateTotalScore() <= DEALER_CAN_DRAW_SCORE;
    }

    public Players getPlayers() {
        return players;
    }
}
