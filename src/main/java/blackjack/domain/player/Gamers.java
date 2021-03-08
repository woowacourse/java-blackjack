package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.exception.GamerDuplicateException;
import blackjack.exception.PlayerNotFoundException;
import blackjack.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Gamers {

    private final List<Player> gamers;

    public Gamers(List<Pair<String, Integer>> nameAndBettingMonies) {
        validateDuplicate(nameAndBettingMonies.stream()
                .map(Pair::getKey)
                .collect(toList()));

        this.gamers = nameAndBettingMoneyToGamer(nameAndBettingMonies);
    }

    private void validateDuplicate(List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new GamerDuplicateException();
        }
    }

    private List<Player> nameAndBettingMoneyToGamer(List<Pair<String, Integer>> nameAndBettingMonies) {
        return nameAndBettingMonies.stream()
                .map(nameAndBettingMoney -> new Gamer(nameAndBettingMoney.getKey(),
                        nameAndBettingMoney.getValue()))
                .collect(Collectors.toList());
    }

    public void drawToGamers(Cards cards) {
        for (Player gamer : gamers) {
            gamer.addCardToDeck(cards.next());
        }
    }

    public Player findGamer(String name) {
        return gamers.stream()
                .filter(gamer -> gamer.isSameName(name))
                .findAny()
                .orElseThrow(PlayerNotFoundException::new);
    }

    public List<Player> getGamers() {
        return Collections.unmodifiableList(gamers);
    }

    public List<String> getGamerNames() {
        return gamers.stream()
                .map(Player::getName)
                .collect(toList());
    }
}