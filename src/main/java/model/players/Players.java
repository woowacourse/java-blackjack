package model.players;

import model.card.Card;
import model.deck.Deck;
import model.name.Name;
import model.player.Player;
import model.player.dto.PlayerRequest;
import model.player.dto.PlayerResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static util.Keyword.DEALER;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final String input) {
        List<Player> players = new ArrayList<>();

        players.add(joinDealer());
        players.addAll(joinPlayers(input));

        return new Players(players);
    }

    private static List<Player> joinPlayers(String input) {
        List<String> splitNames = Name.createSplitNameValues(input);

        return splitNames.stream()
                .map(PlayerRequest::from)
                .map(request -> Player.of(request.getName(), request.getCards()))
                .collect(Collectors.toList());
    }

    private static Player joinDealer() {
        PlayerRequest dealerRequest = PlayerRequest.from(DEALER.getValue());
        return Player.of(dealerRequest.getName(), dealerRequest.getCards());
    }

    public String getPlayerNamesExceptDealer() {
        List<Name> names = players.stream()
                .map(player -> PlayerResponse.createDefault(player.getName(), player.getCards()))
                .map(PlayerResponse::getNameValue)
                .map(Name::from)
                .filter(Name::isNotDealer)
                .collect(Collectors.toList());

        return Name.chainingNames(names);
    }

    public void giveInitialCards(Deck deck, int count) {
        players.forEach(player -> {
            List<Card> initCards = deck.getCardsFromDeckAsMuchAs(count);
            player.addCards(initCards);
        });
    }

    public boolean dealerScoreUnder(final String dealer, final int score) {
        Player targetDealer = findByName(dealer);
        return targetDealer.getScore() <= score;
    }

    public boolean dealerScoreEnough(final String dealer, final int score) {
        return !dealerScoreUnder(dealer, score);
    }

    public List<PlayerResponse> playersResponse() {
        return players.stream()
                .map(player -> PlayerResponse.createDefault(player.getName(), player.getCards()))
                .collect(Collectors.toList());
    }

    public void giveOneCard(final Deck deck, final String name) {
        Player targetPlayer = findByName(name);
        targetPlayer.addCards(deck.getCardsFromDeckAsMuchAs(1));
    }

    public boolean isNotExceed(final String name, final int goal) {
        Player targetPlayer = findByName(name);
        return targetPlayer.getScore() < goal;
    }

    private Player findByName(final String name) {
        return players.stream()
                .filter(player -> player.getName().equals(Name.from(name)))
                .findFirst()
                .orElse(null);
    }

    public List<String> getPlayerNameValuesExceptDealer() {
        return players.stream()
                .map(Player::getName)
                .filter(Name::isNotDealer)
                .map(Name::getName)
                .collect(Collectors.toList());
    }

    public PlayerResponse getPlayerResponseByName(final String name) {
        Player player = findByName(name);
        return PlayerResponse.createDefault(player.getName(), player.getCards());
    }

    private List<Player> getPlayersOrderByDistance(int goal) {
        return players.stream()
                .sorted(Comparator.comparing(response -> response.getDistance(goal)))
                .collect(Collectors.toList());
    }

    public List<PlayerResponse> calculateEachGradeWithGoal(int goal) {
        List<Player> orderedPlayers = getPlayersOrderByDistance(goal);

        return eachPlayerWriteGrade(orderedPlayers, goal);
    }

    private List<PlayerResponse> eachPlayerWriteGrade(final List<Player> orderedPlayers,
                                             final int goal) {
        orderedPlayers.forEach(player -> {
            int grade = Math.abs(player.getScore() - goal);
            player.writeGrade(grade);
        });

        return players.stream()
                .map(player -> PlayerResponse.withGrade(player.getName(), player.getCards(), player.getGrade()))
                .collect(Collectors.toList());
    }

    public int getDealerWin(int grade) {
        AtomicInteger win = new AtomicInteger();

        List<PlayerResponse> playerResponses = getPlayerResponsesExceptDealer();

        playerResponses.forEach(response -> {
            if (response.getGrade() > grade) {
                win.getAndIncrement();
            }
        });

        return win.get();
    }

    private List<PlayerResponse> getPlayerResponsesExceptDealer() {
        return players.stream()
                .filter(player -> player.getName().isNotDealer())
                .map(player -> PlayerResponse.withGrade(player.getName(), player.getCards(), player.getGrade()))
                .collect(Collectors.toList());
    }

    public int getDealerSame(int grade) {
        AtomicInteger same = new AtomicInteger();

        List<PlayerResponse> playerResponses = getPlayerResponsesExceptDealer();

        playerResponses.forEach(response -> {
            if (response.getGrade() == grade) {
                same.getAndIncrement();
            }
        });

        return same.get();
    }

    public int getDealerLose(int grade) {
        AtomicInteger lose = new AtomicInteger();

        List<PlayerResponse> playerResponses = getPlayerResponsesExceptDealer();

        playerResponses.forEach(response -> {
            if (response.getGrade() < grade) {
                lose.getAndIncrement();
            }
        });

        return lose.get();
    }
}
