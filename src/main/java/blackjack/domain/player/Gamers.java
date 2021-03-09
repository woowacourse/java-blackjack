package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.exception.GamerDuplicateException;
import blackjack.exception.PlayerNotFoundException;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Gamers {

    private final List<? extends Player> gamers;

    public Gamers(List<Gamer> gamers) {
        validateDuplicate(gamers);

        this.gamers = gamers;
    }

    private void validateDuplicate(List<Gamer> gamers) {
        if (gamers.size() != gamers.stream().map(Gamer::getName).distinct().count()) {
            throw new GamerDuplicateException();
        }
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