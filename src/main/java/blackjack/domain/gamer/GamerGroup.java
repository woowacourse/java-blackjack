package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.result.GameResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamerGroup {
    private static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Dealer dealer;
    private final PlayerGroup playerGroup;

    public GamerGroup(Dealer dealer, PlayerGroup playerGroup) {
        this.dealer = dealer;
        this.playerGroup = playerGroup;
    }

    public void addInitialCards(CardPack cardPack) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            playerGroup.addCardToAllPlayers(cardPack);
        }
        addInitialDealerCards(cardPack);
    }

    private void addInitialDealerCards(CardPack cardPack) {
        Card card = cardPack.pickOne();
        card.close();
        dealer.addCard(card);
        dealer.addCard(cardPack.pickOne());
    }

    public int addCardToDealer(CardPack cardPack) {
        int addedCardsCount = 0;
        while (dealer.isNotBust()) {
            dealer.addCard(cardPack.pickOne());
            addedCardsCount++;
        }

        return addedCardsCount;
    }

    public void openDealerCards() {
        dealer.openAllCards();
    }

    public GameResult getGameResult() {
        return GameResult.of(dealer, playerGroup);
    }

    public Player findPlayerByName(String name) {
        return playerGroup.findPlayerByName(name);
    }

    public List<Gamer> getGamers() {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(playerGroup.getPlayers());
        return Collections.unmodifiableList(gamers);
    }

    public Dealer getDealer() {
        Dealer copiedDealer = new Dealer();
        List<Card> cards = dealer.getCards();
        for (Card card : cards) {
            copiedDealer.addCard(card);
        }
        return copiedDealer;
    }

    public List<Player> getPlayers() {
        return playerGroup.getPlayers();
    }
}
