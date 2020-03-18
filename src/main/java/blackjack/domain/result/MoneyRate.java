package blackjack.domain.result;

import blackjack.domain.score.Score;

interface MoneyRate {
    double getRate(Score score);
}
