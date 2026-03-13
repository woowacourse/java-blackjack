package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vo.Bet;
import vo.Name;

public class Participants {
    private static final Integer MAX_PLAYER_COUNT = 16;

    private final List<User> participants;
    private final Dealer dealer;

    public Participants(List<Name> names, Map<Name, Bet> bets) {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        saveUsers(names, bets);
    }

    private void saveUsers(List<Name> names, Map<Name, Bet> bets) {
        names.forEach(name -> {
            participants.add(new User(name, bets.get(name)));
        });
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Integer getDealerScore() {
        return dealer.getScore();
    }

    public List<String> getParticipantNames() {
        return participants.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public void dealCards(Deck deck) {
        for (User user : participants) {
            user.receiveCard(deck.dealCard());
        }
        dealer.receiveCard(deck.dealCard());
    }

    public void dealCard(Deck deck, User user) {
        user.receiveCard(deck.dealCard());
    }

    public Boolean determineDealerDealMore() {
        return dealer.determineDealerDealMore();
    }

    public void dealCardToDealer(Card card) {
        dealer.receiveCard(card);
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
    }
}
