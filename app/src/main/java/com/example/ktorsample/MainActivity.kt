package com.example.ktorsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktorsample.data.model.PostApi
import com.example.ktorsample.ui.theme.KtorSampleTheme
import com.example.ktorsample.view_model.MainViewModel
import io.ktor.http.*
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

					var text by remember {
						mutableStateOf("")
					}

					//MainScreen()

					Column(modifier = Modifier.padding(horizontal = 5.dp)) {
						Spacer(modifier = Modifier.padding(vertical = 10.dp))
						MyTextField(name = "Поиск...", icon = ImageVector.vectorResource(id = R.drawable.baseline_search_24), onValueChange = {text = it}, value = text)
					}


				}
			}
		}
	}
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyTextField(name: String, icon: ImageVector, onValueChange: (String) -> Unit, value: String) {

	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.height(60.dp)
			.fillMaxWidth()
			.border(1.dp, color = Color.Gray, CircleShape)
	) {
		BasicTextField(value = value, onValueChange = onValueChange,
			decorationBox = @Composable{ innerTextField ->
				TextFieldDefaults.TextFieldDecorationBox(
					value = value,
					label = null,
					visualTransformation = VisualTransformation.None,
					innerTextField = innerTextField,
					placeholder = { Text(text = name) } ,
					leadingIcon = null,
					trailingIcon = null,
					singleLine = true,
					enabled = true,
					isError = false,
					interactionSource = remember { MutableInteractionSource() },
					colors = TextFieldDefaults.textFieldColors()
				)
			}
		)


		Icon(imageVector = icon, contentDescription = "", modifier = Modifier.padding(end = 10.dp))
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

