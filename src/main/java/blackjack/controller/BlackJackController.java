package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void play() {
        CardDeck entireCardDeck = new CardDeck();
        Dealer dealer = new Dealer(entireCardDeck.generateUserDeck());
        Players players = new Players(entireCardDeck, InputView.requestPlayers());
        OutputView.showInitiate(dealer, players);
    }
}
