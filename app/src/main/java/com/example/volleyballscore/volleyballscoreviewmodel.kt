package com.example.volleyballscore

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class VolleyballScoreViewModel : ViewModel() {
    private val _scoreTeamA = MutableStateFlow(0)
    val scoreTeamA: StateFlow<Int> = _scoreTeamA

    private val _scoreTeamB = MutableStateFlow(0)
    val scoreTeamB: StateFlow<Int> = _scoreTeamB

    private val _setScore = MutableStateFlow("0 - 0")
    val setScore: StateFlow<String> = _setScore

    private val _gameScore = MutableStateFlow("0 - 0")
    val gameScore: StateFlow<String> = _gameScore

    fun addPoint(team: String) {
        if (team == "A") {
            _scoreTeamA.value += 1
        } else {
            _scoreTeamB.value += 1
        }
    }

    fun resetSet() {
        _setScore.value = "0 - 0"
        _scoreTeamA.value = 0
        _scoreTeamB.value = 0
    }

    fun resetGame() {
        _gameScore.value = "0 - 0"
        resetSet()
    }
}
