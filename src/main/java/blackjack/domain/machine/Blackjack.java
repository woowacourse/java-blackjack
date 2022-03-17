package blackjack.domain.machine;

import blackjack.domain.card.CardPickMachine;
import blackjack.domain.dto.ResultDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.strategy.NumberGenerator;

public class Blackjack {
    private static final int NUMBER_OF_INIT_CARD = 2;

    private final CardPickMachine cardPickMachine;

    public Blackjack() {
        this.cardPickMachine = new CardPickMachine();
    }

    public void dealInitialCards(NumberGenerator numberGenerator, Dealer dealer, Players players) {
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
}
