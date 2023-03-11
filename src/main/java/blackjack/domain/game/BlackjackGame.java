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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<ParticipantPrizeDto> calculatePrize() {
        Map<Player, Money> playerMoneyMap = players.calculateEachPrize(dealer);
        Map<Participant, Money> prize = new LinkedHashMap<>();
        prize.put(dealer, calculateDealerPrize(playerMoneyMap));
        prize.putAll(playerMoneyMap);

        return prize.entrySet().stream()
                .map(ParticipantPrizeDto::of)
                .collect(Collectors.toUnmodifiableList());
    }

    private Money calculateDealerPrize(Map<Player, Money> playerMoneyMap) {
        int sumOfPlayerPrize = playerMoneyMap.values().stream()
                .mapToInt(Money::getValue)
                .sum();
        return new Money(sumOfPlayerPrize)
                .product(-1);
    }

    public ParticipantResultDto getDealerResult() {
        return ParticipantResultDto.from(dealer);
    }

    public List<ParticipantResultDto> getPlayerResults() {
        return players.getPlayerResults();
    }

    public DealerFirstCardDto getDealerFirstOpen() {
        return DealerFirstCardDto.from(dealer);
    }

    public List<ParticipantCardsDto> getPlayersCards() {
        return players.getPlayerCards();
    }

}
