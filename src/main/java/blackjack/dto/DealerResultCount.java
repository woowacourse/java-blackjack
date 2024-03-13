package blackjack.dto;

import blackjack.model.result.ResultCommand;

public record DealerResultCount(ResultCommand result, int count) {

}
