package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.cards.Cards;
import model.cards.DealerCards;
import model.cards.DealerCardsFactory;
import model.cards.PlayerCardsFactory;
import model.deck.Deck;
import model.deck.DeckFactory;
import model.result.GameResultPolicy;
import model.result.GameResults;

public class BlackjackGame {

    private final Deck deck;
    private final Players players;
    private final DealerCards dealerCards;

    public BlackjackGame(final Deck deck, final Players players, final DealerCards dealerCards) {
        this.deck = deck;
        this.players = players;
        this.dealerCards = dealerCards;
    }

    public Cards getPlayerCardsByName(final String name) {
        return players.findCardsByName(name);
    }

    public int getDealerResult() {
        return dealerCards.calculateResult();
    }

    public int getPlayerResultByName(final String name) {
        return players.findCardsByName(name).calculateResult();
    }

    public void giveCardToPlayer(final String name) {
        players.findCardsByName(name).addCard(deck.getCard());
    }

    public boolean checkIsBustByName(final String name) {
        return players.findCardsByName(name).isBust();
    }

    public int getDealerAdditionalDrawCount() {
        return dealerCards.getAdditionalDrawCount();
    }

    public Set<String> getSequencedPlayerNames() {
        return players.getNames();
    }

    public GameResults calculateGameResults() {
        return new GameResults(players.getNames().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        name -> GameResultPolicy.determineGameResult(dealerCards, players.findCardsByName(name)
                        )))
        );
    }

    public static BlackjackGame getBlackjackGame(
            final List<String> rawPlayers,
            final DeckFactory deckFactory,
            final PlayerCardsFactory playerCardsFactory,
            final DealerCardsFactory dealerCardsFactory
    ) {
        Deck deck = new Deck(deckFactory.getInitializedDeck());
        DealerCards dealerCards = (DealerCards) dealerCardsFactory.generate(deck);
        Players players = generatePlayersWithCards(rawPlayers, deck, playerCardsFactory);
        return new BlackjackGame(deck, players, dealerCards);
    }

    private static Players generatePlayersWithCards(
            final List<String> names,
            final Deck deck,
            final PlayerCardsFactory playerCardsFactory
    ) {
        Map<String, Cards> rawPlayers = new LinkedHashMap<>(names.size());
        names.forEach(name -> rawPlayers.put(name, playerCardsFactory.generate(deck)));

        return new Players(rawPlayers);
    }

    public Players getPlayers() {
        return players;
    }

    public DealerCards getDealerCards() {
        return dealerCards;
    }
}
