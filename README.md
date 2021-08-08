# StringHolderSample
This repository is the sample of [StringHolder](https://github.com/tkhs0604/StringHolderSample/blob/master/app/src/main/java/com/tkhs0604/stringholdersample/StringHolder.kt) class, which allows Activities and Fragments to utilize string without distinguishing string and string resource.

## How to Use
```kotlin
// MainViewModel.kt
class MainViewModel: ViewModel() {
  private val _textForPlain = MutableLiveData<StringHolder>()
  val textForPlain: LiveData<StringHolder>
    get() = _textForPlain

  private val _textForResource = MutableLiveData<StringHolder>()
  val textForResource: LiveData<StringHolder>
    get() = _textForResource

  init {
    // emit dummy data
    _textForPlain.value = StringHolder.Plain("Hello World!")
    _textForResource.value = StringHolder.Resource(R.string.app_name)
  }
}

// MainActivity.kt
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    
    ...

    viewModel.textForPlain.observe(this) {
      binding.textViewForPlain.text = it.getString(this)
    }
    viewModel.textForResource.observe(this) {
      binding.textViewForResource.text = it.getString(this)
    }
  }
}
```
