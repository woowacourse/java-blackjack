package blackjack.controller;

import blackjack.domain.GameManager;
import blackjack.domain.card.CardPack;
import blackjack.domain.card.RandomBlackjackShuffle;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Players;

import java.util.List;
import java.util.Map;

public class GameManagerFactory {

    public GameManager createGameManager(Map<String, Integer> gamblerBets) {
        List<Gambler> gamblers = crateGamblers(gamblerBets);
        Dealer dealer = new Dealer();
        Players players = new Players(dealer, gamblers);

        CardPack cardPack = new CardPack(new RandomBlackjackShuffle());
        return new GameManager(cardPack, players);
    }

    private static List<Gambler> crateGamblers(final Map<String, Integer> gamblerBets) {
        return gamblerBets.entrySet().stream()
                .map(entry -> new Gambler(entry.getKey(), entry.getValue()))
                .toList();
    }
}
