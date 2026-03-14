package blackjack.core;

import blackjack.domain.card.CardsGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerGroup;
import blackjack.domain.result.GameReferee;
import blackjack.domain.result.GameResult;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final GameReferee referee;
    private final PlayerGroup playerGroup;

    public BlackjackGame(Deck deck, Dealer dealer, GameReferee referee, PlayerGroup playerGroup) {
        this.deck = deck;
        this.dealer = dealer;
        this.referee = referee;
        this.playerGroup = playerGroup;
    }

    public static BlackjackGame create(
        CardsGenerator cardsGenerator, GameReferee referee, String playerNames) {

        return new BlackjackGame(
            Deck.create(cardsGenerator),
            Dealer.create(),
            referee,
            PlayerGroup.from(playerNames));
    }

    public List<Player> getPlayers() {
        return playerGroup.players();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void initialDeal() {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            hit(dealer);
            playerGroup.deal(deck);
        }
    }

    public void hit(Participant participant) {
        participant.hit(deck.draw());
    }

    public int playDealerTurn() {
        int hitCount = 0;
        while (dealer.canHit()) {
            hit(dealer);
            hitCount++;
        }
        return hitCount;
    }

    public GameResult judge(Player player) {
        return referee.judge(dealer, player);
    }
}
