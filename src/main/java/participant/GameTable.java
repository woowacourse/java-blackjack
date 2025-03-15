package participant;

import card.Deck;
import card.Hand;
import client.AnswerType;
import java.util.function.Function;
import participant.value.Score;
import result.AllPlayerResult;

public class GameTable {
    private final Deck deck;
    private Dealer dealer;
    private Players players;

    private GameTable(Dealer dealer, Players players) {
        this.deck = new Deck();
        this.dealer = dealer;
        this.players = players;
        initializeHands();
    }

    public static GameTable enter(Dealer dealer, Players players) {
        return new GameTable(dealer, players);
    }

    public Hand openDealerHand() {
        return dealer.openHand();
    }

    public Players openPlayersHand() {
        return players.openHand();
    }

    public void playPlayersRound(Function<Player, AnswerType> requestAdditionalCard) {
        players = players.updatePlayers(player -> {
            AnswerType answerType = requestAdditionalCard.apply(player);

            while (answerType.isPositive() && player.isNotBust()) {
                player = (Player) player.updateHandWith(deck.drawCard());
                answerType = requestAdditionalCard.apply(player);
            }
            return player;
        });
    }

    public void playDealerRound() {
        if (dealer.shouldDrawCard()) {
            dealer = (Dealer) dealer.updateHandWith(deck.drawCard());
        }
    }

    public Hand openDealerFinalHand() {
        return dealer.getHand();
    }

    public Score openDealerFinalScore() {
        return dealer.getScore();
    }

    public Players openPlayersFinalHand() {
        return players;
    }

    public AllPlayerResult calculateAllPlayerResult() {
        return players.calculateAllPlayerResult(dealer);
    }

    private void initializeHands() {
        this.dealer = (Dealer) dealer.initializeHandWith(deck.drawDefaultHand());
        this.players = players.initializeHandWith(deck);
    }
}
