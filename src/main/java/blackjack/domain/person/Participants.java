package blackjack.domain.person;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Participants {
    private final List<Person> participants = new ArrayList<>();
    private final BettingMoney bettingMoney;

    public Participants(Dealer dealer, Players players, BettingMoney bettingMoney) {
        this.participants.add(dealer);
        this.participants.addAll(players.getPlayers());
        this.bettingMoney = bettingMoney;
    }

    public Person findByName(String name) {
        return participants.stream()
                .filter(person -> person.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 이름과 일치하는 참가자가 없습니다."));
    }

    public int getPlayerScore(String name) {
        return findByName(name).getScore();
    }

    public Person getDealer() {
        return participants.stream()
                .filter(Person::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 딜러가 없습니다."));
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public List<Card> getDealerInitCard() {
        return getDealer().getInitCards();
    }

    public int getDealerScore() {
        return getDealer().getScore();
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(Person::isPlayer)
                .map(Player.class::cast)
                .collect(toList());
    }

    public List<Person> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<Card> getCardsByName(String name) {
        return findByName(name).getCards();
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
