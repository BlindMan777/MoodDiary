package com.mooddiary.app.elements

import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mooddiary.app.data.SavedMoods

@Composable
fun MoodPieChart(savedMoods: List<SavedMoods>, selectedMood: String) {

    val greatMoodCount = savedMoods.count { it.mood == "great_mood" }
    val goodMoodCount = savedMoods.count { it.mood == "good_mood" }
    val neutralMoodCount = savedMoods.count { it.mood == "neutral_mood" }
    val badMoodCount = savedMoods.count { it.mood == "bad_mood" }


    val moodData = listOf(
        greatMoodCount to Color.Green,
        goodMoodCount to Color.Yellow,
        neutralMoodCount to Color.LightGray,
        badMoodCount to Color.Red
    )

    val totalCount = savedMoods.size
    val angles = mutableListOf<Float>()

    var accumulateAngle = 0f
    moodData.take(3).forEach { (count, _) ->
        val angle = 360f * (count.toFloat() / totalCount)
        angles.add(angle)
        accumulateAngle += angle
    }
    angles.add(360f - accumulateAngle)

    val text:String = when(selectedMood) {
        "great_mood" -> greatMoodCount.toString()
        "good_mood" -> goodMoodCount.toString()
        "neutral_mood" -> neutralMoodCount.toString()
        "bad_mood" -> badMoodCount.toString()
        else -> ""
    }

    val textColor = when (selectedMood) {
        "great_mood" -> Color.Green
        "good_mood" -> Color.Yellow
        "neutral_mood" -> Color.LightGray
        "bad_mood" -> Color.Red
        else -> MaterialTheme.colorScheme.surface
    }

    val primaryColor = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.size(150.dp)) {

        val radius = size.minDimension / 2

        var startAngle = 0f
        moodData.forEachIndexed { index, (_, color) ->
            val sweepAngle = angles[index]
            drawArc(
                color = color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = Offset.Zero,
                size = Size(radius * 2, radius * 2)
            )
            startAngle += sweepAngle
        }
//        val greatSweepAngle = 360f * (greatMoodCount.toFloat() / totalCount)
//        val goodSweepAngle = 360f * (goodMoodCount.toFloat() / totalCount)
//        val neutralSweepAngle = 360f * (neutralMoodCount.toFloat() / totalCount)
//        val badSweepAngle = 360f - greatSweepAngle - goodSweepAngle - neutralSweepAngle

//        drawArc(
//            color = Color.Green,
//            startAngle = startAngle,
//            sweepAngle = greatSweepAngle,
//            useCenter = true,
//            topLeft = Offset.Zero,
//            size = Size(radius * 2, radius * 2)
//        )
//
//        drawArc(
//            color = Color.Yellow,
//            startAngle = greatSweepAngle,
//            sweepAngle = goodSweepAngle,
//            useCenter = true,
//            topLeft = Offset.Zero,
//            size = Size(radius * 2, radius * 2)
//        )
//
//        drawArc(
//            color = Color.LightGray,
//            startAngle = greatSweepAngle + goodSweepAngle,
//            sweepAngle = neutralSweepAngle,
//            useCenter = true,
//            topLeft = Offset.Zero,
//            size = Size(radius * 2, radius * 2)
//        )
//
//        drawArc(
//            color = Color.Red,
//            startAngle = greatSweepAngle + goodSweepAngle + badSweepAngle,
//            sweepAngle = badSweepAngle,
//            useCenter = true,
//            topLeft = Offset.Zero,
//            size = Size(radius * 2, radius * 2)
//        )

        drawCircle(
            color = primaryColor,
            radius = radius - 10.dp.toPx(),
            center = Offset(radius, radius)
        )

        drawIntoCanvas { canvas ->
            val textPaint = Paint().asFrameworkPaint().apply {
                color = textColor.toArgb()
                textSize = 24.sp.toPx()
            }
            val textBounds = Rect()
            textPaint.getTextBounds(text, 0, text.length, textBounds)
            canvas.nativeCanvas.drawText(
                text,
                radius - textBounds.exactCenterX(),
                radius - textBounds.exactCenterY(),
                textPaint
            )
        }
    }
}