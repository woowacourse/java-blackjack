package blackjack.domain;

import java.util.List;

import blackjack.domain.card.CardPack;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.GamerGroup;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.GameResult;

public class BlackJack {
    private final GamerGroup gamerGroup;
    private final CardPack cardPack;

    public BlackJack(PlayerGroup playerGroup) {
        this.gamerGroup = new GamerGroup(new Dealer(), playerGroup);
        this.cardPack = new CardPack();
    }

    public void divideCards() {
        gamerGroup.addInitialCards(cardPack);
    }

    public void addCardTo(Gamer gamer) {
        gamer.addCard(cardPack.pickOne());
    }

    public int playDealer() {
        return gamerGroup.addCardsToDealer(cardPack);
    }

    public void openDealerCards() {
        gamerGroup.openDealerCards();
    }

    public GameResult getGameResult() {
        return gamerGroup.getGameResult();
    }

    public List<Gamer> getGamers() {
        return gamerGroup.getGamers();
    }

    public Dealer getDealer() {
        return gamerGroup.getDealer();
    }
}
