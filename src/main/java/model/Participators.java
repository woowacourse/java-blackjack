package model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.cardbehavior.EveryCardsBehavior;

public class Participators {
    private final List<Participator> participators;

    public Participators(List<String> playerNames) {
        List<Participator> participators = playerNames.stream().map(Player::new).collect(Collectors.toList());
        participators.add(new Dealer());
        this.participators = participators;
    }

    public void receiveCards(CardDeck cardDeck) {
        for (Participator participator : participators) {
            participator.receiveCard(cardDeck.drawCard());
        }
    }

    public void receiveCardTo(String name, CardDeck cardDeck) {
        findName(name).receiveCard(cardDeck.drawCard());
    }

    public Participator findName(String name) {
        return participators.stream()
                .filter(participator -> participator.isSameName(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<String> getPlayerNames() {
        return findPlayers().stream()
                .map(Participator::getPlayerName)
                .map(PlayerName::getValue)
                .collect(Collectors.toList());
    }

    public List<Participator> findPlayers() {
        return participators.stream()
                .filter(participator -> participator instanceof Player)
                .collect(Collectors.toList());

    }

    public Dealer findDealer() {
        return (Dealer) participators.stream()
                .filter(participator -> participator instanceof Dealer).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean canReceiveCard(String name) {
        Participator participator = findName(name);
        return participator.canReceiveCard();
    }

    public Participator tryToHitForDealer(CardDeck cardDeck) {
        Dealer dealer = findDealer();
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(cardDeck.drawCard());
        }
        dealer.setBehavior(new EveryCardsBehavior());
        return dealer;
    }

    public Map<PlayerName, Result> matchAll() {
        Dealer dealer = findDealer();
        return participators.stream()
                .filter(participator -> participator instanceof Player)
                .collect(toMap(player -> player.getPlayerName(), player -> dealer.matchWith(player)));
    }
}
