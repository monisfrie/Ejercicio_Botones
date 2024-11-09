package com.example.ejercicio_botones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio_botones.ui.theme.CheckInfo
import com.example.ejercicio_botones.ui.theme.Ejercicio_BotonesTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio_BotonesTheme {

                Scaffold(

                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    )
    {
        Spacer(modifier = Modifier.height(25.dp))
        MyProgressBar()
        MyCheckBox()
        Myicon()
        MySwitch()
        MyImage()
    }
}

@Composable
fun MyProgressBar() {
    var show by rememberSaveable { mutableStateOf(false) }
    var progress by rememberSaveable { mutableStateOf(0f) }
    var completado by rememberSaveable { mutableStateOf(false) }

    if (show) {
        LaunchedEffect(Unit) {
            progress = 0f
            while (progress < 1f) {
                delay(50)
                progress += 0.05f
            }
            show = false
            completado = true
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { show = true }) {
            Text("Presionar")
        }

        if (show) {
            completado = false
            Spacer(modifier = Modifier.height(10.dp))

            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(30.dp),
                color = Color.Red,
                strokeWidth = 2.dp,
            )
            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth(),

                )
        }
        if (completado) {
            Text("Carga Completada")
        }


    }
}

@Composable
fun MyCheckBox() {
    var pulsado by rememberSaveable { mutableStateOf(false) }
    Column {
        Checkbox(
            checked = pulsado,
            onCheckedChange = { pulsado = it }
        )
        if (pulsado) {
            Text("Texto Oculto")
        }
    }
}

@Composable
fun GetOptions(titulos: List<String>): List<CheckInfo> {
    return titulos.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            titulo = it,
            seleccionado = status,
            cambiado = { status = it }
        )
    }
}


@Composable
fun MyCheckSwitchBox2() {
    var status1 by rememberSaveable { mutableStateOf(false) }
    var status2 by rememberSaveable { mutableStateOf(false) }
    var status3 by rememberSaveable { mutableStateOf(false) }
    var displayedText by rememberSaveable { mutableStateOf("") }

    val check1 = CheckInfo(
        titulo = "Uno",
        seleccionado = status1,
        cambiado = { status1 = it }
    )
    val check2 = CheckInfo(
        titulo = "Dos",
        seleccionado = status2,
        cambiado = { status2 = it }
    )
    val check3 = CheckInfo(
        titulo = "Tres",
        seleccionado = status3,
        cambiado = { status3 = it }
    )

    Column {
        MyCheckSwitchBox(check1) { isChecked ->
            if (isChecked) {
                displayedText = "Uno"
                status2 = false
                status3 = false
            } else {
                displayedText = ""
            }
        }
        MyCheckSwitchBox(check2) { isChecked ->
            if (isChecked) {
                displayedText = "Dos"
                status1 = false
                status3 = false
            } else {
                displayedText = ""
            }
        }
        MyCheckSwitchBox(check3) { isChecked ->
            if (isChecked) {
                displayedText = "Tres"
                status1 = false
                status2 = false
            } else {
                displayedText = ""
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = displayedText)
    }
}


@Composable
fun MyCheckSwitchBox(checkInfo: CheckInfo, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.seleccionado,
            onCheckedChange = { isChecked ->
                checkInfo.cambiado(isChecked)
                onCheckedChange(isChecked)
            }
        )
        Text(checkInfo.titulo)
    }
}

@Composable
fun Myicon() {
    Button(onClick = {}) {
        Row {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "Casa"
            )

        }
    }
}


@Composable
fun MySwitch() {
    var status1 by rememberSaveable { mutableStateOf(false) }
    var status2 by rememberSaveable { mutableStateOf(false) }
    var status3 by rememberSaveable { mutableStateOf(false) }
    var displayedText by rememberSaveable { mutableStateOf("") }

    var state by rememberSaveable { mutableStateOf(false) }

    Switch(
        checked = state,
        onCheckedChange = { state = it },
        enabled = true,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = Color.Green,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = Color.Red
        )
    )

    val check01 = CheckInfo("Uno", seleccionado = status1, cambiado = { status1 = it })
    val check02 = CheckInfo("Dos", seleccionado = status2, cambiado = { status2 = it })
    val check03 = CheckInfo("Tres", seleccionado = status3, cambiado = { status3 = it })

    Column {
        if (state) {
            MyCheckSwitchBox(check01) { isChecked ->
                if (isChecked) {
                    displayedText = "Uno"
                    status2 = false
                    status3 = false
                } else {
                    displayedText = ""
                }
            }
            MyCheckSwitchBox(check02) { isChecked ->
                if (isChecked) {
                    displayedText = "Dos"
                    status1 = false
                    status3 = false
                } else {
                    displayedText = ""
                }
            }
            MyCheckSwitchBox(check03) { isChecked ->
                if (isChecked) {
                    displayedText = "Tres"
                    status1 = false
                    status2 = false
                } else {
                    displayedText = ""
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = displayedText)
        }
    }
}

@Composable
fun MyImage() {
    var image by rememberSaveable { mutableStateOf(0) }
    val images = listOf(
        painterResource(id = R.drawable.gato),
        painterResource(id = R.drawable.tucan),
        painterResource(id = R.drawable.ardilla)
    )

    Image(
        painter = images[image],
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
            .clickable{ image = (image + 1) % images.size}
    )

}

