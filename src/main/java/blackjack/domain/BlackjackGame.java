package blackjack.domain;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.PlayerNameProfitRates;
import blackjack.domain.result.UserNameProfits;
import blackjack.domain.user.Name;
import blackjack.domain.user.PlayerName;
import blackjack.domain.user.Users;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BlackjackGame {

    private final Users users;
    private final GameTable gameTable;

    public BlackjackGame(final List<String> playerNames, final DeckGenerator deckGenerator) {
        final Deck deck = new Deck(deckGenerator);
        this.gameTable = new GameTable(deck);
        final Queue<CardGroup> cardGroups = generateFirstCardGroup(playerNames.size(), deck);
        this.users = new Users(playerNames, cardGroups);
    }

    private Queue<CardGroup> generateFirstCardGroup(final int firstCardGroupsCount, final Deck deck) {
        final Queue<CardGroup> firstCardGroup = new LinkedList<>();
        for (int index = 0; index <= firstCardGroupsCount; index++) {
            firstCardGroup.add(new CardGroup(deck.draw(), deck.draw()));
        }
        return firstCardGroup;
    }

    public CardGroup getCardGroupBy(final PlayerName playerName) {
        return users.getCardGroupBy(playerName);
    }

    public Map<Name, CardGroup> getUserNameAndFirstOpenCardGroups() {
        return users.getUserNameAndFirstOpenCardGroups();
    }

    public void betPlayer(final PlayerName playerName, final int bettingMoney) {
        gameTable.bet(playerName, new BettingMoney(bettingMoney));
    }

    public List<PlayerName> getPlayerNames() {
        return users.getPlayerNames();
    }

    public boolean isContinuous(final PlayerName playerName) {
        return users.isDrawable(playerName);
    }

    public int drawDealerUntilUnderLimit() {
        int dealerDrawCount = 0;
        while (users.isDealerUnderDrawLimit()) {
            users.drawDealer(gameTable.supplyCard());
            dealerDrawCount++;
        }
        return dealerDrawCount;
    }

    public boolean playPlayer(final PlayerName playerName, final DrawOrStay drawOrStay) {
        if (drawOrStay.isDraw()) {
            users.drawCard(playerName, gameTable.supplyCard());
        }
        return drawOrStay.isDraw();
    }

    public UserNameProfits getUserNameAndProfits() {
        final PlayerNameProfitRates playerNameAndProfitRates = users.getPlayerNameAndProfitRates();
        return gameTable.calculateProfits(playerNameAndProfitRates);
    }

    public Map<Name, CardResult> getUserNameAndCardResults() {
        return users.getUserNameAndCardResults();
    }
}
