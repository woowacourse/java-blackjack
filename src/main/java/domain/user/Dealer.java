package domain.user;

import domain.card.Card;
import domain.dto.UserDto;
import domain.state.DealerReady;
import exception.DealerHasNoProfitRatioException;

import java.util.List;

public final class Dealer extends User {
    public Dealer() {
        super(new Name("딜러"), new DealerReady());
    }

    @Override
    public final double getProfitRatio() {
        throw new DealerHasNoProfitRatioException();
    }

    public int drawCount() {
        return getCards().size() - 2;
    }

    public List<Card> getOnlyFirstCard() {
        return getCards().subList(0, 1);
    }

    public UserDto getDealerSetUpDto() {
        return new UserDto(getName(), getScore(), getOnlyFirstCard());
    }
}
