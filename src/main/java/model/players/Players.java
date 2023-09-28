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

    public static Players createDefault() {
        List<Player> players = new ArrayList<>();
        players.add(joinDealer());

        return new Players(players);
    }

    public void joinPlayers(String input) {
        List<String> splitNames = Name.createSplitNameValues(input);

        List<Player> addPlayers = splitNames.stream()
                .map(PlayerRequest::from)
                .map(Player::from)
                .collect(Collectors.toList());

        players.addAll(addPlayers);
    }

    private static Player joinDealer() {
        PlayerRequest dealerRequest = PlayerRequest.from(Name.getDealer());
        return Player.from(dealerRequest);
    }

    public String getPlayerNamesExceptDealer() {
        List<Name> names = players.stream()
                .map(player -> PlayerResponse.createDefault(player.getName(), player.getScore()))
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

}
