package app.mariia.mariia.utils.helpers

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color

fun Modifier.onSilentClick(onClick: () -> Unit) = this
    .clickable(
        interactionSource = null,
        indication = null
    ) { onClick.invoke() }

fun Modifier.dimming() = this
    .drawWithContent {
        drawContent()
        drawRect(color = Color.Black, alpha = 0.6F)
    }

fun Modifier.withCondition(
    condition: Boolean,
    trueModifier: Modifier,
    falseModifier: Modifier = Modifier,
): Modifier = this.then(other = if (condition) trueModifier else falseModifier)