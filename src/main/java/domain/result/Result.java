package domain.result;

import domain.WinningStatus;
import domain.betting.Betting;

public record Result(Betting betting, WinningStatus winningStatus) {

}
