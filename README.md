# Shopping application

### About the assignment
Admittedly, the assignment itself is not a complex one but given the amount of work to be done from scratch, I think 4 hours is a non-realistic time-box.
Personally, setting the pagination component along with graphQL took quite some time, since it's something that's not done frequently.



### Tech-stack
I decided to go with a implementation of *MVVM*, using *useCases* in the domain layer and a *repository* (through a *facade*) in the data layer.
For the product screen I decided to wrap the possible [screen states in a sealed class](https://proandroiddev.com/modelling-ui-state-on-android-26314a5975b9) - a pattern that helps narrowing down the states the screen can take and makes it possible to unit test it.
For the feed screen I wasn't able to do this, since I used android's [Paging v.3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview), and with it you are obliged to expose the android specific class PagingData through the ViewModel.

Navigation is done with the [Navigation Component](https://developer.android.com/guide/navigation).
Dependency Injection is done with *Koin*: I chose it over *Dagger/Hilt* because of the ease of setup both for development and for UI testing (which at this point I didn't know if I would cover).

Threading is done with *Coroutines*, using either a single-shot suspending function or a flow stream where it fits.

Unit testing is done with *JUnit 5* (with mockito), making it possible to improve output readability through the usage of annotations.


### What's missing and what could be done better
In order to not extrapolate (too much) the time constraint, I decided to deliver the bare-minimum: feed screen and item screen.
* Usage of a local database
* Proper UI rendering of Loading and Error states is missing (even though this is covered in the useCase/repository layer)
* Extraction of string resources
* Custom implementation of pagination in order to make it more agnostic to android's plataform and to be able to unit test it
* UI tests
