package beltor.caetano.animextestemcasa


import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyTopAppBar() {
    val contextForToast = LocalContext.current.applicationContext

    TopAppBar(
        title = {
            Text(text = "SemicolonSpace")
        },
        actions = {
            // search icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Search,
                description = "Search"
            ) {
                Toast.makeText(contextForToast, "Search Click", Toast.LENGTH_SHORT)
                    .show()
            }

            // lock icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Lock,
                description = "Lock"
            ) {
                Toast.makeText(contextForToast, "Lock Click", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    )
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}