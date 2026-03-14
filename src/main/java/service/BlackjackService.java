package service;

import domain.BettingTable;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.player.Player;
import domain.vo.Money;
import dto.DealerInfoDto;

public class BlackjackService {
    private static final int START_CARD_COUNT = 2;
    private final Dealer dealer = new Dealer(new HandCards());
    private final CardDeck cardDeck = new CardDeck();
    private final BettingTable bettingTable = new BettingTable();

    public void placeBet(Player player, Money money) {
        bettingTable.placeBet(player, money);
    }

    public void dealDealerCardsOut() {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            dealer.drawCard(cardDeck.getCard());
        }
    }

    public void dealPlayerCardsOut(Player player) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            player.drawCard(cardDeck.getCard());
        }
    }

    public void playerHit(Player player) {
        player.drawCard(cardDeck.getCard());
    }

    public boolean isDealerHit() {
        if (dealer.isDealerHit()) {
            dealer.drawCard(cardDeck.getCard());
            return true;
        }
        return false;
    }

    public DealerInfoDto getDealerInfo() {
        return new DealerInfoDto(dealer.getCards(), dealer.getScore(), dealer.getRecord());
    }

    public void finalizeGameResult(Player player) {
        player.calculateResult(dealer);
        dealer.saveResult(player.getWinStatus());
    }

    public Money getProfit(Player player) {
        return bettingTable.calculateProfit(player);
    }

    public Money getDealerProfit() {
        return bettingTable.getDealerProfit();
    }
}
