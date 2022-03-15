package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.domain.result.ResultStrategy;
import blackjack.domain.result.RuleResult;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Player dealer;
    private final Gamers gamers;

    public BlackJackGame(final Player dealer, final Gamers gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public void handOutStartingCards(final Deck deck) {
        dealStartingCards(dealer, deck);
        for (Gamer gamer : gamers.getGamers()) {
            dealStartingCards(gamer, deck);
            gamer.calculateCurrentBet(RuleResult.findBlackJackRule(gamer)
                    .getResult());
        }
    }

    private void dealStartingCards(final Player player, final Deck deck) {
        for (int i = 0; i < Card.START_CARD_COUNT; i++) {
            player.receiveCard(deck.draw());
        }
    }

    public Map<Player, ResultStrategy> calculateResultBoard() {
        return gamers.compareResult(dealer.calculateResult());
    }

    public int calculateDealerResultBoard(final Map<Player, Bet> resultBoard) {
        return -resultBoard.values()
                .stream()
                .mapToInt(Bet::getAmount)
                .sum();
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Gamer> getGamers() {
        return gamers.getGamers();
    }
}
