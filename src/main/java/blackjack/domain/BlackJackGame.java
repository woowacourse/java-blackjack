package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Nickname;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.ProfitResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(List<String> playersName) {
        players = generatePlayers(playersName);
        players.bettingEachPlayer();
        dealer = new Dealer();
        List<Card> cards = new ArrayList<>(Card.values());
        Collections.shuffle(cards);
        deck = new Deck(cards);
    }

    private Players generatePlayers(List<String> allPlayersName) {
        return new Players(allPlayersName.stream()
                .map(Nickname::new)
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public void distributeCards() {
        dealer.firstDraw(deck.drawCard(), deck.drawCard());
        players.eachPlayerFirstDraw(deck);
    }

    public Card drawOneCard() {
        return deck.drawCard();
    }

    public ProfitResult profitResult() {
        ProfitResult profitResult = new ProfitResult();
        profitResult.calculateProfit(players.verifyResultByCompareScore(dealer), dealer);
        return profitResult;
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
