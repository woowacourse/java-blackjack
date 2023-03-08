package blackjack.domain.participant;

import static blackjack.domain.participant.Participant.INIT_CARD_COUNT;
import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public final class Dealer {

    private static final String NAME = "딜러";
    public static final int CAN_DRAW_SCORE = 16;

    private final Players players;
    private final Deck deck;
    private final Participant participant;

    public Dealer(final Players players) {
        this.players = players;
        this.deck = new Deck();
        this.participant = new Participant(NAME);
    }

    public void receiveCard(final Card card) {
        participant.receiveCard(card);
    }

    public int calculateTotalScore() {
        return participant.calculateTotalScore();
    }

    public void settingCards() {
        settingPlayersCards();
        settingSelfCards();
    }

    private void settingPlayersCards() {
        List<Card> initCards = IntStream.range(0, players.size() * INIT_CARD_COUNT)
                .mapToObj(x -> deck.drawCard())
                .collect(toList());

        players.receiveSettingCards(initCards);
    }

    private void settingSelfCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            Card drawCard = deck.drawCard();
            this.receiveCard(drawCard);
        }
    }

    public Card showOneCard() {
        return participant.getCards().get(0);
    }

    public void giveCard(Player player) {
        player.receiveCard(deck.drawCard());
    }

    public void drawCard() {
        receiveCard(deck.drawCard());
    }

    public Map<Player, Result> requestResultToPlayers() {
        return players.makeResult(participant.calculateTotalScore());
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
        return participant.calculateTotalScore() <= CAN_DRAW_SCORE;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Card> getCards() {
        return participant.getCards();
    }

    public String getName() {
        return NAME;
    }
}
