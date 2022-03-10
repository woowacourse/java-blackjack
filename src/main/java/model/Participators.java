package model;

import java.util.List;
import java.util.stream.Collectors;

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

    public Participator findDealer() {
        return participators.stream()
                .filter(participator -> participator instanceof Dealer).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
