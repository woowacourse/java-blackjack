package blackjack.domain;

import blackjack.domain.bet.BetRevenue;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.player.PlayerBetAmount;
import blackjack.domain.player.PlayerBetResult;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerBetAmountDto;
import blackjack.dto.StartCardsDto;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;

    private BlackjackGame(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame from(final List<PlayerBetAmountDto> playerBetAmountDtos) {
        final Dealer dealer = new Dealer(Deck.create());

        final List<PlayerBetAmount> playerBetAmounts = playerBetAmountDtos.stream()
                .map(PlayerBetAmountDto::toDomain)
                .toList();

        return new BlackjackGame(Players.of(playerBetAmounts, dealer), dealer);
    }


    public StartCardsDto getStartCards() {
        final Hands dealerOpenedHands = dealer.getOpenedHands();
        return StartCardsDto.of(players.getPlayersHands(), dealerOpenedHands);
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
}
