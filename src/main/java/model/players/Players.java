package model.players;

import model.card.Card;
import model.deck.Deck;
import model.name.Name;
import model.player.Player;
import model.player.dto.PlayerRequest;
import model.player.dto.PlayerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        PlayerRequest dealerRequest = PlayerRequest.from(Name.getDealer());
        return Player.of(dealerRequest.getName(), dealerRequest.getCards());
    }

    public String getPlayerNamesExceptDealer() {
        List<Name> names = players.stream()
                .map(player -> PlayerResponse.createDefault(player.getName(), player.getCards()))
                .map(PlayerResponse::getName)
                .map(Name::from)
                .filter(Name::isNotDealer)
                .collect(Collectors.toList());

        return Name.chainingNames(names);
    }

    public void giveInitialCards(Deck deck, int count) {
        players.forEach(player -> {
            List<Card> initCards = deck.getCards(count);
            player.selectCardsFromDeck(initCards);
        });
    }

    public boolean dealerScoreUnder(final String dealer, final int score) {
        Player targetDealer = findByName(dealer);
        return targetDealer.getScore() <= score;
    }

    public boolean dealerScoreEnough(final String dealer, final int score) {
        return !dealerScoreUnder(dealer, score);
    }

    public List<String> showInitialStatusExceptDealer() {
        return players.stream()
                .filter(player -> player.getName().isNotDealer())
                .map(player -> PlayerResponse.createDefault(player.getName(), player.getCards()))
                .map(PlayerResponse::getCards)
                .collect(Collectors.toList());
    }

    public String printShowStatus(final String name) {
        Player player = findByName(name);
        PlayerResponse response = PlayerResponse.createDefault(player.getName(), player.getCards());
        return response.getCards();
    }

    public String showInitialDealerStatus() {
        Player dealer = players.get(0);
        PlayerResponse response = PlayerResponse.createDefault(dealer.getName(), dealer.getCards());

        return Name.getDealer() + response.getCardsWithSecret();
    }

    public void giveOneCard(final Deck deck, final String name) {
        Player targetPlayer = findByName(name);
        targetPlayer.selectCardsFromDeck(deck.getCards(1));
    }

    public boolean isNotExceed(final String name, int goal) {
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

    public List<String> result() {
        List<String> result = new ArrayList<>();
        result.add(dealerResult());
        result.addAll(playerResult());

        return result;
    }

    private String dealerResult() {
        Player dealer = findByName(Name.getDealer());
        PlayerResponse response = PlayerResponse.createDefault(dealer.getName(), dealer.getCards());
        return response.getDealerCardsResult();
    }

    private List<String> playerResult() {
        return players.stream()
                .filter(player -> player.getName().isNotDealer())
                .map(player -> PlayerResponse.createDefault(player.getName(), player.getCards()))
                .map(PlayerResponse::getPlayerCardsResult)
                .collect(Collectors.toList());
    }
}
