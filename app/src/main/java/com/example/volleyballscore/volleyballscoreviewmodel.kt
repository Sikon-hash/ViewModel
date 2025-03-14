import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VolleyballScoreViewModel : ViewModel() {
    private val _scoreTeamA = MutableStateFlow(0)
    val scoreTeamA = _scoreTeamA.asStateFlow()

    private val _scoreTeamB = MutableStateFlow(0)
    val scoreTeamB = _scoreTeamB.asStateFlow()

    private val _setScore = MutableStateFlow(0)
    val setScore = _setScore.asStateFlow()

    private val _gameScore = MutableStateFlow(0)
    val gameScore = _gameScore.asStateFlow()

    private val _winner = MutableStateFlow<String?>(null)
    val winner = _winner.asStateFlow()

    fun addPoint(team: String) {
        if (_winner.value != null) return

        when (team) {
            "A" -> _scoreTeamA.value += 1
            "B" -> _scoreTeamB.value += 1
        }

        checkWinner()
    }

    private fun checkWinner() {
        if (_scoreTeamA.value >= 25 && _scoreTeamA.value - _scoreTeamB.value >= 2) {
            _winner.value = "Tim A Menang!"
        } else if (_scoreTeamB.value >= 25 && _scoreTeamB.value - _scoreTeamA.value >= 2) {
            _winner.value = "Tim B Menang!"
        }
    }

    fun resetSet() {
        _scoreTeamA.value = 0
        _scoreTeamB.value = 0
        _winner.value = null
    }

    fun resetGame() {
        resetSet()
        _setScore.value = 0
        _gameScore.value = 0
    }
}
