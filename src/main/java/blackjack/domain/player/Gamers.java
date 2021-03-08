package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.exception.GamerDuplicateException;
import blackjack.exception.PlayerNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Gamers {

    public static class NameAndBettingMoney {

        private final String name;
        private final int bettingMoney;

        public NameAndBettingMoney(String nmae, int bettingMoney) {
            this.name = nmae;
            this.bettingMoney = bettingMoney;
        }

        public String getName() {
            return name;
        }

        public int getBettingMoney() {
            return bettingMoney;
        }

    }

    private final List<Player> gamers;

    public Gamers(List<NameAndBettingMoney> nameAndBettingMonies) {
        validateDuplicate(nameAndBettingMonies.stream()
                .map(NameAndBettingMoney::getName)
                .collect(toList()));

        this.gamers = nameAndBettingMoneyToGamer(nameAndBettingMonies);
    }

    private void validateDuplicate(List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new GamerDuplicateException();
        }
    }

    private List<Player> nameAndBettingMoneyToGamer(List<NameAndBettingMoney> nameAndBettingMonies) {
        return nameAndBettingMonies.stream()
                .map(nameAndBettingMoney -> new Gamer(nameAndBettingMoney.getName(),
                        nameAndBettingMoney.getBettingMoney()))
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