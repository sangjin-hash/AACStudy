# MVVM in Android
---
## CleanArchitecture
![image](https://user-images.githubusercontent.com/67935576/147564987-4589845b-2b81-42a0-a015-59b258ce704a.png)
- 로버트 C 마틴에 의해 고안된 프로그래밍 철학
- 핵심 골자는 각 소프트웨어의 '관심사분리'

## MVC
![image](https://user-images.githubusercontent.com/67935576/147565114-6560ed20-af86-4ab4-b00d-d6a25adf5fd9.png)
- Model, View, Controller로 이루어지는 디자인 패턴
- 컨트롤러는 사용자의 입력을 받아 모델에 데이터를 요청, 받아서 뷰에 데이터를 전달, 뷰에서 UI의 변화가 일어난다.
예를들어 버튼을 누르면 현재 시간을 보여주는 프로그램을 구현할 때, 버튼을 클릭하면 컨트롤러는 이를 입력받고 모델에 현재 시간을 요청, 받아서 View에 전달, View는 현재 시간을 나타낸다.
- 안드로이드에서는 Activity/Fragment가 View와 Controller의 역할을 동시에 수행한다.
때문에 프로젝트 규모가 커질수록 Activity에 수많은 로직이 쌓이게 되고, 이는 유지보수하기 힘든 복잡한 구조를 가지게 된다.
- Controller가 안드로이드 프레임워크에 강하게 의존하고 있어 유닛테스트 진행하기가 어려움.

## MVP
![image](https://user-images.githubusercontent.com/67935576/147565663-6ac72378-dfa0-4c05-a1eb-93f903b46734.png)
- Model, View, Presenter로 이루어지는 디자인 패턴
- Presenter가 비즈니스 로직을 담당하고, View는 오로지 UI의 변화만 담당한다.
- 그러나 여전히 View와 Presenter 사이의 의존성이 강하고, 반드시 1:1구조로 이루어 지므로 많은 Presenter가 필요함.


## MVVM
![image](https://user-images.githubusercontent.com/67935576/147565884-19ec0ced-9903-407e-807c-ac95d6c4bd93.png)
- Model, View, ViewModel로 이루어지는 디자인 패턴
- View는 ViewModel을, ViewModel은 Repository(Usecase)를, Repository는 Data를 받아오기만 하면 되는 관심사 분리가 이루어짐.
- MVP의 Presenter와 달리 ViewModel은 View에 대한 참조가 필요 없다.
- 안드로이드에서는 AAC-ViewModel을 사용해 생명주기에 맞게 데이터를 관리할 수 있다.
그러나 이 경우에는 View와 ViewModel은 1:1관계를 유지하는것이 좋다. (View의 생명주기때문)
- 안드로이드에서는 DataBinding 기술을 사용하여 View와 ViewModel 사이의 의존성을 더욱 낮출 수 있다.

## MVVM in Android 예제

```kotlin
    dataBinding {
        enabled = true
    }
```
app단의 gradle 파일에 databinding을 사용할 수 있게 추가한다.

```kotlin
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
```
view(activity)에서 viewModel과 연결해준다.

```kotlin
class MainViewModel : ViewModel() {

    private val myRepository by lazy {
        DefaultMyRepository()
    }
    val data: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    ...(중략)
```
viewModel에서는 repository 객체를 만들어 데이터를 처리하고, 데이터를 담을 LiveData를 사용한다.

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="viewModel"
            type="com.seoplee.androidstudy.screen.main.MainViewModel" />
    </data>
    (...)
            <EditText
            android:id="@+id/emailEditText"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:text="@={viewModel.data}"
    (...)
```
xml 파일에서 databinding을 활용하여 viewModel의 Livedata와 양방향으로 데이터를 바인딩 하여 사용한다.


```kotlin
    viewModel.data.observe(this) {
        ...(ui 처리)
    }
```
view에서 해당 LiveData를 observe하여 사용한다.