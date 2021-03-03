package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participant.BlackJackParticipant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlackJackGame {

    public static final int STARTING_CARD_COUNT = 2;

    private final Players unpreparedPlayers;
    private final Players preparedPlayers;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(String playersInput, List<Card> deck) {
        this.unpreparedPlayers = Players.valueOf(playersInput);
        this.preparedPlayers = new Players(new ArrayList<>());
        Collections.shuffle(deck);
        this.deck = new Deck(deck);
        this.dealer = new Dealer();
    }

    public void prepare() {
        for (int i = 0; i < STARTING_CARD_COUNT; i++) {
            unpreparedPlayers.unwrap().stream().forEach(player -> player.drawCard(deck));
            dealer.drawCard(deck);
        }
    }

    public Player nextPlayer() {
        Player player = unpreparedPlayers.pop();
        preparedPlayers.push(player);
        return player;
    }

    public boolean isPrepared() {
        return unpreparedPlayers.isEmpty();
    }

    public Deck getDeck() {
        return this.deck;
    }

    public List<Player> getPlayers() {
        List<Player> result = unpreparedPlayers.unwrap();
        result.addAll(preparedPlayers.unwrap());
        return result;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<BlackJackParticipant> getParticipants() {
        List<BlackJackParticipant> participants = new ArrayList(Arrays.asList(dealer));
        participants.addAll(getPlayers());
        return participants;
    }

    public GameResult getGameResult() {
        return preparedPlayers.match(dealer);
    }
}
