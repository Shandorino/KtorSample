package com.example.ktorsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktorsample.data.model.PostApi
import com.example.ktorsample.ui.theme.KtorSampleTheme
import com.example.ktorsample.view_model.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			KtorSampleTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {


					MainScreen()


				}
			}
		}
	}
}


@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {

	val posts by viewModel.posts.collectAsState()

	var num by remember {
		mutableStateOf("")
	}

	Scaffold(topBar = {
		Row(modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.background(Color.DarkGray)) {
			Button(onClick = { viewModel.getPosts() }) {
				Text(text = "Получить")
			}
			Button(onClick = { viewModel.clear()}) {
				Text(text = "Очистить")
			}
		}
	}) {
		Column() {
			LazyColumn(modifier = Modifier.padding(it)) {

				items(posts) { post ->
					MyCard(post = post)
				}

			}

			Row() {
				TextField(value = num, onValueChange = {num = it})

				Button(onClick = { viewModel.sendNum(num.toInt()) }) {
					Text(text = "Отправить")
				}
			}
		}
	}


}

@Composable
fun MyCard(post: PostApi) {
	Card(modifier = Modifier
		.fillMaxWidth()
		.padding(5.dp)) {
		Column() {
			Text(text = post.id.toString())
			Text(text = post.userId.toString())
			Text(text = post.title)
			Text(text = post.body)
		}
	}
}

