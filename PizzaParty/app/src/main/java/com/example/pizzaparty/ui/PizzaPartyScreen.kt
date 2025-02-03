package com.example.pizzaparty.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizzaparty.HungerLevel
import com.example.pizzaparty.R
import com.example.pizzaparty.ui.theme.PizzaPartyTheme

@Composable
fun PizzaPartyScreen(
    modifier: Modifier = Modifier,
    vm: PizzaPartyViewModel = viewModel()
) {
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        AppTitle(modifier)
        
        PartySize(
            numPeopleInput = vm.numPeople,
            onValueChange = { vm.numPeople = it }
        )

        HungerStation(
            hungerLevelSelection = vm.hungerLevel,
            onSelected = { vm.hungerLevel = it }
        )

        Text("Total Pizzas: ${vm.totalPizza}",
            modifier = modifier.padding(bottom = 10.dp)
        )

        CalculateButton(onClick = { vm.calcPizzas() }, modifier = modifier.fillMaxWidth())
    }
}

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Text("Pizza Party",
        modifier = modifier.padding(bottom = 16.dp),
        fontSize = 38.sp
    )
}

@Composable
fun PartySize(
    numPeopleInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NumberField(
        "Number Of People?",
        modifier = modifier.padding(bottom = 16.dp).fillMaxWidth(),
        input = numPeopleInput,
        onValueChange = { onValueChange(it) }
    )
}

@Composable
fun HungerStation(
    modifier: Modifier = Modifier,
    hungerLevelSelection: HungerLevel,
    onSelected: (HungerLevel) -> Unit,
) {
    val hungerOptions = HungerLevel.entries.map { it.name.lowercase().replaceFirstChar { char -> char.uppercaseChar() } }

    RadioGroup(
        labelText = "How Hungry",
        radioOptions = hungerOptions,
        selectedOption = when (hungerLevelSelection) {
            HungerLevel.LIGHT -> hungerOptions[0]
            HungerLevel.MEDIUM -> hungerOptions[1]
            else -> hungerOptions[2]
        },
        onSelected = {
            onSelected(
                when (it) {
                    hungerOptions[0] -> HungerLevel.LIGHT
                    hungerOptions[1] -> HungerLevel.MEDIUM
                    else -> HungerLevel.RAVENOUS
                }
            )
        },
        modifier = modifier
        )
}

@Composable
fun CalculateButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(onClick = onClick, modifier = modifier) {
        Text("Calculate")
    }
}


@Composable
fun NumberField(title: String,
                modifier: Modifier = Modifier,
                input: String,
                onValueChange: (String) -> Unit,
) {
    TextField(value = input,
        onValueChange =  onValueChange,
        label = { Text(title) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}

@Composable
fun RadioGroup(
    labelText: String,
    radioOptions: List<String>,
    selectedOption: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isSelectedOption: (String) -> Boolean = { selectedOption == it }

    Column(
        modifier = modifier.padding(bottom = 10.dp)
    ) {
        Text(labelText)
        radioOptions.forEach { option ->
            Row(
                modifier = modifier
                    .selectable(
                        selected = isSelectedOption(option),
                        onClick = { onSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedOption(option),
                    onClick = null,
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(
                    text = option,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PizzaPartyTheme {
        PizzaPartyScreen()
    }
}

