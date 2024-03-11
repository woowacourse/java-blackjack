package blackjack.service;

import blackjack.domain.player.BetResult;
import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.rule.GameRule;
import blackjack.dto.DealerMoreCardDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerCardsDto;
import blackjack.dto.StartCardsDto;
import blackjack.dto.BetRevenueResultDto;
import blackjack.exception.NeedRetryException;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final List<String> playersName) {
        this.players = Players.from(playersName);
        this.dealer = new Dealer(Deck.create());

        validateDateDealerName();
    }

    private void validateDateDealerName() {
        if (players.hasName(Dealer.DEALER_NAME)) {
            throw new NeedRetryException(Dealer.DEALER_NAME + "를 이름으로 사용할 수 없습니다.");
        }
    }

    public StartCardsDto start() {
        dealer.addCard(GameRule.START_CARD_COUNT);

        final List<List<Card>> cards = dealer.drawCards(players.count(), GameRule.START_CARD_COUNT);
        players.divideCard(cards);

        return getStartCards();
    }

    private StartCardsDto getStartCards() {
        final Hands dealerOpenedHands = dealer.getOpenedHands();
        return StartCardsDto.of(players.getPlayersHands(), dealerOpenedHands, new PlayerName(Dealer.DEALER_NAME));
    }

    public void saveBetAmountByName(final int betAmount, final String name) {
        players.saveBetAmountByName(betAmount, name);
    }

    public void addCardToPlayers(final String name) {
        players.addCardTo(name, dealer.drawCard());
    }

    public DealerMoreCardDto giveDealerMoreCard() {
        if (players.isAllDead()) {
            return DealerMoreCardDto.of(Dealer.DEALER_NAME, 0, Dealer.NEED_CARD_NUMBER_MAX);
        }
        int count = 0;

        while (dealer.needMoreCard()) {
            dealer.addCard();
            count++;
        }

        return DealerMoreCardDto.of(Dealer.DEALER_NAME, count, Dealer.NEED_CARD_NUMBER_MAX);
    }

    public boolean isNotDealerBlackjack() {
        return dealer.isNotBlackjack();
    }

    public boolean isPlayerAliveByName(final String name) {
        return players.isNotDead(name);
    }

    public FinalHandsScoreDto getFinalHandsScore() {
        return FinalHandsScoreDto.of(players.getPlayersHands(), dealer);
    }

    public BetRevenueResultDto getBetRevenueResults() {
        final Map<PlayerName, BetRevenue> playersBetResult = players.determineBetRevenue(dealer.getHands());
        final BetResult betResult = new BetResult(playersBetResult);
        final BetRevenue dealerRevenue = betResult.calculateDealerRevenue();

        return BetRevenueResultDto.of(playersBetResult, dealerRevenue, Dealer.DEALER_NAME);
    }

    public PlayerCardsDto getCardsOf(final String name) {
        final Hands hands = players.getHandsOf(name);
        return PlayerCardsDto.of(name, hands);
    }

    public List<String> getPlayersName() {
        return players.getNames().stream()
                .map(PlayerName::getName)
                .toList();
    }
}
