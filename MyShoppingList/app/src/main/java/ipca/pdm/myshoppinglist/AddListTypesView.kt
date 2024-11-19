package ipca.pdm.myshoppinglist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import ipca.pdm.myshoppinglist.ui.theme.MyShoppingListTheme
import kotlinx.coroutines.delay


@Composable
fun AddListTypesView(modifier: Modifier = Modifier, onNavigateToLogin: () -> Unit = {}){

    val viewModel by remember { mutableStateOf(AddListTypesViewModel()) }
    val state = viewModel.state
    val auth = Firebase.auth

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Verifica a cada segundo
            if (FirebaseAuth.getInstance().currentUser == null) {
                onNavigateToLogin()
                break
            }
        }
    }

    Box(modifier = modifier.fillMaxSize() ){
        Row {
            Column {
                Button(onClick = {
                    viewModel.addList()
                }) {
                    Text(text = "Add new list")
                }
                LazyColumn {
                    itemsIndexed(
                        items = state.value.listItems
                    ) { index, item ->
                        Column {
                            Text(text = item.name ?: "")
                            Text(text = item.description ?: "")
                        }
                    }
                }
            }
            Column {
                Button(onClick = {
                    auth.signOut()
                }) {
                    Text(text = "Sign out")
                }
            }
        }

    }

    LaunchedEffect(key1 = Unit) {
        viewModel.loadListTypes()
    }
}

@Preview(showBackground = true)
@Composable
fun AddListTypesViewPreview() {
    MyShoppingListTheme {
        AddListTypesView()
    }
}