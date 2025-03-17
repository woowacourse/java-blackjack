package Blackjack.domain.game;

import Blackjack.domain.card.Card;
import Blackjack.domain.card.Cards;
import Blackjack.domain.participant.Dealer;
import Blackjack.domain.participant.Participant;
import Blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Participants {

    public static final String DEALER_NAME = "딜러";

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Participants(final List<String> names) {
        this(new Dealer(DEALER_NAME), Players.of(names));
    }

    public Participants(final Players players) {
        this(new Dealer(DEALER_NAME), players);
    }

    public int size() {
        return players.size() + 1;
    }

    public void addInitialCards(final List<Card> cards) {
        final List<Card> modifiableCards = new ArrayList<>(cards);
        while (modifiableCards.size() > size()) {
            addCardToAll(modifiableCards.subList(0, size()));
            modifiableCards.subList(0, size()).clear();
        }
        addCardToAll(modifiableCards);
    }

    private void addCardToAll(final List<Card> cards) {
        dealer.addCard(cards.getFirst());
        List<Card> playerCards = cards.stream().skip(1).toList();
        players.addCardToAll(playerCards);
    }

    public void addCardTo(final String name, final Card card) {
        players.addCardTo(name, card);
    }

    public boolean isAbleToAddCard(final String name) {
        return players.canHit(name);
    }

    public boolean canHitDealer() {
        return dealer.ableToAddCard();
    }

    public void hitDealer(final Card card) {
        dealer.addCard(card);
    }

    /*public Map<String, GameResult> determineGameResult() {
        final Map<String, GameResult> gameResults = new HashMap<>();
        gameResults.put(dealer.getName(), new GameResult());
        for (String playerName : getPlayerNames()) {
            gameResults.put(playerName, new GameResult());
            determineParticipantsGameResult(gameResults, playerName, dealer.getName());
            determineParticipantsGameResult(gameResults, dealer.getName(), playerName);
        }
        return gameResults;
    }

    private void determineParticipantsGameResult(
            final Map<String, GameResult> playerResults,
            final String name,
            final String otherName
    ) {
        final GameStatus gameStatus = participants.get(name).determineGameStatus(participants.get(otherName));
        playerResults.get(name).addStatusCount(gameStatus);
    }*/

    public Map<String, Cards> getInitialCardsOfAll() {
        return getCardsBy(Participant::getInitialCards);
    }

    public Map<String, Cards> getCardsOfAll() {
        return getCardsBy(Participant::getCards);
    }

    private Map<String, Cards> getCardsBy(Function<Participant, List<Card>> function) {
        final Map<String, Cards> cardsOfAll = new HashMap<>();
        cardsOfAll.put(dealer.getName(), new Cards(function.apply(dealer)));
        List<String> names = players.getNames();
        names.forEach(name -> cardsOfAll.put(name, players.getCardsOf(name)));
        return Collections.unmodifiableMap(cardsOfAll);
    }

    public Cards getCardsOf(final String name) {
        return players.getCardsOf(name);
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }
}
