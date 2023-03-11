package blackjack.domain.player;

import blackjack.dto.ChallengerNameAndMoneyDto;

public class Challenger extends Player {

    private static final int MAXIMUM_POINT_TO_PICK = 21;

    private final ChallengerName name;
    private final Money money;

    public Challenger(final ChallengerNameAndMoneyDto challengerNameAndMoneyDto) {
        this.name = challengerNameAndMoneyDto.getChallengerName();
        this.money = challengerNameAndMoneyDto.getMoney();
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public Boolean canPick() {
        Score score = holdingCards.getDefaultSum();
        return score.getValue() <= MAXIMUM_POINT_TO_PICK;
    }

    @Override
    public Boolean isDealer() {
        return false;
    }

    @Override
    public Boolean isChallenger() {
        return true;
    }

    @Override
    public String getName() {
        return name.getName();
    }
}
