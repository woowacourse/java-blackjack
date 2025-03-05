package domain;

import except.BlackJackException;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private final int MAX_PEOPLE_WITHOUT_DEALER = 7;
    private final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 1-7명만 이용하실 수 있습니다";

    private final BlackjackPlayers blackjackPlayers;
    private final Deck deck;

    public BlackjackGame(List<String> names, Deck deck, Dealer dealer) {
        int playerSize = names.size();
        if (playerSize < MIN_PEOPLE_WITHOUT_DEALER || playerSize > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new BlackJackException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        this.deck = deck;
        this.blackjackPlayers = new BlackjackPlayers(players, dealer);
        gameStart();
    }

    private void gameStart() {
        for (String name : blackjackPlayers.getPlayerNames()) {
            blackjackPlayers.giveToPlayer(name, deck.drawCard());
            blackjackPlayers.giveToPlayer(name, deck.drawCard());
        }
        blackjackPlayers.giveToDealer(deck.drawCard());
        blackjackPlayers.giveToDealer(deck.drawCard());
    }

    public List<TrumpCard> playerCards(String name) {
        return blackjackPlayers.playerCards(name);
    }
}
