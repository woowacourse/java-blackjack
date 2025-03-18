package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.participant.Participants;
import model.result.BettingResults;
import model.bet.ParticipantsBet;
import model.cards.DealerCards;
import model.cards.DealerCardsGenerator;
import model.cards.ParticipantsCards;
import model.cards.PlayerCards;
import model.cards.PlayerCardsGenerator;
import model.deck.Deck;
import model.deck.DeckGenerator;
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

    public boolean canPlayerDrawCard(final String name) {
        return participants.findCardsByName(name).canDraw();
    }

    public int getDealerAdditionalDrawCount() {
        return participants.getDealerCards().getAdditionalDrawCount();
    }

    public Set<String> getSequencedPlayerNames() {
        return participants.getPlayerNames();
    }

    public BettingResults calculateBettingResults() {
        GameResults gameResults = calculateGameResults();
        return participants.calculateBettingResults(gameResults);
    }

    private GameResults calculateGameResults() {
        return new GameResults(participants.getPlayerNames().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        name -> GameResult.determineGameResult(
                                participants.getDealerCards(),
                                participants.findCardsByName(name))
                )));
    }

    public static BlackjackGame createBlackjackGame(
            final List<String> rawPlayers,
            final List<Integer> betAmount
    ) {
        DeckGenerator deckGenerator = new DeckGenerator();
        Deck deck = new Deck(deckGenerator.getInitializedDeck());
        Participants participants = generateParticipants(rawPlayers, betAmount, deck);
        return new BlackjackGame(deck, participants);
    }

    private static Participants generateParticipants(
            final List<String> names,
            final List<Integer> betAmount,
            final Deck deck
    ) {
        ParticipantsCards participantsCards = generateParticipantsCards(names, deck);
        ParticipantsBet participantsBet = generateParticipantsBet(names, betAmount);

        return new Participants(participantsCards, participantsBet);
    }

    private static ParticipantsCards generateParticipantsCards(
            final List<String> names,
            final Deck deck
    ) {
        DealerCardsGenerator dealerCardsGenerator = new DealerCardsGenerator();
        PlayerCardsGenerator playerCardsGenerator = new PlayerCardsGenerator();

        DealerCards dealerCards = (DealerCards) dealerCardsGenerator.generate(deck);

        Map<String, PlayerCards> playerCards = new LinkedHashMap<>(names.size());
        names.forEach(name -> playerCards.put(name, (PlayerCards) playerCardsGenerator.generate(deck)));

        return new ParticipantsCards(dealerCards, playerCards);
    }

    private static ParticipantsBet generateParticipantsBet(
            final List<String> names,
            final List<Integer> betAmount
    ) {
        Map<String, Integer> playerBet = new LinkedHashMap<>(names.size());
        for (int i = 0; i < names.size(); i++) {
            playerBet.put(names.get(i), betAmount.get(i));
        }
        return new ParticipantsBet(playerBet);
    }

    public PlayerCards getPlayerCardsByName(final String name) {
        return participants.findCardsByName(name);
    }

    public DealerCards getDealerCards() {
        return participants.getDealerCards();
    }
}
