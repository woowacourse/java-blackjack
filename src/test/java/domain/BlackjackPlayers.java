package domain;

import except.BlackJackException;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackPlayers {

    private final List<Player> players;
    private final Dealer dealer;
    private final String INVALID_PLAYER = "존재하지 않는 플레이어입니다.";


    public BlackjackPlayers(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void giveToPlayer(String name, TrumpCard trumpCard) {
        Player findPlayer = findPlayer(name);
        findPlayer.addDraw(trumpCard);
    }

    private Player findPlayer(String name) {
        return players.stream()
                .filter((player) -> player.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new BlackJackException(INVALID_PLAYER));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public void giveToDealer(TrumpCard trumpCard) {
        dealer.addDraw(trumpCard);
    }

    public List<TrumpCard> playerCardsString name) {
        Player player = findPlayer(name);
        return player.trumpCards();
    }
}
