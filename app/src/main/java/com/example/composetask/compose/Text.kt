package com.example.composetask.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun TextViewCompose(
    modifier: Modifier = Modifier,
    text: String,
    fonSize: TextUnit,
    color: Color,
    fontWeight: FontWeight,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        text = text,
        fontSize = fonSize,
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}