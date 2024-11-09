package com.example.ejercicio_botones.ui.theme

data class CheckInfo(
    val titulo :String,
    var seleccionado: Boolean = false,
    var cambiado: (Boolean) -> Unit
)
