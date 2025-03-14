package com.example.volleyballscore

import VolleyballScoreViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VolleyballScoreScreen()
        }
    }
}

@Composable
fun VolleyballScoreScreen(viewModel: VolleyballScoreViewModel = viewModel()) {
    val scoreA by viewModel.scoreTeamA.collectAsState()
    val scoreB by viewModel.scoreTeamB.collectAsState()
    val setScore by viewModel.setScore.collectAsState()
    val gameScore by viewModel.gameScore.collectAsState()
    val winner by viewModel.winner.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Score Voli", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Game: $gameScore")
        Text(text = "Set: $setScore")

        Spacer(modifier = Modifier.height(16.dp))

        if (winner != null) {
            Text(
                text = winner!!,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Tim A", style = MaterialTheme.typography.h6)
                Text(text = "$scoreA", style = MaterialTheme.typography.h4)

                Button(onClick = { viewModel.addPoint("A") }, enabled = winner == null) {
                    Text(text = "Point A")
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Tim B", style = MaterialTheme.typography.h6)
                Text(text = "$scoreB", style = MaterialTheme.typography.h4)

                Button(onClick = { viewModel.addPoint("B") }, enabled = winner == null) {
                    Text(text = "Point B")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.resetSet() }) {
            Text(text = "Ulangi Set")
        }

        Button(onClick = { viewModel.resetGame() }) {
            Text(text = "Ulangi Game")
        }
    }
}
