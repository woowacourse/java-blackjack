package blackjack.domain;

import java.util.List;

import blackjack.domain.card.CardPack;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.GameResult;

public class BlackJack {
    private final Gamers gamers;
    private final CardPack cardPack;

    public BlackJack(PlayerGroup playerGroup) {
        this.gamers = new Gamers(new Dealer(), playerGroup);
        this.cardPack = new CardPack();
    }

    public void divideCards() {
        gamers.addInitialCards(cardPack);
    }

    public void addCardTo(Gamer gamer) {
        gamer.addCard(cardPack.pickOne());
    }

    public int playDealer() {
        return gamers.addCardsToDealer(cardPack);
    }

    public void openDealerCards() {
        gamers.openDealerCards();
    }

    public GameResult getGameResult() {
        return gamers.getGameResult();
    }

    public List<Gamer> getGamers() {
        return gamers.getGamers();
    }

    public Dealer getDealer() {
        return gamers.getDealer();
    }
}
