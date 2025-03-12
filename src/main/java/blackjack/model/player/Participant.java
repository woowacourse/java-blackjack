package blackjack.model.player;

import blackjack.model.game.BetAmount;
import blackjack.model.game.ParticipantResult;
import java.util.Objects;

public class Participant extends Player {
    private final String name;
    private final BetAmount betAmount;

    public Participant(String name) {
        this(name, new BetAmount(0));
    }

    public Participant(String name, BetAmount betAmount) {
        validate(name);
        this.name = name;
        this.betAmount = betAmount;
    }

    public ParticipantResult duelWith(Dealer dealer) {
        if (this.isBlackJack() && dealer.isBlackJack()) {
            return ParticipantResult.DRAW;
        }
        if (this.isBlackJack()) {
            return ParticipantResult.BLACKJACK;
        }
        if (isBust()) {
            return ParticipantResult.LOSE;
        }
        if (dealer.isBust()) {
            return ParticipantResult.WIN;
        }
        int dealerPoint = dealer.calculatePoint();
        int participantPoint = calculatePoint();
        if (dealerPoint > participantPoint) {
            return ParticipantResult.LOSE;
        }
        if (dealerPoint < participantPoint) {
            return ParticipantResult.WIN;
        }
        return ParticipantResult.DRAW;
    }

    public String getName() {
        return name;
    }

    public int calculateProfitAmount(ParticipantResult participantResult) {
        return betAmount.calculateProfitAmount(participantResult);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참여자 이름을 입력해주세요.");
        }
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("참여자 이름은 2~5글자 입니다.");
        }
    }

    public int getBetAmount() {
        return betAmount.value();
    }
}
