package FuelInjection;

public abstract class Car {
    /**
     * 리터당 이동 거리. 즉, 연비
     */
    abstract double getDistancePerLiter();

    /**
     * 여행하려는 거리
     */
    abstract double getTripDistance();

    /**
     * 차종의 이름
     */
    abstract String getName();

    /**
     * 주입해야할 연료량을 구한다.
<<<<<<< HEAD
     */
=======
    */
>>>>>>> 67807ba0 (feat: 추상클래스 Car 생성)
    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
