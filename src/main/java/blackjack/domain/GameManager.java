package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    public Dealer inviteDealer() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.initCardDeck();
        return dealer;
    }

    public Players invitePlayers(final List<String> playerNames, final Dealer dealer) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            CardDeck cardDeck = dealer.createCardDeck();
            Player player = new Player(playerName);
            player.initCardDeck(cardDeck);
            players.add(player);
        }
        return new Players(players);
    }

    public Map<GameResult, Integer> generateDealerFinalResult(final Dealer dealer, final Players players) {
        FinalResult finalResult = new FinalResult();
        return finalResult.createDealerFinalResult(dealer, players.members());
    }

    public Map<Player, GameResult> generatePlayersFinalResult(final Dealer dealer, final Players players) {
        Map<Player, GameResult> playersFinalResult = new HashMap<>();

        for (Player player : players.members()) {
            FinalResult finalResult = new FinalResult();
            finalResult.createPlayerFinalResult(player, dealer);

            playersFinalResult.put(player, finalResult.createPlayerFinalResult(player, dealer));
        }
        return playersFinalResult;
    }
}
