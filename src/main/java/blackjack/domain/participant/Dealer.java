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

    public static final int BLACKJACK_SCORE = 21;
    private static final int SPECIFIC_SCORE_OF_DEALER = 16;

    private final Players players;
    private final Deck deck;

    public Dealer(final Players players) {
        super(Cards.generateEmptyCards());
        this.players = players;
        this.deck = new Deck();
    }

    public void distributeTwoCards() {
        List<Card> cardList = new ArrayList<>();

        while (cardList.size() < players.size() * 2) {
            Card drawnCard = deck.drawCard();
            cardList.add(drawnCard);
        }
        players.receiveSettingCards(cardList);
    }

    public void drawSelfInitCards() {
        for (int count = 0; count < 2; count++) {
            Card drawnCard = deck.drawCard();
            receiveCard(drawnCard);
        }
    }

    public void giveOneMoreCard(Player player) {
        player.receiveCard(deck.drawCard());
    }

    public Map<Player, Result> decideResult() {
        return players.decideResults(cards.calculateTotalScore());
    }

    public List<Integer> decideSelfResult() {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>() {{
            put(WIN, 0);
            put(DRAW, 0);
            put(LOSE, 0);
        }};
        Map<Player, Result> playerResultMap = decideResult();

        for (Result playerResult : playerResultMap.values()) {
            decideResult(playerResult, dealerResult);
        }

        return new ArrayList<>(dealerResult.values());
    }

    private void decideResult(Result playerResult, Map<Result, Integer> dealerResult) {
        if (playerResult == WIN) {
            dealerResult.put(LOSE, dealerResult.get(LOSE) + 1);
            return;
        }
        if (playerResult == DRAW) {
            dealerResult.put(DRAW, dealerResult.get(DRAW) + 1);
            return;
        }
        dealerResult.put(WIN, dealerResult.get(WIN) + 1);
    }

    public boolean canDraw() {
        return cards.calculateTotalScore() <= SPECIFIC_SCORE_OF_DEALER;
    }

    public void drawOneMoreCard() {
        receiveCard(deck.drawCard());
    }

    public Players getPlayers() {
        return players;
    }
}
