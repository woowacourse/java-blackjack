package model;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.cardbehavior.EveryCardsBehavior;

public class Participators {
    private final List<Participator> participators;

    public Participators(List<String> playerNames) {
        List<Participator> participators = playerNames.stream().map(Player::new).collect(Collectors.toList());
        participators.add(0, new Dealer());
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
                .map(participator -> (Player) participator)
                .collect(toMap(Participator::getPlayerName, player -> player.matchWith(dealer)));
    }

    public List<Participator> findAll() {
        return participators;
    }

    public Map<PlayerName, Result> getPlayersFinalMatchResult() {
        Dealer dealer = findDealer();
        return participators.stream()
                .filter(participator -> participator instanceof Player)
                .map(participator -> (Player) participator)
                .collect(toMap(Participator::getPlayerName, player -> player.matchWith(dealer)));
    }

    public Map<PlayerName, List<Result>> getDealerFinalMatchResult() {
        List<Result> playerFinalResults = new ArrayList<>(getPlayersFinalMatchResult().values());
        List<Result> dealerFinalResults = playerFinalResults
                .stream()
                .map(Result::reverseOf)
                .collect(Collectors.toList());
        Map<PlayerName, List<Result>> result = new HashMap<>();
        result.put(findDealer().getPlayerName(), dealerFinalResults);
        return result;
    }
}
