package blackjack.domain.result;

import blackjack.domain.score.Score;

interface MoneyRateStrategy {
    double getRate(Score score);
}
