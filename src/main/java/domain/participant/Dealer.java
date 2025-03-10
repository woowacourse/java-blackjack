package domain.participant;

import domain.GameResult;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;

import java.util.*;

public class Dealer extends Participant {
    private static final int DRAW_BOUNDARY = 16;

    private final Deck deck;
    private final Map<GameResult, Integer> result;

    public Dealer(Cards cards, Deck deck) {
        super(cards);
        this.deck = deck;
        this.result = new EnumMap<>(GameResult.class);
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

    public Map<Player, GameResult> getGameResult(Players players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult playerResult = getRule().getResult(player, this);
            gameResult.put(player, playerResult);
            GameResult dealerResult = playerResult.getReverse();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return gameResult;
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

    public Map<GameResult, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
