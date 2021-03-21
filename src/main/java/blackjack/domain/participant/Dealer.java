package blackjack.domain.participant;

import blackjack.controller.dto.GameResultDto;
import blackjack.domain.GameResult;
import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static blackjack.domain.GameResult.*;

public class Dealer extends Participant {

    private static final int INIT_HAND_COUNT = 2;

    private final CardDeck cardDeck;

    public Dealer() {
        super(new Name("딜러"), Money.of(1));
        this.cardDeck = CardDeck.newShuffledDeck();
    }

    public Card drawCard() {
        try {
            return cardDeck.draw();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("더이상 뽑을 카드가 없습니다.");
        }
    }

    public List<Card> drawCards() {
        return Stream.generate(this::drawCard)
                .limit(INIT_HAND_COUNT)
                .collect(Collectors.toList());
    }

    public void initHand(Players players) {
        for (Player player : players.toList()) {
            player.receiveFirstHand(drawCards());
        }
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore().isDealerStateStay();
    }

    @Override
    public void receiveCard(final Card card) {
        this.state = this.state.receiveCard(card);
        if (isOverLimitScore() && !isFinished()) {
            this.stay();
        }
    }

    public GameResult judgePlayer(final Player player) {
        if (didLose(player)) {
            return LOSE;
        }
        if (didWin(player)) {
            return WIN;
        }
        return TIE;
    }

    private boolean didWin(final Player player) {
        return (player.isBlackjack() && !this.isBlackjack()) || (this.isBust() && player.isStay()) ||
                (player.isStay() && this.isStay() && player.isHigherThan(this));
    }

    private boolean didLose(final Player player) {
        return player.isBust() || (this.isBlackjack() && !player.isBlackjack()) ||
                (player.isStay() && this.isStay() && this.isHigherThan(player));
    }

    public List<GameResultDto> getPlayersResult(final Players players) {
        return players
                .map(player -> new GameResultDto(player.getName(), GameResult.calculateEarning(this, player)))
                .collect(Collectors.toList());
    }

    public GameResultDto getDealerResult(final List<GameResultDto> playerResults) {
        double dealerEarning = playerResults.stream()
                .mapToDouble(GameResultDto::getEarning)
                .reduce(0.0, (a, b) -> a + (b * -1));
        return new GameResultDto(this.getName(), dealerEarning);
    }
}
