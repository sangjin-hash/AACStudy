# MVVM 패턴

> 구조  

<p align="center"><img src="https://user-images.githubusercontent.com/68101656/147802717-4e836a1e-63c4-40ff-bcfd-65fb2ded1d46.png"></p>

* **Model**  
    어플리케이션에서 사용되는 데이터의 값과 데이터의 상태를 보관
* **View Model**  
    View를 나타내기 위한 데이터 처리를 하는 부분  
    ex) textView에 보여질 내용을 담당하는 함수
* **View**  
    사용자에게 보여지는 UI  
<br/>

> 동작  

<p align="center"><img src="https://user-images.githubusercontent.com/68101656/147802766-5be0b3df-6670-47ec-a647-30f3bf486838.png"></p>

1. 사용자의 Action들은 View를 통해 들어온다.
2. <u>*Command 패턴*</u>으로 View Model에 Action을 전달한다.
3. View Model은 필요한 데이터를 Model에게 요청한다.
4. Model은 요청받은 데이터를 View Model에게 응답한다. 
5. View Model은 응답 받은 데이터를 가공해서 저장한다.
6. View는 View Model과의 <u>*Data Binding*</u>을 통해 자동으로 갱신된다.  

    * Command 패턴  
    실행될 기능을 캡슐화 함으로써 여러 기능을 수행할 수 있는 재사용성이 높은 클래스를 설계하는 패턴  
    
    * Data Binding  
    레이아웃의 UI 구성요소를 앱의 데이터 소스와 결합할 수 있는 라이브러리    
    이 패턴을 통해 한 쪽이 바뀌면 다른 쪽도 업데이트가 이루어져 데이터의 일관성을 유지할 수 있다.  
    유지보수가 용이해진다.  
<br/>

> 장 · 단점  
* 장점  
    - 각각은 독립성을 유지하기 때문에 모듈화 하여 개발 할 수 있다.
    - Unit Test가 가능하다.
    - View와 View Model을 바인딩하기 때문에 코드의 양이 줄어든다.
* 단점
    - View Model의 설계가 어렵다.
    - 복잡해질수록 View Model이 빠르게 비대해진다.








