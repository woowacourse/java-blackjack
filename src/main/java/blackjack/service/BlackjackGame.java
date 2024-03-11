package blackjack.service;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Players;
import blackjack.domain.rule.GameRule;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerCardsDto;
import blackjack.dto.StartCardsDto;
import blackjack.dto.WinningResultDto;
import blackjack.exception.NeedRetryException;
import java.util.List;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final List<String> playersName) {
        this.players = Players.from(playersName);
        this.dealer = new Dealer(Deck.create());

        validateDateDealerName();
    }

    private void validateDateDealerName() {
        final ParticipantName dealerName = dealer.getName();

        if (players.hasName(dealerName)) {
            throw new NeedRetryException(dealerName.getName() + "를 이름으로 사용할 수 없습니다.");
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
        return StartCardsDto.of(players.getPlayersHands(), dealerOpenedHands, dealer.getName());
    }

    public boolean isNotDealerBlackjack() {
        return dealer.isNotBlackjack();
    }

    public boolean isPlayerAliveByName(final String name) {
        return players.isNotDead(name);
    }

    public void addCardToPlayers(final String name) {
        players.addCardTo(name, dealer.drawCard());
    }

    public int giveDealerMoreCard() {
        if (players.isAllDead()) {
            return 0;
        }
        int count = 0;

        while (dealer.needMoreCard()) {
            dealer.addCard();
            count++;
        }

        return count;
    }

    public PlayerCardsDto getCardsOf(final String name) {
        final Hands hands = players.getHandsOf(name);
        return PlayerCardsDto.of(name, hands);
    }

    public FinalHandsScoreDto getFinalHandsScore() {
        return FinalHandsScoreDto.of(players.getPlayersHands(), dealer);
    }

    public WinningResultDto getWinningResults() {
//        final BattingResult battingResult = BattingResult.of(players, dealer.getHands());
//        final Map<ParticipantName, BattingStatus> playerWinningResults = battingResult.getParticipantsWinStatus();
//        final Map<BattingStatus, Long> dealerWinningResult = battingResult.summarizeDealerWinningResult();

//        return WinningResultDto.of(playerWinningResults, dealerWinningResult, dealer.getName());
        return null;
    }

    public List<String> getPlayersName() {
        return players.getNames().stream()
                .map(ParticipantName::getName)
                .toList();
    }

    public String getDealerName() {
        return dealer.getName().getName();
    }
}
