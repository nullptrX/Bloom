/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.common.Screen
import com.example.androiddevchallenge.common.ext.fixedViewModel
import com.example.androiddevchallenge.ui.model.BloomModel
import com.example.androiddevchallenge.ui.theme.BloomTheme
import com.example.androiddevchallenge.ui.theme.shapes

@Composable
fun LoginScreen() {
    val model = viewModel<BloomModel>()
    Content(model = model)
}

@Composable
private fun Content(model: BloomModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .paddingFromBaseline(top = 160.dp)
                .align(Alignment.CenterHorizontally),
            text = "Log in with email",
            style = MaterialTheme.typography.h1,
            color = BloomTheme.colors.h1,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(56.dp),
            label = {
                Text(
                    text = "Email address",
                    style = MaterialTheme.typography.body1,
                    color = BloomTheme.colors.body1,
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            value = email, onValueChange = {
                email = it
            })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(56.dp),
            label = {
                Text(
                    text = "Password (8+ characters)",
                    style = MaterialTheme.typography.body1,
                    color = BloomTheme.colors.body1,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            value = password, onValueChange = {
                password = it
            })
        val terms = "Terms of Use"
        val policy = "Privacy Policy"
        val hint = """
        By clicking below you agree to out $terms and consent to our $policy.
        """.trimIndent()

        val termsStartIndex = hint.indexOf(terms)
        val termsEndIndex = termsStartIndex + terms.length
        val policyStartIndex = hint.indexOf(policy)
        val policyEndIndex = policyStartIndex + policy.length
        val spanStyle = SpanStyle(textDecoration = TextDecoration.Underline)
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .paddingFromBaseline(top = 24.dp),
            text = AnnotatedString(
                text = hint,
                spanStyles = listOf(
                    AnnotatedString.Range(spanStyle, termsStartIndex, termsEndIndex),
                    AnnotatedString.Range(spanStyle, policyStartIndex, policyEndIndex),
                ),
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = BloomTheme.colors.body2,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = BloomTheme.colors.buttonBackground,
                contentColor = BloomTheme.colors.buttonText1,
            ),
            shape = shapes.large,
            onClick = {
                model.push(Screen.home)
            },
        ) {
            Text(
                text = "Log in",
                style = MaterialTheme.typography.button,
                color = BloomTheme.colors.buttonText1,
            )
        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreViewLoginScreen() {
    BloomTheme {
        Content(model = fixedViewModel())
    }
}


@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreViewLoginScreen() {
    BloomTheme(BloomTheme.Theme.Dark) {
        Content(model = fixedViewModel())
    }
}

