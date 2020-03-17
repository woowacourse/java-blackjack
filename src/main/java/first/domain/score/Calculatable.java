package first.domain.score;

public interface Calculatable {
     Calculatable plus(Calculatable calculatable);

     boolean isLargerThan(Calculatable calculatable);

     boolean isLargerOrEqualThan(Calculatable calculatable);

     int getValue();
}
