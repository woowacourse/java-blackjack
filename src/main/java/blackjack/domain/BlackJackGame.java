package blackjack.domain;

import blackjack.controller.DrawOrStay;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.money.Deposit;
import blackjack.domain.money.Money;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.WinningStatus;
import blackjack.domain.user.Name;
import blackjack.domain.user.Users;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    //TODO: deposit과 Deck 합치기
    private final Deposit deposit;
    private final Deck deck;
    private final Users users;

    public BlackJackGame(final List<String> playerNames, final DeckGenerator deckGenerator) {
        this.deck = new Deck(deckGenerator);
        this.users = new Users(playerNames, deck);
        this.deposit = new Deposit();
    }

    public CardGroup getCardGroupBy(final Name userName) {
        return users.getCardGroupBy(userName);
    }

    public Map<Name, CardGroup> getUserNameAndFirstOpenCardGroups() {
        return users.getUserNameAndFirstOpenCardGroups();
    }

    public void betPlayer(final Name playerName, final int betMoney) {
        deposit.betting(playerName, new Money(betMoney));
    }

    public List<Name> getPlayerNames() {
        return users.getPlayerNames();
    }

    public boolean isContinuous(final Name playerName) {
        if (users.isBlackJackScore(playerName) || users.isPlayerBust(playerName)) {
            return false;
        }
        return true;
    }

    public void drawDealer() {
        users.drawDealer(deck);
    }

    public boolean shouldDealerDraw() {
        return users.isDealerUnderDrawLimit();
    }

    public void playPlayer(final Name userName, final DrawOrStay drawOrStay) {
        if (drawOrStay.isDraw()) {
            users.drawCard(userName, deck);
        }
    }

    public Map<Name, CardResult> getUserNameAndCardResults() {
        return users.getUserNameAndCardResults();
    }

    public Map<Name, WinningStatus> getPlayersWinningResults() {
        return users.getPlayersWinningResults();
    }

    public Map<WinningStatus, Long> getDealerWinningResult() {
        return users.getDealerWinningResults();
    }

}
