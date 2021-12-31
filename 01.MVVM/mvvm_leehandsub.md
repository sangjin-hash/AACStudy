### MVVM ( Model - View - View Model )

<img src="https://blog.kakaocdn.net/dn/7IE8f/btqBRvw9sFF/AGLRdsOLuvNZ9okmGOlkx1/img.png" alt="MVVM" width="300" />

- View
	- 사용자에게 보여지는 UI 부분 (다른 패턴과 동일)
	- Activity/Fragment가 View에 포함된다.
	- ViewModel을 관찰하고 있다가 상태 변화가 전달되면 UI를 갱신한다.
	- 여러 뷰가 하나의 뷰 모델을 참조 할 수 있다.
	- 뷰모델에게 사용자 액션을 전달한다. 사용자는 view를 통해 입력하고, view의 값을 바탕으로 viewModel의 값이 변경된다.
	- model에는 영향을 주지 않는다.
	- View는 기본적으로 데이터를 보여주기만 해야 해서 비즈니스 로직을 포함하지 않지만 UI 변경과 관련된 일부 로직은 포함

- Model
	- 어플리케이션에서 사용되는 데이터와 그 데이터를 처리하는 부분
	- 기존 모델에 business logic을 추가하기 위해 MVVM에 SquareParams라는 Model을 추가했다.
	- DB 사용이나 Retrofit을 통한 백엔드 API 호출 등을 주로 수행한다.
	- 모델은 뷰, 뷰 모델 계층을 신경 쓸 필요 없다.
	
- ViewModel
	- View를 표현하기 위해 만든 View를 위한 Model. 뷰에서 사용할 메소드, 필드를 갖는 뷰의 추상화된 모델
	- 기본적으로 View에 종속되지 않는다.(그래서는 안된다)
	- 모든 View와 관련된 비즈니스 로직은 이 곳에 들어가게 되며 데이터를 잘 가공해서 View에서 뿌리기 쉬운 Model로 바꾸는 역할을 함.
	- MVC의 컨트롤러, MVP의 프레젠터를 대신하여 데이터 바인딩, Obsevable을 통해 자신의 상태를 뷰에게 알려 뷰의 갱신을 일으킬 수 있다.

- 동작
	
	(1) 사용자의 Action들은 View를 통해 들어오게 된다.

	(2) View에 Action이 들어오면, Command 패턴으로 View Model에 Action을 전달한다.

	(3) View Model은 Model에게 데이터를 요청한다.

	(4) Model은 View Model에게 요청받은 데이터를 응답한다.

	(5) View Model은 응답 받은 데이터를 가공하여 저장한다.

	(6) View는 View Model과 Data Binding하여 화면을 나타낸다.

- 특징
	- View와 Model 사이에도 의존성이 없다.
	- View Model과 View는 1 : n 관계이다.
	- 하나의 App을 최대한 기능적으로 작은 단위로 나누어 테스트가 쉽고 큰 프로젝트도 상대적으로 관리하기 좋은 구조이다.

- 장점
	- 각각의 부분이 독립적이기 때문에 모듈화하여 개발할 수 있다.
	- 뷰와 모델이 서로를 전혀 신경 쓰지 않기에 유닛 테스트 용이

- 단점
	- ViewModel의 설계가 어렵다.
	- View가 변수와 표현식 모두에 Binding될 수 있으므로 갈수록 presentation logic이 늘어나 XML이 방대해진다. 
		- 이를 방지하려면 항상 ViewModel에서 직접 값을 가져오는 것이 좋다.
