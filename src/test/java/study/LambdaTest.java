package study;

import fuel.Car;
import fuel.Sonata;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdaTest {

    @DisplayName("내부 로컬클래스는 build/classes에 별도의 클래스를 생성한다. (IntelliJ 내부에서는 숨겨져 있음)")
    @Test
    void localInnerClassTest() {
        class InnerCar extends Car {
            protected InnerCar(String name, int fuelEfficiency, int distance) {
                super(name, fuelEfficiency, distance);
            }
        }

        // System.out.println(InnerCar.class);
        // class study.LambdaTest$1InnerCar
    }

    @DisplayName("익명클래스는 build/classes에 별도의 클래스를 생성한다. (IntelliJ 내부에서는 숨겨져 있음)")
    @Test
    void anonymousClassTest() {
        Car anonymousClass = new Car("익명클래스1", 10, 10) {
            /* nothing to implement */
        };

        // System.out.println(anonymousClass.getClass());
        // class study.LambdaTest$1
    }

    @DisplayName("람다는 익명클래스와 같지만 별도의 클래스 파일을 생성하지는 않는다.")
    @Test
    void lambdaTest() {
        Runnable lambda = () -> new Sonata(10);

        // System.out.println(lambda.getClass());
        // class study.LambdaTest$$Lambda$336/0x0000000800174040
    }
}
