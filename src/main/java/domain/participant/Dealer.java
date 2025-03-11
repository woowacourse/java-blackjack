package domain.participant;

import domain.GameResult;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;

import java.util.*;

public class Dealer extends Participant {
    private static final int DRAW_BOUNDARY = 16;

    private final Deck deck;

    public Dealer(Cards cards, Deck deck) {
        super(cards);
        this.deck = deck;
    }

    @Override
    public List<Card> getShowCards() {
        List<Card> cards = getCards().getValues();
        return cards.subList(0, 1);
    }

    public void handoutCards(Players players) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(this);
        deck.handoutCards(participants);
    }

    public void giveOneCardTo(Participant participant) {
        participant.addCard(deck.pick());
    }

    public Map<Player, GameResult> getPlayerResult(Players players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult playerResult = getRule().getResult(player, this);
            gameResult.put(player, playerResult);
        }
        return gameResult;
    }

    public Map<GameResult, Integer> getDealerResult(Players players) {
        Map<GameResult, Integer> result = new HashMap<>();
        Map<Player, GameResult> gameResult = getPlayerResult(players);
        for (GameResult playerResult : gameResult.values()) {
            GameResult dealerResult = playerResult.getReverse();
            final int updated = result.getOrDefault(dealerResult, 0) + 1;
            result.put(dealerResult, updated);
        }
        return result;
    }

    public int drawCards() {
        int count = 0;
        while (hasToDraw()) {
            addCard(deck.pick());
            count++;
        }
        return count;
    }

    private boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }
}
