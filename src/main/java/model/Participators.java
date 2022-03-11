package model;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.card.CardDeck;

public class Participators {
    private final List<Participator> participators;

    public Participators(List<String> playerNames) {
        if (isDuplicate(playerNames)) {
            throw new IllegalArgumentException("중복된 플레이어는 존재할 수 없습니다.");
        }
        this.participators = createAllParticipatorsAndDealer(playerNames);
    }

    private List<Participator> createAllParticipatorsAndDealer(List<String> names) {
        List<Participator> participators = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        participators.add(0, new Dealer());
        return participators;
    }

    private boolean isDuplicate(List<String> playerNames) {
        return playerNames.stream().distinct().count() != playerNames.size();
    }

    public void receiveCardsAll(CardDeck cardDeck) {
        for (Participator participator : participators) {
            participator.receiveCard(cardDeck.drawCard());
        }
    }

    public void receiveCardTo(String name, CardDeck cardDeck) {
        findByName(name).receiveCard(cardDeck.drawCard());
    }

    public Participator findByName(String name) {
        return participators.stream()
                .filter(participator -> participator.isSameName(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
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
        Participator participator = findByName(name);
        return participator.canReceiveCard();
    }

    public Map<String, Result> matchAll() {
        Dealer dealer = findDealer();
        return participators.stream()
                .filter(participator -> participator instanceof Player)
                .map(participator -> (Player) participator)
                .collect(toMap(player -> player.getPlayerName(), player -> player.matchWith(dealer)));
    }

    public List<Participator> findAll() {
        return participators;
    }
}
