package model;

import model.card.CardDeck;
import model.card.Cards;
import model.player.Dealer;
import model.player.Name;
import model.player.Participants;
import model.player.Users;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BlackJack {
    private final Users users;
    private final CardDeck cardDeck;

    public BlackJack(Users users, CardDeck cardDeck) {
        this.users = users;
        this.cardDeck = cardDeck;
    }

    public void decideParticipantsPlay(Predicate<Name> inputForMoreCard,
                                       BiConsumer<Name, Cards> printParticipantsCard) {
        users.decideParticipantsPlay(inputForMoreCard, printParticipantsCard, cardDeck::selectRandomCard);
    }

    public void decideDealerPlay(Runnable runnable) {
        users.decideDealerPlay(runnable, cardDeck::selectRandomCard);
    }

    public Map<Name, Cards> matchParticipantsNameAndCards() {
        return users.matchParticipantsNameAndCards();
    }

    public int findDealerRevenue() {
        return matchNameAndRevenues()
                .values()
                .stream()
                .mapToInt(money -> money)
                .sum() * -1;
    }

    public Map<Name, Integer> matchNameAndRevenues() {
        return users.matchNameAndRevenues();
    }

    public List<Name> findParticipantsName() {
        return users.findParticipantsName();
    }

    public Cards findDealerCards() {
        return users.dealer().cards();
    }
}
