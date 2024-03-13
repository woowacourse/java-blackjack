package blackjack.domain;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.player.PlayerBetResult;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetRevenue;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.StartCardsDto;
import blackjack.exception.NeedRetryException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final List<PlayerName> playersName) {
        this.dealer = new Dealer(Deck.create());
        this.players = Players.of(playersName, dealer);

        validateDateDealerName();
    }

    private void validateDateDealerName() {
        if (players.hasName(Dealer.DEALER_NAME)) {
            throw new NeedRetryException(Dealer.DEALER_NAME + "를 이름으로 사용할 수 없습니다.");
        }
    }

    public StartCardsDto getStartCards() {
        final Hands dealerOpenedHands = dealer.getOpenedHands();
        return StartCardsDto.of(players.getPlayersHands(), dealerOpenedHands);
    }

    public void saveBetAmount(final Map<PlayerName, BetAmount> playerBetAmounts) {
        players.saveBetAmount(playerBetAmounts);
    }

    public void playGame(
            final Predicate<PlayerName> isHitInput,
            final BiConsumer<PlayerName, Hands> playerHitConsumer,
            final IntConsumer dealerHitConsumer
    ) {
        if (dealer.isNotBlackjack()) {
            players.hit(dealer, isHitInput, playerHitConsumer);
        }

        runDealerTurn(dealerHitConsumer);
    }

    private void runDealerTurn(final IntConsumer dealerHitConsumer) {
        if (players.isAllBurst()) {
            dealerHitConsumer.accept(0);
            return;
        }

        final int count = dealer.hit();
        dealerHitConsumer.accept(count);
    }

    public FinalHandsScoreDto getFinalHandsScore() {
        return FinalHandsScoreDto.of(players.getPlayersHands(), dealer.getHands());
    }

    public BetRevenueResultDto getBetRevenueResults() {
        final Map<PlayerName, BetRevenue> playersBetResult = players.determineBetRevenue(dealer);
        final PlayerBetResult playerBetResult = new PlayerBetResult(playersBetResult);

        final BetRevenue dealerRevenue = playerBetResult.calculateDealerRevenue();

        return BetRevenueResultDto.of(playersBetResult, dealerRevenue);
    }

    public List<PlayerName> getPlayersName() {
        return players.getNames();
    }
}
