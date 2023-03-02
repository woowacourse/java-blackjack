package blackjack.domain;

import blackjack.domain.card.Cards;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void giveFirstCards() {
        prepareCards();
        for (Player player : players.getPlayers()) {
            player.playerCards.updateCardScore(Cards.giveFirstCard());
            player.playerCards.updateCardScore(Cards.giveFirstCard());
        }
        dealer.playerCards.updateCardScore(Cards.giveFirstCard());
        dealer.playerCards.updateCardScore(Cards.giveFirstCard());
    }

    // TODO : 컨트롤러에서 할까..? 서비스에서 할까..
    public void prepareCards() {
        Cards.init();
    }

}
