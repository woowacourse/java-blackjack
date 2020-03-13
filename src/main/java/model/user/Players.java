package model.user;

import exception.OverlapPlayerNameException;
import java.util.*;
import model.card.Deck;
import utils.StringUtils;

import static controller.BlackJackGame.INITIAL_DRAW_COUNT;

public class Players implements Iterable<Player> {

    private static final String COMMA = ",";
    private List<Player> players = new ArrayList<>();

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
        validateOverlap(input);
    }

    private void validateSplit(String input) {
        for (String name : input.split(COMMA)) {
            StringUtils.validateEmpty(name);
        }
    }

    private void validateOverlap(String input) {
        List<String> names = Arrays.asList(StringUtils.trimString(input).split(COMMA));
        Set<String> overlapNameFilter = new HashSet<>(names);
        if (names.size() != overlapNameFilter.size()) {
            throw new OverlapPlayerNameException("중복된 이름이 있습니다.");
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
