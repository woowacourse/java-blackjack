package blackjack.domain.participant;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static java.util.stream.Collectors.toList;

import blackjack.domain.result.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Dealer extends Participant {

    public static final int INIT_CARD_COUNT = 2;
    public static final int BLACKJACK_SCORE = 21;
    public static final int DEALER_CAN_DRAW_SCORE = 16;

    private final Players players;
    private final Deck deck;

    public Dealer(final Players players) {
        super(new Cards());
        this.players = players;
        this.deck = Deck.getInstance();
    }

    public void settingCards() {
        settingPlayersCards();
        settingSelfCards();
    }

    private void settingPlayersCards() {
        List<Card> initCards = IntStream.range(0, players.size() * INIT_CARD_COUNT)
                .mapToObj(x -> deck.drawACard())
                .collect(toList());

        players.receiveSettingCards(initCards);
    }

    private void settingSelfCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            Card drawCard = deck.drawACard();
            this.receiveCard(drawCard);
        }
    }

    public Card showOneCard() {
        return this.cards.getCards().get(0);
    }

    public void giveACard(Player player) {
        player.receiveCard(deck.drawACard());
    }

    public void drawACard() {
        receiveCard(deck.drawACard());
    }

    public Map<Player, Result> requestResultToPlayers() {
        return players.makeResult(this.cards.calculateTotalScore());
    }

    public Map<Result, Integer> countSelfResults(final Map<Player, Result> playerResults) {
        Map<Result, Integer> dealerResults = new LinkedHashMap<>() {{
            put(WIN, 0);
            put(DRAW, 0);
            put(LOSE, 0);
        }};

        for (Result playerResult : playerResults.values()) {
            compareToPlayerResult(dealerResults, playerResult);
        }
        return dealerResults;
    }

    private void compareToPlayerResult(final Map<Result, Integer> dealerResult, final Result playerResult) {
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
        return cards.calculateTotalScore() <= DEALER_CAN_DRAW_SCORE;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
