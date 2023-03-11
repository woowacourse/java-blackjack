package blackjack.domain.game;

import blackjack.domain.Money;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.ParticipantResultDto;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.dealer.DealerFirstCardDto;
import blackjack.domain.participant.player.CardDecisionStrategy;
import blackjack.domain.participant.player.CardDisplayStrategy;
import blackjack.domain.participant.player.Player;
import blackjack.domain.participant.player.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void addPlayer(Player player) {
        players = players.add(player);
    }

    public void supplyCardsToDealer() {
        dealer.hit(deck.drawCard());
        dealer.hit(deck.drawCard());
    }

    public void supplyCardsToPlayers() {
        players.hitFirstCards(deck);
    }

    public void supplyAdditionalCardToPlayersBy(CardDecisionStrategy cardDecisionStrategy,
                                                CardDisplayStrategy cardDisplayStrategy) {
        players.hitAdditionalCard(deck, cardDecisionStrategy, cardDisplayStrategy);
    }

    public void supplyAdditionalCardToDealerAnd(CardDisplayStrategy dealerDisplayStrategy) {
        while (dealer.isUnderScore()) {
            dealer.hit(deck.drawCard());
            dealerDisplayStrategy.display(dealer);
        }
    }

    public ParticipantResultDto getDealerResult() {
        return ParticipantResultDto.from(dealer);
    }

    public List<ParticipantResultDto> getPlayerResults() {
        return players.getPlayerResults();
    }

    public GameResultDto calculateWinning() {
        Map<Player, WinningResult> playerToResult = players.calculateWinning(dealer);
        return GameResultDto.of(playerToResult);
    }

    public DealerFirstCardDto getDealerFirstOpen() {
        return DealerFirstCardDto.from(dealer);
    }

    public List<ParticipantCardsDto> getPlayersCards() {
        return players.getPlayerCards();
    }

    private static Money calculateDealerPrize(Map<Player, Money> playerMoneyMap) {
        int sumOfPlayerPrize = playerMoneyMap.values().stream().mapToInt(Money::getValue).sum();
        return new Money(sumOfPlayerPrize)
                .product(-1);
    }

    public Map<Participant, Money> calculatePrize() {
        Map<Player, Money> playerMoneyMap = players.calculateEachPrize(dealer);
        Money dealerPrize = calculateDealerPrize(playerMoneyMap);
        Map<Participant, Money> prize = new HashMap<>();
        prize.put(dealer, dealerPrize);
        prize.putAll(playerMoneyMap);
        return prize;
    }
}
