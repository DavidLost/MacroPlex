package de.david.macroplex

class Settings(var pointAmount: Int,
               var speedFactor: Float,
               var connectionDistance: Float,
               var minSize: Float, var maxSize: Float,
               var color1: Color, var color2: Color,
               var connectionColor: Color,
               var calcConnectionColor: Boolean,
               var backgroundColor: Color
)

abstract class PointAmount {
    companion object {
        val DEFAULT = 52
        val MIN = 0
        val MAX = 300
    }
}

abstract class SpeedFactor {
    companion object {
        val DEFAULT = 1.8f
        val MIN = 0f
        val MAX = 12f
    }
}

abstract class ConnectionDistance {
    companion object {
        val DEFAULT = 210f
        val MIN = 1f
        val MAX = 1000f
    }
}

abstract class MinSize {
    companion object {
        val DEFAULT = 3f
        val MIN = 0f
        val MAX = 128f
    }
}

abstract class MaxSize {
    companion object {
        val DEFAULT = 12f
        val MIN = 0f
        val MAX = 128f
    }
}

abstract class Color1 {
    companion object {
        val DEFAULT = Color(10f, 65f, 105f, 255f)
    }
}

abstract class Color2 {
    companion object {
        val DEFAULT = Color(10f, 255f, 255f, 255f)
    }
}

abstract class ConnectionColor {
    companion object {
        val DEFAULT = Color(0f, 0f, 0f, 255f)
    }
}

abstract class BackgroundColor {
    companion object {
        val DEFAULT = Color(45f, 45f, 45f, 255f)
    }
}

class Color(var red: Float, var green: Float, var blue: Float, var opacity: Float) {
    fun toJavaFxColor(): javafx.scene.paint.Color {
        return javafx.scene.paint.Color.color(red/255.0, green/255.0, blue/255.0, opacity/255.0)
    }
}