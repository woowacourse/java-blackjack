package model;

import java.util.*;
import utils.StringUtils;

import static controller.BlackJackGame.INITIAL_DRAW_COUNT;

public class Players implements Iterable<Player> {
    private static final String COMMA = ",";
    private final List<Player> players = new ArrayList<>();

    public Players(String input, Deck deck) {
        validate(input);
        List<String> names = Arrays.asList(StringUtils.trimString(input).split(COMMA));
        for (String name : names) {
            players.add(new Player(name, deck.draw(INITIAL_DRAW_COUNT)));
        }
    }

    private void validate(String input) {
        StringUtils.validateNull(input);
        validateSplit(input);
    }

    private void validateSplit(String input) {
        for (String name : input.split(COMMA)) {
            StringUtils.validateEmpty(name);
        }
    }

    public String getNames() {
        List<String> names = new ArrayList<>();

        for (Player player : players) {
            names.add(player.getName());
        }
        return String.join(COMMA, names);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    public boolean contains(Player player) {
        return players.contains(player);
    }
}
