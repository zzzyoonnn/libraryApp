# 첫 번째 과제
## [질문]
### 어노테이션을 사용하는 이유(효과)는 무엇일까?
<span style="color:red">어노테이션의 의미는 ‘주석’이다.</span> 주석과는 역할이 다르지만, <span style="color:blue"> 주석처럼 달아 특수한 의미 부여가 가능하며, 기능 주입이 가능하다.</span> 그렇기에 <span style="background-color:#fff5b1">어노테이션은 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시키기 위해 작성한다.</span>

기존의 자바 웹 어플리케이션은 구성과 설정값들을 외부 XML설정 파일에 명시하여 프로그래밍 되었다. 외부에서 변경될 수 있는 값을 관리하기에 재컴파일 없이 쉽게 변경사항을 적용할 수 있었지만, 프로그램의 규모가 커질수록 설정을 다 적어줘야했고, 프로그램을 작성할때 마다 많은 설정을 작성해야했기에 문제점이 있었다. 

이를 해결하기 위해 고안된 문법이 어노테이션이다. 어노테이션은 런타임 또는 컴파일에 해석될 수 있다. 어노테이션은 보통 문서화, 컴파일러 체크, 코드 분석을 위한 용도로 사용되는데 본질적인 목적은 <span style="background-color:#fff5b1">프로그램에게 추가 정보를 제공하는 메타 데이터를 위해서 사용</span>한다.

메타 데이터 어노테이션이란 어노테이션에 사용되는 어노테이션으로 해당 어노테이션의 동작대상을 결정한다. 주로 새로운 어노테이션을 정의할 때 사용한다.


#
### 나만의 어노테이션은 어떻게 만들 수 있을까?
<span style="color:red">커스텀 어노테이션은 메타 어노테이션을 사용</span>하여 다음과 같은 구조를 가진다.

<span style="background-color:#fff5b1">메타 어노테이션이란 커스텀 어노테이션을 구성할 때 시점, 위치등을 지정하기 위한 어노테이션이다.</span>
어노테이션의 필드 타입은 enum, String이나 기본 자료형, 기본 자료형의 배열만 사용할 수 있다.

    @Target({ElementType.[적용대상]})
    @Retention(RetentionPolicy.[정보유지되는 대상])
    public @interface [어노테이션명]{
    public 타입 elementName() [default 값]
    ...
    }

메타 어노테이션의 종류는 다음과 같다.
##### @Retention : 컴파일러가 어노테이션을 다루는 방법을 기술, 어느 시점까지 영향을 미치는지를 결정
- RetentionPolicy.SOURCE : 컴파일 전까지만 유효
- RetentionPolicy.CLASS : 컴파일러가 클래스를 참조할 때까지 유효
- RetentionPolicy.RUNTIME : 컴파일 이후 런타임 시기에도 JVM에 의해 참조가 가능(리플렉션)

##### @Target : 어노테이션 적용할 위치 선택
- ElementType.PACKAGE : 패키지 선언
- ElementType.TYPE : 타입 선언
- ElementType.ANNOTATION_TYPE : 어노테이션 타입 선언
- ElementType.CONSTRUCTOR : 생성자 선언
- ElementType.FIELD : 멤버 변수 선언
- ElementType.LOCAL_VARIABLE : 지역 변수 선언
- ElementType.METHOD : 메서드 선언
- ElementType.PARAMETER : 전달인자 선언
- ElementType.TYPE_PARAMETER : 전달인자 타입 선언
- ElementType.TYPE_USE : 타입 선언

##### @Documented : 해당 어노테이션을 Javadoc에 포함시킴
##### @Inherited : 어노테이션의 상속을 가능하게 함
##### @Repeatable : Java8 부터 지원하며, 연속적으로 어노테이션을 선언할 수 있게 함



#
[참고]
https://velog.io/@pearl0725/Override-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98%EC%9D%98-%EC%9D%98%EB%AF%B8%EC%99%80-%EC%82%AC%EC%9A%A9-%EC%9D%B4%EC%9C%A0%EB%8A%94-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C

https://kkminseok.github.io/posts/2023-01-26-Annotation_Ad01/

https://velog.io/@potato_song/Java-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98-%EC%BB%A4%EC%8A%A4%ED%85%80-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98-%EB%A7%8C%EB%93%A4%EA%B8%B0