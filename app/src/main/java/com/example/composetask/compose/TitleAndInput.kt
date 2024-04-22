package com.example.composetask.compose

import com.example.composetask.ui.theme.BLACK
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetask.ui.theme.GRAY
import com.example.composetask.ui.theme.PURPLE3
import com.example.composetask.ui.theme.RED


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleAndInput(
    modifier: Modifier = Modifier,
    title: String,
    errorTitle: String,
    hint: String,
    keyboardType: KeyboardType,
    showInputUser: Boolean = false,
    showTrailingIcon: Boolean = false,
    painter: Painter?,
    spacer: Dp,
    value: String,
    isError: Boolean = false,
    isEmail: Boolean? = null,
    onToggleClicked: () -> Unit = {},
    onValueChange: (String) -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        SpacerHeight(height = spacer)

        Text(
            text = title,
            fontSize = 14.sp,
            color = BLACK,
            fontWeight = FontWeight.Bold
        )

        SpacerHeight(height = 10.dp)

        OutlinedTextField(modifier = modifier
            .fillMaxWidth(),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = hint,
                    fontSize = 12.sp,
                    color = GRAY
                )
            },
            leadingIcon = if (painter != null) {
                {
                    Icon(painter = painter, contentDescription = "icon")
                }
            } else null,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if (isError || isEmail == false) RED else GRAY,
                focusedBorderColor = if (isError || isEmail == false) RED else GRAY,
                focusedTextColor = PURPLE3,
                unfocusedLabelColor = PURPLE3
            ),
            visualTransformation = if (showInputUser) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = if (showTrailingIcon) {
                val image = if (showInputUser)
                    Icons.Default.Visibility
                else Icons.Default.VisibilityOff

                // Localized description for accessibility services
                val description = if (showInputUser) "Hide password" else "Show password"

                // Toggle button to hide or display password
                {
                    IconButton(onClick = {
                        onToggleClicked()
                    }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            } else null

        )

        AnimatedVisibility(visible = isError || isEmail == false) {
            Text(
                text = errorTitle,
                fontSize = 12.sp,
                color = RED,
                fontWeight = FontWeight.Normal
            )
        }

    }

}