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
import model.cards.PlayerCards;
import model.cards.PlayerCardsFactory;
import model.deck.Deck;
import model.deck.DeckFactory;
import model.result.GameResult;
import model.result.GameResults;

public class BlackjackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public int getDealerResult() {
        return participants.getDealerCards().calculateResult();
    }

    public int getPlayerResultByName(final String name) {
        return participants.findCardsByName(name).calculateResult();
    }

    public void giveCardToPlayer(final String name) {
        participants.findCardsByName(name).addCard(deck.getCard());
    }

    public boolean checkIsBustByName(final String name) {
        return participants.findCardsByName(name).isBust();
    }

    public int getDealerAdditionalDrawCount() {
        return participants.getDealerCards().getAdditionalDrawCount();
    }

    public Set<String> getSequencedPlayerNames() {
        return participants.getNames();
    }

    public GameResults calculateGameResults() {
        return new GameResults(participants.getNames().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        name -> GameResult.determineGameResult(participants.getDealerCards(), participants.findCardsByName(name)
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
        Participants participants = generatePlayersWithCards(rawPlayers, deck, dealerCardsFactory, playerCardsFactory);
        return new BlackjackGame(deck, participants);
    }

    private static Participants generatePlayersWithCards(
            final List<String> names,
            final Deck deck,
            final DealerCardsFactory dealerCardsFactory,
            final PlayerCardsFactory playerCardsFactory
    ) {
        DealerCards dealerCards = (DealerCards) dealerCardsFactory.generate(deck);

        Map<String, Cards> playerCards = new LinkedHashMap<>(names.size());
        names.forEach(name -> playerCards.put(name, playerCardsFactory.generate(deck)));

        return new Participants(dealerCards, playerCards);
    }

    public PlayerCards getPlayerCardsByName(final String name) {
        return participants.findCardsByName(name);
    }

    public DealerCards getDealerCards() {
        return participants.getDealerCards();
    }
}
