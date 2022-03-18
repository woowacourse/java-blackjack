package blackjack.domain.machine;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPickMachine;
import blackjack.domain.dto.ResultDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.strategy.NumberGenerator;

public class Blackjack {
    private static final int NUMBER_OF_INIT_CARD = 2;

    private final CardPickMachine cardPickMachine;

    private Blackjack(NumberGenerator numberGenerator, Dealer dealer, Players players) {
        this.cardPickMachine = new CardPickMachine();
        dealInitialCards(numberGenerator, dealer, players);
    }

    public static Blackjack of(NumberGenerator numberGenerator, Dealer dealer, Players players) {
        return new Blackjack(numberGenerator, dealer, players);
    }

    private void dealInitialCards(NumberGenerator numberGenerator, Dealer dealer, Players players) {
        for (int i = 0; i < NUMBER_OF_INIT_CARD; ++i) {
            dealer.addCard(cardPickMachine.pickCard(numberGenerator));
            players.addCards(cardPickMachine, numberGenerator);
        }
    }

    public void dealAdditionalCardToPlayer(NumberGenerator numberGenerator, Player player) {
        player.addCard(cardPickMachine.pickCard(numberGenerator));
    }

    public void dealAdditionalCardToDealer(NumberGenerator numberGenerator, Dealer dealer) {
        dealer.addCard(cardPickMachine.pickCard(numberGenerator));
    }

    public ResultDto result(Dealer dealer, Players players) {
        return Records.of(dealer, players.getPlayers());
    }

    public ProfitResult profitResult(Dealer dealer, Players players) {
        return ProfitResult.of(dealer, players);
    }
}
