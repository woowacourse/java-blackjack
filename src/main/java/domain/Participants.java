package domain;

import domain.card.Card;
import exception.ErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Participants {

    public static final String DEALER_NAME = "딜러";

    private final Map<String, Participant> participants;

    public Participants(List<String> names) {
        participants = new HashMap<>();
        participants.put(DEALER_NAME, new Dealer(DEALER_NAME));

        validateDistinct(names);
        for (String name : names) {
            participants.put(name, new Player(name));
        }
    }

    public int size() {
        return participants.size();
    }

    public void addInitialCards(final List<Card> cards) {
        List<Card> modifiableCards = new ArrayList<>(cards);
        while (modifiableCards.size() > participants.size()) {
            addCardToAll(modifiableCards.subList(0, participants.size()));
            modifiableCards.subList(0, participants.size()).clear();
        }
        addCardToAll(modifiableCards);
    }

    private void addCardToAll(final List<Card> cards) {
        List<Card> modifiableCards = new ArrayList<>(cards);
        for (Participant participant : participants.values()) {
            participant.addCard(modifiableCards.removeFirst());
        }
    }

    public void addCardTo(String name, Card card) {
        participants.get(name).addCard(card);
    }

    public boolean isAbleToAddCard(String name) {
        Participant participant = participants.get(name);
        return participant.ableToAddCard();
    }

    public Map<String, GameResult> determineGameResult() {
        Map<String, GameResult> gameResults = new HashMap<>();
        Dealer dealer = findDealer();
        gameResults.put(dealer.name, new GameResult());
        for (String playerName : getPlayerNames()) {
            gameResults.put(playerName, new GameResult());
            determineParticipantsGameResult(gameResults, playerName, dealer.name);
            determineParticipantsGameResult(gameResults, dealer.name, playerName);
        }
        return gameResults;
    }

    private void determineParticipantsGameResult(
            Map<String, GameResult> playerResults,
            String name,
            String otherName
    ) {
        GameStatus gameStatus = participants.get(name).determineGameStatus(participants.get(otherName));
        playerResults.get(name).addStatusCount(gameStatus);
    }

    public Dealer findDealer() {
        return (Dealer) participants.get(DEALER_NAME);
    }

    public Map<String, Cards> getInitialCardsOfAll() {
        return getCardsBy(Participant::getInitialCards);
    }

    public Map<String, Cards> getCardsOfAll() {
        return getCardsBy(Participant::getCards);
    }

    private Map<String, Cards> getCardsBy(Function<Participant, List<Card>> function) {
        Map<String, Cards> cardsOfAll = new HashMap<>();
        participants.forEach((key, value) -> cardsOfAll.put(key, new Cards(function.apply(value))));
        return Collections.unmodifiableMap(cardsOfAll);
    }

    public Cards getCardsOf(String name) {
        List<Card> cards = participants.get(name).getCards();
        return new Cards(cards);
    }

    public List<String> getPlayerNames() {
        return participants.keySet().stream()
                .filter(name -> !name.equals(DEALER_NAME))
                .toList();
    }

    private void validateDistinct(List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new ErrorException("참여자 이름은 중복될 수 없습니다.");
        }
    }
}
