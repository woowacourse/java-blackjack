package model;

import model.card.CardDeck;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BlackJack {

    private final Participants participants;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJack(Participants participants, Dealer dealer, CardDeck cardDeck) {
        validateParticipantIsNotNull(participants);
        validateDealerIsNotNull(dealer);

        this.participants = participants;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public void validateParticipantIsNotNull(Participants participants) {
        if (participants == null) {
            throw new IllegalArgumentException("참가자 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void validateDealerIsNotNull(Dealer dealer) {
        if (dealer == null) {
            throw new IllegalArgumentException("딜러가 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void decideParticipantsPlay(Predicate<String> inputForMoreCard, BiConsumer<String, Cards> printParticipantsCard) {
        participants.offerCardToParticipants(inputForMoreCard, printParticipantsCard, cardDeck::selectRandomCard);
    }

    public void decideDealerPlay(Runnable runnable) {
        while (dealer.isHit()) {
            runnable.run();
            dealer.addCard(cardDeck.selectRandomCard());
        }
    }

    public Map<String, Cards> matchUsersNameAndCards() {
        Map<String, Cards> matchUser = new LinkedHashMap<>(matchDealerNameAndCards());
        matchUser.putAll(matchParticipantsNameAndCards());
        return matchUser;
    }

    private Map<String, Cards> matchDealerNameAndCards() {
        return Map.of(dealer.getName(), dealer.getCards());
    }

    private Map<String, Cards> matchParticipantsNameAndCards() {
        return participants.matchParticipantNameAndCards();
    }

    public int findDealerRevenue() {
        return matchNameAndRevenues()
                .values()
                .stream()
                .mapToInt(money -> money)
                .sum() * -1;
    }

    public Map<String, Integer> matchNameAndRevenues() {
        return participants.matchNameAndRevenues(dealer);
    }

    public List<String> findParticipantsName() {
        return participants.findParticipantsName();
    }
}
