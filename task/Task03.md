# 세 번째 과제
## [키워드]
익명 클래스 / 람다 / 함수형 프로그래밍 / @FunctionalInterface / 스트림 API / 메소드 레퍼런스

## [질문]
### 자바의 람다식은 왜 등장했을까?
람다식이 등장한 이유는 불필요한 코드를 줄이고 가독성을 높이기 위함이다. 

아래의 코드를 보고 비교해보자.

    // 기존의 방식
    반환타입 메소드명 (매개변수, ...) {
        실행문
    }

    // 예시
    public String hello() {
        return "Hello World!"
    }


    // 람다 방식
    (매개변수, ... ) -> { 실행문 ... }

    // 예시
    () -> "Hello World!";

람다식의 등장으로 인해 함수형 프로그래밍 패러다임을 자바에서도 적용할 수 있게 되었다.
###
#### 함수형 프로그래밍이란?
함수형 프로그래밍(functional programming)은 프로그래밍 패러다임 중 하나로, 순수 함수를 기반으로 데이터 처리와 상태 변화를 최소화하는 방식의 프로그래밍 기법이다. 순수 함수(pure function)란 동일한 입력에 대해 항상 같은 결과를 반환하며, 외부 상태를 변경하지 않는 함수를 의미한다. 순수 함수를 이용하면 코드의 복잡성에 따른 부작용(side effects)를 최소화하며, 프로그램의 유지 보수와 테스트를 용이하게 할 수 있다.

###
#### 함수형 프로그래밍과 람다식
람다식(lambda expression)은 함수형 프로그래밍에서 중요한 개념으로, 익명 함수(이름이 없는 함수)를 표현하는 간결한 문법을 의미한다. 람다식은 함수를 일급 객체(first-class object)로 취급하여 다른 함수의 인자로 전달하거나, 함수에서 반환값으로 사용할 수 있다.

#
### 람다식과 익명 클래스는 어떤 관계가 있을까? - 람다식의 문법은 어떻게 될까?
자바 람다식과 함수형 인터페이스를 사용하면 익명 클래스의 선언을 대체할 수 있다. 이를 통해 코드를 더욱 간결하게 만들고, 익명 클래스의 중복 사용에 다른 코드의 중복을 줄일 수 있다. 예를 들어, 아래의 코드처럼 문자열 리스트를 정렬하는 경우, 람다식과 함수형 인터페이스를 사용하여 익명 클래스의 사용을 대체할 수 있다.

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        // 일반적인 방법 : 익명 클래스를 사용하여 정렬
        Collcections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // 람다식과 함수형 인터페이스를 사용한 정렬
        Collections.sort(names, (String o1, String o2) -> o1.compareTo(o2));

###
#### 함수형 인터페이스
함수형 인터페이스는 추상 메서드(abstract method)를 단 하나만 가지고 있는 인터페이스를 의미한다. 자바에서는 @FunctionalInterface 어노테이션으로 함수형 인터페이스임을 표시한다. 만약 @FunctionalInterface 어노테이션을 달고, 추상 메서드를 하나만 가져야 한다는 함수형 인터페이스 조건에 부합하지 않으면 자바에서 에러를 발생시킨다.

함수형 인터페이스는 아래의 코드처럼 자바 람다식을 사용하여 구현할 수 있다.

    // 함수형 인터페이스 예시
    @FunctionalInterface
    interface Greeting {
        void sayHi(String name);
    }

    public class FunctionalInterfaceExample {
        public static void main(String[] args) {
            // 람다식 사용
            Greeting greeting = (String name)
                -> System.out.println("Hi, " + name + "!");

            // 함수형 인터페이스의 메소드를 호출
            greeting.sayHi("Kim");
        }
    }

##
## 추가 조사
### 스트림 API
자바에서는 많은 양의 데이터를 저장하기 위해서 배열이나 컬렉션을 사용한다. 이렇게 저장된 데이터에 접근하기 위해서는 반복문이나 반복자(iterator)를 사용하여 매번 새로운 코드를 작성한다.

하지만 이렇게 작성된 코드는 길이가 너무 길고 가독성도 떨어지며, 코드의 재사용이 거의 불가능하다.

즉, 데이터베이스의 쿼리와 같이 정형화된 처리 패턴을 가지지 못했기에 데이터마다 다른 방법으로 접근해야만 했다.

이러한 문제점을 극복하기 위해서 Java SE 8부터 스트림(stream) API를 도입했다.

스트림 API는 데이터를 추상화하여 다루므로, 다양한 방식으로 저장된 데이터를 읽고 쓰기 위한 공통된 방법을 제공한다.

따라서 스트림 API를 이용하면 배열이나 컬렉션뿐만 아니라 파일에 저장된 데이터도 모두 같은 방법으로 다룰 수 있게 된다.

#### 스트림 API의 특징
- 스트림은 외부 반복을 통해 작업하는 컬렉션과는 달리 내부 반복(internal iteration)을 통해 작업을 수행한다. 
- 스트림은 재사용이 가능한 컬렉션과는 달리 단 한 번만 사용할 수 있다. 
- 스트림은 원본 데이터를 변경하지 않는다. 
- 스트림의 연산은 필터-맵(filter-map) 기반의 API를 사용하여 지연(lazy) 연산을 통해 성능을 최적화한다. 
- 스트림은 parallelStream() 메소드를 통한 손쉬운 병렬 처리를 지원한다.

#### 스트림 API의 동작 흐름
1. 스트림의 생성
2. 스트림의 중개 연산 (스트림의 변환)
3. 스트림의 최종 연산 (스트림의 사용)

###
### 메소드 레퍼런스(method reference)
메소드 참조(method reference)는 람다 표현식이 단 하나의 메소드만을 호출하는 경우에 해당 람다 표현식에서 불필요한 매개변수를 제거하고 사용할 수 있도록 한다.

메소드 참조를 사용하면 불필요한 매개변수를 제거하고 다음과 같이 '::' 기호를 사용하여 표현할 수 있다.

    클래스이름::메소드이름
    참조변수이름::메소드이름

다음 예제는 두 개의 값을 전달받아 제곱 연산을 수행하는 Math 클래스의 클래스 메소드인 pow() 메소드를 호출하는 람다 표현식이다.

    (base, exponent) -> Math.pow(base, exponent);

위의 예제는 단순히 Math 클래스의 pow() 메소드로 인수를 전달하는 역할만 하므로, 메소드 참조를 사용하여 다음과 같이 간단히 표현할 수 있다.

    Math::pow;

또한, 특정 인스턴스의 메소드를 참조할 때에도 참조 변수의 이름을 통해 메소드 참조를 사용할 수 있다.

    MyClass obj = new MyClass;
    Function<String, Boolean> func = (a) -> obj.equals(a); // 람다 표현식
    Function<String, Boolean> func = obj::equals(a);       // 메소드 참조

다음 예제는 람다 표현식과 메소드 참조를 비교하는 예제이다.

    DoubleUnaryOperator oper;
    oper = (n) -> Math.abs(n); // 람다 표현식
    System.out.println(oper.applyAsDouble(-5));     // 5.0

    oper = Math::abs; // 메소드 참조
    System.out.println(oper.applyAsDouble(-5));     // 5.0

#
[참고]

https://itkjspo56.tistory.com/289

https://yozm.wishket.com/magazine/detail/2023/

https://www.tcpschool.com/java/java_stream_concept

https://www.tcpschool.com/java/java_lambda_reference